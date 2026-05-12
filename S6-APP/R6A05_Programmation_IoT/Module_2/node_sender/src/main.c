/* @file main.c
 * @brief UART sender node using polling
 */

#include <zephyr/kernel.h>
#include <zephyr/drivers/uart.h>
#include <string.h>

// On utilise UART0, typiquement disponible sur les boards nRF52840
#define UART_DEVICE_NODE DT_NODELABEL(uart0)
const struct device *uart_dev;

void main(void) {
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        printk("UART device not ready\n");
        return;
    }

    const char *msg = "Message depuis sender\n";

    while (1) {
        // Envoi caractère par caractère (UART est synchrone)
        for (size_t i = 0; i < strlen(msg); i++) {
            uart_poll_out(uart_dev, msg[i]);
        }
        k_sleep(K_SECONDS(2));
    }
}

