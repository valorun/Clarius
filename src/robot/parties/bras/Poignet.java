package robot.parties.bras;
import robot.*;

public class Poignet{
	Relay gauche;
	Relay droite;
	
	public Poignet(Relay gauche, Relay droite){
		this.gauche=gauche;
		this.droite=droite;
	}
	public Poignet(String gauche, String droite){
		this.gauche=new Relay(gauche);
		this.droite=new Relay(droite);
	}
	
	public Relay getGauche(){
		return gauche;
	}
	public Relay getDroite(){
		return droite;
	}
	
	//commandes de mouvement
	public void tournerGauche(){
		if(droite.getState()==0) gauche.enableRelay();
	}
	public void tournerDroite(){
		if(gauche.getState()==0) droite.enableRelay();
	}
	public void stop(){
		gauche.enableRelay(0);
		droite.enableRelay(0);
	}
	
}