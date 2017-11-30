#!/bin/bash 
#/home/pi/Scripts/sequence.bash
#on envoie dabord en argument le pin puis le temps d'allumage entre les pins
SCRIPTS_LOCATION="~/Scripts"
args=("$@") 
for (( c=0; c<"$#"; c+=2 )) 
do
    if [ "${args[c]}" = "x" ]
    then
	    sleep ${args[$c+1]}s
    else
        eval $SCRIPTS_LOCATION/Scripts/enable_relay.bash ${args[$c]} 1
        #./enable_relay.bash ${args[$c]} 1
        sleep ${args[$c+1]}s
        eval $SCRIPTS_LOCATION/Scripts/enable_relay.bash ${args[$c]} 0
        #./enable_relay.bash ${args[$c]} 0
    fi
done
