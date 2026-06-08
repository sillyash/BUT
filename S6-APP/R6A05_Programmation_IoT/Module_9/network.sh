#!/bin/sh

ip l del tap0
ip tuntap add dev tap0 mode tap
ip link set tap0 up
ip addr add 192.0.2.1/24 dev tap0
