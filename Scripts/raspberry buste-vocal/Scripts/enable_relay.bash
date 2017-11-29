#!/bin/bash
#/home/pi/Scripts/enable_relay.bash
SCRIPTS_LOCATION="/home/pi/Scripts"
CONFIG_FILE="$SCRIPTS_LOCATION/config.txt"
function getPin {
        grep -w "${1}.pin" $CONFIG_FILE | cut -d "=" -f 2
}
function getParity {
        echo "$1" | cut -d "-" -f 1
}
function getPairPin {
        #On récupère le pin du relai paire du relai donné en argument
        #Pour cela on récupère les deux pins avec la même parité dans le fichier de config. 
        #Puis on récupère que les infos ne correspondant pas au relai donné en ragument.
        #Et enfin, on récupère seulement la partie affichant le pin du relai paire
        pairPin=$(grep -w "$(getParity $1)" config.txt | grep -vw "$1" | grep ".pin" | cut -d "=" -f 2)
        pairPinCount=$(printf $pairPin | wc -l)
        if [ $pairPinCount -eq 0 ] #On vérifie si le relay indiqué est bien appairé
        then
            echo "none"
        else
            echo $pairPin
        fi
}

function canBeEnabled {
        pairPin=$(getPairPin $1)
		value=$2
        if [ $value -eq 1 ] ; then
            if [ "$pairPin" == "none" ] #si le pin n'est pas appairé
            then
                echo "true"
            else #si le pin est appairé
                if [[ "${pairPin:0:1}" = [a-bA-B] ]] ; then #si il s'agit d'un pin du shield SPI
                    pairPinState=$(python $SCRIPTS_LOCATION/read_SPI_pin.py $pairPin)
                    #pairPinState=0
                else
                    pairPinState=$(cat "/sys/class/gpio/gpio${pairPin}/value")
                    #pairPinState=0
                fi
                if [ "$pairPinState" == "0" ] ; then
                    echo "true"
                else
                    echo "false"
                fi
            fi
        else #si on veut mettre le pin à 0
            echo "true"
        fi
}
function changeRelayState {
        pin="$(getPin $1)"
        value=$2
        if [ "$(canBeEnabled $1 $2)" == "true" ] ; then
            if [[ "${pin:0:1}" = [a-bA-B] ]] ; then #si il s'agit d'un pin du shield SPI
                python $SCRIPTS_LOCATION/write_SPI_pin.py $pin $value
                #echo "$pin $value"
            else
                echo $value > /sys/class/gpio/gpio${pin}/value
                #echo "$pin $value"
            fi
        fi
}
changeRelayState $1 $2
#echo $(getPairPin $1)
