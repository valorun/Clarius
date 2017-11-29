#!/bin/bash 
#raise_arm.sh

python /home/pi/Scripts/enable_SPIrelay.py $1
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $1
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $2
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $2
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $3
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $3
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $4
sleep 1
python /home/pi/Scripts/enable_SPIrelay.py $4
