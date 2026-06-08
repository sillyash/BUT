#include <zephyr/net/net_core.h>
#include <zephyr/net/net_if.h>
#include <zephyr/net/net_pkt.h>
#include <zephyr/net/net_ip.h>
#include <zephyr/kernel.h>

int main(void)
{
    printk("Serveur ICMP actif. Testez avec: ping 192.0.2.4\n");
    return 0;
}
