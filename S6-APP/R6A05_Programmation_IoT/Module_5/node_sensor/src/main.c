#include <zephyr/kernel.h>
#include <zephyr/device.h>
#include <zephyr/drivers/uart.h>
#include <zephyr/logging/log.h>
#include <string.h>

LOG_MODULE_REGISTER(sensor, LOG_LEVEL_INF);

#define UART_DEVICE_NODE DT_NODELABEL(uart0)
const struct device *uart_dev;

struct sensor_msg {
    uint8_t node_id;
    int8_t temperature;
    uint32_t timestamp;
} __packed;

void main(void)
{
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        LOG_ERR("UART non prêt");
        return;
    }

    struct sensor_msg msg;
    msg.node_id = 42; // ID du noeud
    int timestamp = 0;

    while (1) {
        msg.temperature = 20 + (timestamp % 5);  // Simule une température variable
        msg.timestamp = timestamp;

        uart_poll_out(uart_dev, *((uint8_t *)&msg + 0));
        uart_poll_out(uart_dev, *((uint8_t *)&msg + 1));
        uart_poll_out(uart_dev, *((uint8_t *)&msg + 2));
        uart_poll_out(uart_dev, *((uint8_t *)&msg + 3));
        uart_poll_out(uart_dev, *((uint8_t *)&msg + 4));
        uart_poll_out(uart_dev, *((uint8_t *)&msg + 5));

        LOG_INF("Message envoyé : ID=%d, Temp=%d, T=%d", msg.node_id, msg.temperature, msg.timestamp);

        timestamp += 10;
        k_sleep(K_SECONDS(5));
    }
}
