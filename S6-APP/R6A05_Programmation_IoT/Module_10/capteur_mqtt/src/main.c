#include <zephyr/net/socket.h>
#include <zephyr/net/mqtt.h>
#include <zephyr/net/net_config.h>
#include <zephyr/logging/log.h>
#include <zephyr/kernel.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <zephyr/random/random.h>

LOG_MODULE_REGISTER(main);

#define STACK_SIZE 4096
#define MQTT_BUFFER_SIZE 128
#define TEMP_SEND_INTERVAL_MS 5000

#define CONFIG_MQTT_BROKER_PORT 1883
#define CONFIG_MQTT_CLIENT_ID "capteur1"
#define CONFIG_MQTT_PUB_TOPIC "temp"
#define CONFIG_MQTT_BROKER_HOSTNAME "192.0.2.1" /* Check ip a tap0 for addr */


static struct mqtt_client client;
static struct sockaddr_in broker;
// memset(&broker, 0, sizeof(broker));
static uint8_t rx_buffer[MQTT_BUFFER_SIZE];
static uint8_t tx_buffer[MQTT_BUFFER_SIZE];
static char payload[64];

K_TIMER_DEFINE(temp_timer, NULL, NULL); // timer kernel optionnel
static void publish_temperature(struct k_work *work);
K_WORK_DEFINE(pub_work, publish_temperature);

// Simule une température aléatoire
static int get_temperature(void)
{
    return 20 + (sys_rand32_get() % 10); // 20 à 29 degrés
}

static void publish_temperature(struct k_work *work)
{
    int temp = get_temperature();
    snprintf(payload, sizeof(payload), "{\"temperature\": %d}", temp);

    struct mqtt_publish_param param;
    param.message.topic.qos = MQTT_QOS_0_AT_MOST_ONCE;
    param.message.topic.topic.utf8 = CONFIG_MQTT_PUB_TOPIC;
    param.message.topic.topic.size = strlen(CONFIG_MQTT_PUB_TOPIC);
    param.message.payload.data = payload;
    param.message.payload.len = strlen(payload);
    param.message_id = sys_rand32_get();
    param.dup_flag = 0;
    param.retain_flag = 0;

    int rc = mqtt_publish(&client, &param);
    if (rc) {
        LOG_ERR("Erreur publication MQTT: %d", rc);
    } else {
        LOG_INF("Température publiée: %s", payload);
    }
}

static void mqtt_event_handler(struct mqtt_client *c, const struct mqtt_evt *evt)
{
    switch (evt->type) {
        case MQTT_EVT_CONNACK:
            LOG_INF("Connexion MQTT établie.");
            break;
        case MQTT_EVT_DISCONNECT:
            LOG_WRN("Déconnecté du serveur MQTT.");
            break;
        default:
            break;
    }
}

static void configure_mqtt_client(void)
{
    mqtt_client_init(&client);

    broker.sin_family = AF_INET;
    broker.sin_port = htons(CONFIG_MQTT_BROKER_PORT);
    inet_pton(AF_INET, CONFIG_MQTT_BROKER_HOSTNAME, &broker.sin_addr);

    client.broker = &broker;
    client.evt_cb = mqtt_event_handler;
    client.client_id.utf8 = CONFIG_MQTT_CLIENT_ID;
    client.client_id.size = strlen(CONFIG_MQTT_CLIENT_ID);
    client.password = NULL;
    client.user_name = NULL;
    client.protocol_version = MQTT_VERSION_3_1_1;
    client.rx_buf = rx_buffer;
    client.rx_buf_size = sizeof(rx_buffer);
    client.tx_buf = tx_buffer;
    client.tx_buf_size = sizeof(tx_buffer);
    client.transport.type = MQTT_TRANSPORT_NON_SECURE;
}

int main(void)
{
    LOG_INF("Démarrage du capteur MQTT");

    if (!net_if_is_up(net_if_get_default())) {
        LOG_ERR("Interface réseau non disponible");
        return 1;
    }

    LOG_INF("Tentative de connexion TCP vers %s:%d", CONFIG_MQTT_BROKER_HOSTNAME, CONFIG_MQTT_BROKER_PORT);
    memset(&broker, 0, sizeof(broker));

    int sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

    if (sock < 0) {
        LOG_ERR("socket() chou : %d", errno);
    } else {
        LOG_INF("socket() réussi: %d", sock);
    }

    int rc = connect(sock, (struct sockaddr *)&broker, sizeof(struct sockaddr_in));

    if (rc < 0) {
        LOG_ERR("connect() chou : %d", errno);
    } else {
        LOG_INF("connect() réussi");
    }

    configure_mqtt_client();
    rc = mqtt_connect(&client);

    if (rc != 0) {
        LOG_ERR("Échec connexion MQTT: %d", rc);
        LOG_ERR("Adresse du broker: %s:%d", CONFIG_MQTT_BROKER_HOSTNAME, CONFIG_MQTT_BROKER_PORT);
        return 0;
    }

    while (1) {
        mqtt_input(&client);
        mqtt_live(&client);
        k_work_submit(&pub_work);
        k_sleep(K_MSEC(TEMP_SEND_INTERVAL_MS));
    }

    return 0;
}
