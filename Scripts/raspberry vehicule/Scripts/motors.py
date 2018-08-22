#/home/pi/Scripts/motors.py
# Script permettant de faire fonctionner les moteurs du chariot à l'aide de la carte Sabertooth 2x32. On passe en argument la vitesse du premier moteur puis celle du second moteur (une valeur entre 0 et 2047)
# Le montage pour contrôler la carte est le suivant:
#-relier le S1 sur le TX du raspberry (envoi)
#-relier le S2 sur le RX du raspberry (réception)
#-relier le 0V sur un GND du raspberry
import wiringpi, sys
m1Speed = sys.argv[1]
m2Speed = sys.argv[2]
wiringpi.wiringPiSetup()
serial = wiringpi.serialOpen('/dev/serial0',9600)
wiringpi.serialPuts(serial,'M1: '+ m1Speed +'\r\n')
wiringpi.serialPuts(serial,'M2: '+ m2Speed +'\r\n')
#wiringpi.serialClose(serial)

#file = open("/home/pi/testfile.txt","a")
#file.write("commande >")
#file.write(sys.argv[1])
#file.write(" ")
#file.write(sys.argv[2])
#file.write("\n")
#file.close()
