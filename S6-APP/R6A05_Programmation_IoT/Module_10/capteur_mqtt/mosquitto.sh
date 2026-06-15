#!/bin/bash

mkdir -p /tmp/mosquitto

apt update
apt install mosquitto mosquitto-clients

echo "listener 1883" > /tmp/mosquitto/mosquitto.conf
echo "allow_anonymous true" >> /tmp/mosquitto/mosquitto.conf

/usr/sbin/mosquitto -c /tmp/mosquitto/mosquitto.conf -v
