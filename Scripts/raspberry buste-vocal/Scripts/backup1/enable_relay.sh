#!/bin/bash
#enable_relay.sh

echo 1 > /sys/class/gpio/gpio$1/value
sleep $2
echo 0 > /sys/class/gpio/gpio$1/value
