#!/bin/sh
#initialize_pins.sh
# script permettant d'initialiser tous les pins du raspberry
i=1
while [ "$i" -le 27 ]; do
        gpio export $i out
        i=$((i+1))
done
exit 0

