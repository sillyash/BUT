#include <zephyr/kernel.h>
#include <zephyr/device.h>
#include <zephyr/drivers/uart.h>
#include <zephyr/logging/log.h>
#include <string.h>

LOG_MODULE_REGISTER(server, LOG_LEVEL_INF);

#define UART_DEVICE_NODE DT_NODELABEL(uart0)
const struct device *uart_dev;

#define MSG_SIZE sizeof(struct sensor_msg)
K_MSGQ_DEFINE(uart_msgq, sizeof(uint8_t), 64, 4);

struct sensor_msg {
    uint8_t node_id;
    int8_t temperature;
    uint32_t timestamp;
} __packed;

char buffer[MSG_SIZE];
size_t current_len = 0;

struct k_work uart_work;

void uart_work_handler(struct k_work *work)
{
    if (current_len != MSG_SIZE) {
        LOG_WRN("Message incomplet");
        current_len = 0;
        return;
    }

    struct sensor_msg *msg = (struct sensor_msg *)buffer;

    LOG_INF("Reçu ID=%d Temp=%d Time=%d", msg->node_id, msg->temperature, msg->timestamp);

    // Vider la file
    uint8_t dummy;
    for (size_t i = 0; i < current_len; i++) {
        k_msgq_get(&uart_msgq, &dummy, K_NO_WAIT);
    }

    current_len = 0;
}

void main(void)
{
    uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);
    if (!device_is_ready(uart_dev)) {
        LOG_ERR("UART non prêt");
        return;
    }

    k_work_init(&uart_work, uart_work_handler);
    LOG_INF("Serveur prêt à recevoir");

    uint8_t c;
    while (1) {
        if (uart_poll_in(uart_dev, &c) == 0) {
            k_msgq_put(&uart_msgq, &c, K_NO_WAIT);
            if (current_len < MSG_SIZE) {
                buffer[current_len++] = c;
            }
            if (current_len == MSG_SIZE) {
                k_work_submit(&uart_work);
            }
        }

        k_sleep(K_MSEC(10));
    }
}
