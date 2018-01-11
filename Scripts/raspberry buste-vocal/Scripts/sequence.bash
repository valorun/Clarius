#!/bin/bash 
#/home/pi/Scripts/sequence.bash
#on envoie dabord en argument le pin puis le temps d'allumage entre les pins
SCRIPTS_LOCATION="/home/pi/Scripts"
args=("$@") 
for (( c=0; c<"$#"; c+=2 )) 
do
    if [ "${args[c]}" = "x" ]
    then
		tempo=$(echo "scale=3;$args[$c+1] / 1000" | bc -l)
	    sleep ${tempo}s
    else
		tempo=$(echo "scale=3;$args[$c+1] / 1000" | bc -l)
        eval $SCRIPTS_LOCATION/Scripts/enable_relay.bash ${args[$c]} 1
        #./enable_relay.bash ${args[$c]} 1
        sleep ${tempo}s
        eval $SCRIPTS_LOCATION/Scripts/enable_relay.bash ${args[$c]} 0
        #./enable_relay.bash ${args[$c]} 0
    fi
done
