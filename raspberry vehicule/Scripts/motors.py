#/home/pi/Scripts/motors.py
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
