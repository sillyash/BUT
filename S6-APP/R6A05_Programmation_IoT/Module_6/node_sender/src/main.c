#include <zephyr/kernel.h>
#include <zephyr/drivers/uart.h>
#include <string.h>

#define UART_DEVICE_NODE DT_NODELABEL(uart0)

const struct device *uart_dev;
static struct k_timer my_timer;
const char *msg = "Message périodique du sender\n";

// Fonction appelée à chaque expiration du timer
void timer_expiry_function(struct k_timer *timer_id)
{
    for (size_t i = 0; i < strlen(msg); i++) {
        uart_poll_out(uart_dev, msg[i]);
    }
}

void main(void)
{
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        printk("UART device not ready\n");
        return;
    }

    // Initialisation du timer avec callback, sans fonction de stop
    k_timer_init(&my_timer, timer_expiry_function, NULL);

    // Démarrage : période = 3s, délai initial = 1s
    k_timer_start(&my_timer, K_SECONDS(1), K_SECONDS(3));

    // Boucle principale vide : tout est géré par le timer
    while (1) {
        k_sleep(K_FOREVER);
    }
}