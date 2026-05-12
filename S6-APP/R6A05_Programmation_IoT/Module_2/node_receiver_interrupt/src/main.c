/* @file main_interrupt.c
 * @brief UART receiver node using interrupts
 */

#include <zephyr/kernel.h>
#include <zephyr/drivers/uart.h>
#include <zephyr/sys/printk.h>

// UART device configuration
#define UART_DEVICE_NODE DT_NODELABEL(uart0)
static const struct device *uart_dev;

// Receive buffer
static uint8_t rx_data;

// UART interrupt callback
void uart_callback(const struct device *dev, void *user_data)
{
    ARG_UNUSED(user_data);
    //printk("Inside callback.\n");
    // Necessario per aggiornare lo stato degli interrupt
    if (!uart_irq_update(dev)) {
        printk("Can't configure interrupts! Exiting.\n");
        return;
    }
    //printk("Callback ok.\n");
    // Check if data is received
    if (uart_irq_rx_ready(dev)) {
        //printk("Coda pronta.\n");
        int ret = uart_fifo_read(dev, &rx_data, 1);
        //printk("uart_fifo_read ret: %d\n", ret); // Cosa stampa?
        if (ret > 0) {
            printk("Reçu: %c\n", rx_data);
        }
    }
}

void main(void)
{
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        printk("UART device not ready\n");
        return;
    }

    // Configure UART interrupt
    uart_irq_callback_set(uart_dev, uart_callback);
    uart_irq_rx_enable(uart_dev);

    while (1) {
        // Main loop does nothing, everything is handled by interrupt
        k_sleep(K_SECONDS(1));
    }
}
