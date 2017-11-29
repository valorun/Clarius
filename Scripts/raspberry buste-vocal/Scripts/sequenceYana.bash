#!/bin/bash
#/home/pi/Scripts/sequenceYana.bash
CONFIG_FILE="config.txt"
function getTemp {
        tempo="$(grep -w "${1}.tempo" $CONFIG_FILE | cut -d "=" -f 2)"
        if [ $(echo $tempo | wc -l) -ne 0 ] ; then # on vérifie si une temporisation existe
            echo "scale=3;$tempo / 1000" | bc -l
        else
            echo "none"
        fi
}
if [ $(getTemp $1) != "none" ] ; then
	#echo "$1 $(getTemp $1)"
    ./sequence.bash "$1" "$(getTemp $1)"
else
    echo "le relai n'a pas de temporisation associée"
fi
