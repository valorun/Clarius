package robot.parties;
import robot.*;
import java.util.concurrent.TimeUnit;

public class Tete{
	TemporizedRelay gauche;
	TemporizedRelay droite;
	int position;
	
	public Tete(TemporizedRelay gauche, TemporizedRelay droite){
		this.gauche=gauche;
		this.droite=droite;
		position=0;
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
	public void tourner(int value) {
		TemporizedRelay relay = gauche;
        if (value - position < 0) relay = gauche;// si on va vers la gauche, c'est le relai 1 qu'on choisi
        if (value - position > 0) relay = droite;// sinon on choisis le relai 2        
        int D = relay.getTempo() * Math.abs(value - position);
        relay.enableRelayFor(D);// le delai est ici calculé en fonction de la durée D d'un pas de rotation et de la longueur du trajet.
        position = value;
        try {
        	TimeUnit.MILLISECONDS.sleep(D);
        }
        catch(InterruptedException e){}
	}
	
}