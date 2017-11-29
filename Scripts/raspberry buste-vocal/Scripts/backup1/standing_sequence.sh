#!/bin/bash
#standing_sequence.sh

echo 1 > /sys/class/gpio/gpio$1/value
sleep 8
echo 0 > /sys/class/gpio/gpio$1/value
sleep 1
echo 1 > /sys/class/gpio/gpio$2/value
sleep 6
echo 0 > /sys/class/gpio/gpio$2/value
sleep 1
echo 1 > /sys/class/gpio/gpio$3/value
sleep 9
echo 0 > /sys/class/gpio/gpio$3/value
