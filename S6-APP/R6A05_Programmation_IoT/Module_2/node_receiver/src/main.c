/* @file main.c
 * @brief UART receiver node using polling
 */

#include <zephyr/kernel.h>
#include <zephyr/drivers/uart.h>

// Buffer pour recevoir les données
uint8_t rx_data;

#define UART_DEVICE_NODE DT_NODELABEL(uart0)
const struct device *uart_dev;

void main(void) {
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        printk("UART device not ready\n");
        return;
    }

    while (1) {
        // Lecture bloquante d'un caractère
        if (uart_poll_in(uart_dev, &rx_data) == 0) {
            printk("Reçu: %c\n", rx_data);
        }
        k_sleep(K_MSEC(10));
    }
}
