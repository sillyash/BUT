/*
* Capteur JSON - Génération et envoi de données au format JSON via UART
*/

#include <zephyr/kernel.h>
#include <zephyr/device.h>
#include <zephyr/drivers/uart.h>
#include <zephyr/sys/printk.h>
#include <zephyr/random/random.h>
#include <string.h>
#include "cJSON.h"

/* Définitions */
#define UART_DEVICE_NODE DT_CHOSEN(zephyr_shell_uart)
#define SENSOR_DATA_INTERVAL K_SECONDS(5)
#define BUFFER_SIZE 256

/* Variables globales */
static const struct device *const uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
static struct k_work sensor_work;
static struct k_work_delayable sensor_timer_work;

/* Structure pour simuler des données capteur */
struct sensor_data {
    int temperature;
    int humidite;
    int pression;
    int luminosite;
    int batterie;
};

/* Fonction pour générer des données simulées de capteur */
static void generer_donnees_capteur(struct sensor_data *data) {
    data->temperature = (sys_rand32_get() % 40) - 5;        // -5°C à 35°C
    data->humidite = sys_rand32_get() % 101;               // 0% à 100%
    data->pression = 990 + (sys_rand32_get() % 40);        // 990 à 1030 hPa
    data->luminosite = sys_rand32_get() % 10001;           // 0 à 10000 lux
    data->batterie = 50 + (sys_rand32_get() % 51);         // 50% à 100%
}

/* Fonction pour envoyer des données par UART */
static void uart_envoyer_donnees(const char *donnees) {
    for (size_t i = 0; i < strlen(donnees); i++) {
        uart_poll_out(uart_dev, donnees[i]);
    }
    uart_poll_out(uart_dev, '\n'); // Ajouter un retour à la ligne à la fin
}

/* Fonction de worker pour créer et envoyer le JSON */
static void sensor_work_handler(struct k_work *work) {
    char buffer[BUFFER_SIZE];
    struct sensor_data data;
    
    // Générer des données simulées
    generer_donnees_capteur(&data);
    
    // Créer l'objet JSON
    cJSON *root = cJSON_CreateObject();
    if (root == NULL) {
        printk("Erreur lors de la création de l'objet JSON\n");
        return;
    }
    
    // Ajouter les champs
    cJSON_AddStringToObject(root, "type", "lecture_capteur");
    cJSON_AddNumberToObject(root, "temperature", data.temperature);
    cJSON_AddNumberToObject(root, "humidite", data.humidite);
    cJSON_AddNumberToObject(root, "pression", data.pression);
    cJSON_AddNumberToObject(root, "luminosite", data.luminosite);
    cJSON_AddNumberToObject(root, "batterie", data.batterie);
    cJSON_AddNumberToObject(root, "timestamp", k_uptime_get() / 1000);
    
    // Convertir en chaîne JSON
    char *json_str = cJSON_Print(root);
    if (json_str == NULL) {
        printk("Erreur lors de la conversion en chaîne JSON\n");
        cJSON_Delete(root);
        return;
    }
    
    // Copier dans notre buffer pour éviter les erreurs de longueur
    if (strlen(json_str) < BUFFER_SIZE) {
        strcpy(buffer, json_str);
        
        // Envoyer les données via UART
        printk("Envoi de données: %s\n", buffer);
        uart_envoyer_donnees(buffer);
    } else {
        printk("Données JSON trop grandes pour le buffer\n");
    }
    
    // Nettoyer la mémoire
    cJSON_free(json_str);
    cJSON_Delete(root);
}

/* Fonction pour planifier la prochaine lecture du capteur */
static void sensor_timer_work_handler(struct k_work *work) {
    // Soumettre le travail de lecture du capteur
    k_work_submit(&sensor_work);
    
    // Replanifier le timer
    struct k_work_delayable *dwork = k_work_delayable_from_work(work);
    k_work_schedule(dwork, SENSOR_DATA_INTERVAL);
}

void main(void) {
    if (!device_is_ready(uart_dev)) {
        printk("UART non disponible\n");
        return;
    }

    printk("Capteur JSON démarré\n");
    
    // Initialiser les kworkers
    k_work_init(&sensor_work, sensor_work_handler);
    k_work_init_delayable(&sensor_timer_work, sensor_timer_work_handler);
    
    // Démarrer la première lecture après 1 seconde
    k_work_schedule(&sensor_timer_work, K_SECONDS(1));
    
    // Le programme principal ne fait rien d'autre, tout est géré par les kworkers
}
