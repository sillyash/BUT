#include <zephyr/net/net_if.h>
#include <zephyr/net/net_config.h>
#include <zephyr/net/net_ip.h>
#include <zephyr/net/net_event.h>
#include <zephyr/logging/log.h>
#include <zephyr/kernel.h>

LOG_MODULE_REGISTER(main, LOG_LEVEL_INF);

int main(void)
{
    // 1. Récupération de l'interface par défaut
    struct net_if *iface = net_if_get_default();
    
    if (!iface) {
        LOG_ERR("Aucune interface réseau détectée.");
        return -ENODEV;
    }
    
    struct net_if_config *cfg = net_if_get_config(iface);
    
    // CORRECTION : Accès à .ipv4.is_used
    if (!cfg || !cfg->ip.ipv4 || !cfg->ip.ipv4->unicast[0].ipv4.is_used) {
        LOG_ERR("Pas de configuration IPv4 ou d'adresse IP attribuée.");
        return -ENOENT;
    }
    
    char addr_str[NET_IPV4_ADDR_LEN];
    
    // CORRECTION : Accès à .ipv4.address.in_addr
    net_addr_ntop(
        AF_INET,
        &cfg->ip.ipv4->unicast[0].ipv4.address.in_addr,
        addr_str,
        sizeof(addr_str)
    );

    LOG_INF("Adresse IP: %s", addr_str);

    return 0;
}
