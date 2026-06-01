/*
* Serveur JSON - Réception et traitement de données au format JSON via UART
*/

#include <zephyr/kernel.h>
#include <zephyr/device.h>
#include <zephyr/drivers/uart.h>
#include <zephyr/sys/printk.h>
#include <string.h>
#include "cJSON.h"

/* Définitions */
#define UART_DEVICE_NODE DT_CHOSEN(zephyr_shell_uart)
#define BUFFER_SIZE 512
#define RECV_TIMEOUT K_MSEC(100)

/* Variables globales */
static const struct device *const uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
static struct k_work json_work;
static char rx_buf[BUFFER_SIZE];
static int rx_buf_pos;
static K_MUTEX_DEFINE(rx_mutex);

/* Structure pour stocker les données du capteur */
struct capteur_data {
    int temperature;
    int humidite;
    int pression;
    int luminosite;
    int batterie;
    long long timestamp;
};

/* Fonction pour traiter les données JSON reçues */
static void traiter_json(const char *json_str) {
    struct capteur_data data;

    // Analyser le JSON
    cJSON *root = cJSON_Parse(json_str);
    if (root == NULL) {
        printk("Erreur d'analyse JSON: %s\n", json_str);
        const char *error_ptr = cJSON_GetErrorPtr();
        if (error_ptr != NULL) {
            printk("Erreur à: %s\n", error_ptr);
        }
        return;
    }

    // Vérifier le type de message
    cJSON *type = cJSON_GetObjectItemCaseSensitive(root, "type");
    if (cJSON_IsString(type) && strcmp(type->valuestring, "lecture_capteur") == 0) {
        cJSON *temp = cJSON_GetObjectItemCaseSensitive(root, "temperature");
        cJSON *humid = cJSON_GetObjectItemCaseSensitive(root, "humidite");
        cJSON *press = cJSON_GetObjectItemCaseSensitive(root, "pression");
        cJSON *lumin = cJSON_GetObjectItemCaseSensitive(root, "luminosite");
        cJSON *batt = cJSON_GetObjectItemCaseSensitive(root, "batterie");
        cJSON *timestamp = cJSON_GetObjectItemCaseSensitive(root, "timestamp");

        if (cJSON_IsNumber(temp) && cJSON_IsNumber(humid) &&
            cJSON_IsNumber(press) && cJSON_IsNumber(lumin) &&
            cJSON_IsNumber(batt) && cJSON_IsNumber(timestamp)) {

            data.temperature = (int)temp->valuedouble;
            data.humidite = (int)humid->valuedouble;
            data.pression = (int)press->valuedouble;
            data.luminosite = (int)lumin->valuedouble;
            data.batterie = (int)batt->valuedouble;
            data.timestamp = (long long)timestamp->valuedouble;

            printk("--- Données du capteur reçues ---\n");
            printk("Timestamp: %lld s\n", data.timestamp);
            printk("Température: %d°C\n", data.temperature);
            printk("Humidité: %d%%\n", data.humidite);
            printk("Pression: %d hPa\n", data.pression);
            printk("Luminosité: %d lux\n", data.luminosite);
            printk("Batterie: %d%%\n", data.batterie);
            printk("-------------------------------\n");
        }
    }

    // Libérer la mémoire
    cJSON_Delete(root);
}

/* Fonction de worker pour traiter les données JSON */
static void json_work_handler(struct k_work *work) {
    char buffer_local[BUFFER_SIZE];

    // Accéder au buffer en sécurité
    k_mutex_lock(&rx_mutex, K_FOREVER);

    // Copier et traiter uniquement si nous avons des données
    if (rx_buf_pos > 0) {
        // Terminer la chaîne
        rx_buf[rx_buf_pos] = '\0';

        // Copier le buffer pour le traitement
        strcpy(buffer_local, rx_buf);

        // Réinitialiser la position pour la prochaine réception
        rx_buf_pos = 0;

        k_mutex_unlock(&rx_mutex);

        // Traiter les données JSON
        traiter_json(buffer_local);
    } else {
        k_mutex_unlock(&rx_mutex);
    }
}

/* Fonction pour lire les données UART en polling */
static void lire_uart(void) {
    uint8_t c;

    // Accéder au buffer en sécurité
    k_mutex_lock(&rx_mutex, K_FOREVER);

    // Lire les caractères disponibles
    while (uart_poll_in(uart_dev, &c) == 0) {
        if (c == '\n' || c == '\r') {
            // Fin de ligne, traiter le message
            if (rx_buf_pos > 0) {
                // Soumettre le travail de traitement JSON
                k_work_submit(&json_work);
            }
        } else if (rx_buf_pos < BUFFER_SIZE - 1) {
            // Ajouter au buffer
            rx_buf[rx_buf_pos++] = c;
        }
    }

    k_mutex_unlock(&rx_mutex);
}

void main(void) {
    if (!device_is_ready(uart_dev)) {
        printk("UART non disponible\n");
        return;
    }

    // Initialiser le worker JSON
    k_work_init(&json_work, json_work_handler);

    // Initialiser le buffer de réception
    rx_buf_pos = 0;

    printk("Serveur JSON démarré et en attente de données...\n");

    // Boucle principale - polling UART
    while (1) {
        // Lire les données UART disponibles
        lire_uart();

        // Attendre un peu avant la prochaine lecture
        k_sleep(K_MSEC(10));
    }
}
