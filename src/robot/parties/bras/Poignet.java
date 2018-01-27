package robot.parties.bras;
import robot.*;

public class Poignet{
	TemporizedRelay gauche;
	TemporizedRelay droite;
	
	public Poignet(TemporizedRelay gauche, TemporizedRelay droite){
		this.gauche=gauche;
		this.droite=droite;
	}
	
	public TemporizedRelay getGauche(){
		return gauche;
	}
	public TemporizedRelay getDroite(){
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