#!/bin/sh
#initialize_pins.sh

i=1
while [ "$i" -le 27 ]; do
        gpio export $i out
        i=$((i+1))
done
exit 0

