#!/bin/bash 
#/home/pi/Scripts/sequence.bash
#on envoie dabord en argument le pin puis le temps d'allumage entre les pins
args=("$@") 
for (( c=0; c<"$#"; c+=2 )) 
do
  if [[ "${args[c]:0:1}" = [a-b] ]] ; then
    python /home/pi/Scripts/enable_SPIrelay.py ${args[$c]}
    echo "SPI"
    sleep ${args[$c+1]}s
    python /home/pi/Scripts/enable_SPIrelay.py ${args[$c]} 
  else
    if [ "${args[c]}" = "x" ]
      then
	sleep ${args[$c+1]}s
    else
    	echo 1 > /sys/class/gpio/gpio${args[$c]}/value
    	sleep ${args[$c+1]}s
    	echo 0 > /sys/class/gpio/gpio${args[$c]}/value 
    fi
  fi 
done
