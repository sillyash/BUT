#include <string.h>
#include <zephyr/kernel.h>
#include <zephyr/logging/log.h>
#include <zephyr/drivers/uart.h>

LOG_MODULE_REGISTER(uart_receiver, LOG_LEVEL_INF);

#define UART_DEVICE_NODE DT_NODELABEL(uart0)
const struct device *uart_dev;

// Taille maximale d'un message reçu
#define MSG_SIZE 64

// Déclaration de la file de message
K_MSGQ_DEFINE(uart_msgq, sizeof(uint8_t), MSG_SIZE, 4);

// Buffer temporaire pour construire un message
char current_msg[MSG_SIZE];
size_t current_len = 0;

// Déclaration du travail différé
struct k_work uart_work;

// Callback de traitement
void uart_work_handler(struct k_work *work)
{
    current_msg[current_len] = '\0'; // Terminer la chaîne
    printk("Message traité (k_work) : %s", current_msg);
    current_len = 0; // Réinitialiser le buffer
}

// Fonction principale
void main(void)
{
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        LOG_ERR("Périphérique UART non prêt !");
        return;
    }

    // Initialiser le k_work avec la fonction handler
    k_work_init(&uart_work, uart_work_handler);

    uint8_t c;
    LOG_INF("Récepteur UART initialisé, en attente de données...");

    while (1) {
        // Lire caractère par caractère (non bloquant)
        if (uart_poll_in(uart_dev, &c) == 0) {
            // Ajouter à la file de message
            if (k_msgq_put(&uart_msgq, &c, K_NO_WAIT) != 0) {
                LOG_WRN("File UART pleine, caractère perdu !");
                continue;
            }
            // Ajouter au buffer local
            if (current_len < MSG_SIZE - 1) {
                current_msg[current_len++] = c;
            }
            // Si fin de ligne, déclencher le traitement différé
            if (c == '\n') {
                k_work_submit(&uart_work);
            }
        }
        k_sleep(K_MSEC(10));
    }
}
