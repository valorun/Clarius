#!/bin/bash
#/home/pi/Scripts/sequenceYana.bash
SCRIPTS_LOCATION="~/Scripts"
CONFIG_FILE="config.txt"
# Script permettant à Yana d'éxecuter une sequence avec une temporisation préalablement fixée dans le fichier de config.

# Fonction permettant d'obtenir la temporisation associée au relai donné en argument.
function getTemp {
        tempo="$(grep -w "${1}.tempo" "~/Scripts/$CONFIG_FILE" | cut -d "=" -f 2)"
        if [ $(echo $tempo | wc -l) -ne 0 ] ; then # on vérifie si une temporisation existe
            echo $tempo
        else
            echo "none"
        fi
}
if [ $(getTemp $1) != "none" ] ; then
	#echo "$1 $(getTemp $1)"
     eval $SCRIPTS_LOCATION/Scripts/sequence.bash "$1" "$(getTemp $1)"
else
    echo "le relai n'a pas de temporisation associée"
fi
