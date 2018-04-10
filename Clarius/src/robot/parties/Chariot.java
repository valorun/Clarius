package robot.parties;
import robot.Configuration;

public class Chariot{
		public Chariot(){}

		public void bougerAvant(int vitesse){
			move(vitesse, vitesse);
        }
        public void bougerArriere(int vitesse){
        	move(-vitesse, -vitesse);
        }
        public void tournerGauche(int vitesse){
        	move(0, (int)Math.round(vitesse));
        }
        public void tournerDroite(int vitesse){
        	move((int)Math.round(vitesse), 0);
        }
        public void tournerArriereGauche(int vitesse){
        	move(0, -(int)Math.round(vitesse));
        }
        public void tournerArriereDroite(int vitesse){
        	move(-(int)Math.round(vitesse), 0);
        }
        public void stop(){
        	move(0, 0);
        }
        public void move(int vM1, int vM2) {
        	Configuration.rasPiChar.sendCommand("sudo python /home/pi/Scripts/motors.py "+vM1+" "+vM2);
        }

	
}