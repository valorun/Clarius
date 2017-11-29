#!/bin/bash
function getPin {
        grep -w "${1}.pin" /home/pi/Scripts/config.txt | cut -d "=" -f 2
}
function getTemp {
        grep -w "${1}.tempo" /home/pi/Scripts/config.txt | cut -d "=" -f 2
}
function enableRelay {
        echo $1 $(echo "scale=3;$2 / 1000" | bc -l)
        #sudo /home/pi/Scripts/sequence.bash $1 $(echo "scale=3;$2 / 1000" | bc -l)
}
enableRelay "$(getPin $1)" "$(getTemp $1)"
