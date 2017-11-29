#!/bin/bash
#/set_pins_direction.sh

for pin in "$@"
do
        echo out > /sys/class/gpio/gpio$pin/direction
     
done

