package robot.parties;
import robot.*;
import java.util.concurrent.TimeUnit;

public class Buste{
	Relay avant;
	Relay arriere;
	Relay gauche;
	Relay droite;
	int position;
	static int tempsPasRota=2000;//temps pour un pas de rotation
	
	public Buste(Relay avant, Relay arriere, Relay gauche, Relay droite){
		this.avant=avant;
		this.arriere=arriere;
		this.gauche=gauche;
		this.droite=droite;
		position=0;
	}
	public Buste(String avant, String arriere, String gauche, String droite){
		this.avant=new Relay(avant);
		this.arriere=new Relay(arriere);
		this.gauche=new Relay(gauche);
		this.droite=new Relay(droite);
		position=0;
	}
	
	public Relay getGauche(){
		return gauche;
	}
	public Relay getDroite(){
		return droite;
	}
	public Relay getAvant(){
		return avant;
	}
	public Relay getArriere(){
		return arriere;
	}
	
	//commandes de mouvement
	public void tournerGauche(){
		if(droite.getState()==0) gauche.enableRelay();
	}
	public void tournerDroite(){
		if(gauche.getState()==0) droite.enableRelay();
	}
	public void inclinerAvant(){
		if(arriere.getState()==0)avant.enableRelay();
	}
	public void inclinerArriere(){
		if(avant.getState()==0)arriere.enableRelay();
	}
	public void stop(){
		avant.enableRelay(0);
		arriere.enableRelay(0);
		gauche.enableRelay(0);
		droite.enableRelay(0);
	}
	
	public void tourner(int value) {
		Relay relay = gauche;
        if (value - position < 0) relay = gauche;// si on va vers la gauche, c'est le relai 1 qu'on choisi
        if (value - position > 0) relay = droite;// sinon on choisis le relai 2        
        int D = tempsPasRota * (int)(Math.abs(value - position));
        System.out.println(Math.abs(value - position));
        relay.enableRelayFor(D);// le delai est ici calculé en fonction de la durée D d'un pas de rotation et de la longueur du trajet.
        position = value;
        try {
        	TimeUnit.MILLISECONDS.sleep(D);
        }
        catch(Exception e){}

	}

	
}