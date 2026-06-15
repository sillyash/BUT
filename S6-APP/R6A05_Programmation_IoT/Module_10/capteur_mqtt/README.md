# MQTT

## Subscribe

```bash
mosquitto_sub -h 192.0.2.1 -t "temp"
```

## Test pub

```bash
mosquitto_pub -h 192.0.2.1 -t "temp" -m "22.5"
```

