#!/bin/bash
#initialize_pins.sh

for pin in "$@"
do
	echo $pin > /sys/class/gpio/export
	echo out > /sys/class/gpio/gpio${pin}/direction
done
