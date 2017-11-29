package robot.parties;
import robot.*;
import java.util.concurrent.TimeUnit;

public class Camera{
	TemporizedRelay haut;
	TemporizedRelay bas;
	TemporizedRelay gauche;
	TemporizedRelay droite;
	int positionX;
	int positionY;
	
	public Camera(TemporizedRelay haut, TemporizedRelay bas, TemporizedRelay gauche, TemporizedRelay droite){
		this.haut=haut;
		this.bas=bas;
		this.gauche=gauche;
		this.droite=droite;
		positionX=0;
		positionY=0;
	}
	
	public Relay getHaut(){
		return haut;
	}
	public Relay getBas(){
		return bas;
	}
	public Relay getGauche(){
		return gauche;
	}
	public Relay getDroite(){
		return droite;
	}
	//commandes de mouvement
	public void lever(){
		if(bas.getState()==0) haut.enableRelay();
	}
	public void baisser(){
		if(haut.getState()==0)bas.enableRelay();
	}
	public void tournerGauche(){
		if(droite.getState()==0)gauche.enableRelay();
	}
	public void tournerDroite(){
		if(gauche.getState()==0)droite.enableRelay();
	}
	public void stop(){
		haut.enableRelay(0);
		bas.enableRelay(0);
		gauche.enableRelay(0);
		droite.enableRelay(0);
	}
	public void tourner(int value) {
		TemporizedRelay relay = gauche;
        if (value - positionX < 0) relay = gauche;// si on va vers la gauche, c'est le relai 1 qu'on choisi
        if (value - positionX > 0) relay = droite;// sinon on choisis le relai 2        
        int D = relay.getTempo() * Math.abs(value - positionX);
        relay.enableRelayFor(D);// le delai est ici calculé en fonction de la durée D d'un pas de rotation et de la longueur du trajet.
        positionX = value;
        try {
        	TimeUnit.MILLISECONDS.sleep(D);
        }
        catch(InterruptedException e){}

	}

	public void incliner(int value) {
		TemporizedRelay relay = bas;
        if (value - positionY < 0) relay = bas;// si on va vers la gauche, c'est le relai 1 qu'on choisi
        if (value - positionY > 0) relay = haut;// sinon on choisis le relai 2        
        int D = relay.getTempo() * Math.abs(value - positionY);
        relay.enableRelayFor(D);// le delai est ici calculé en fonction de la durée D d'un pas de rotation et de la longueur du trajet.
        positionY = value;
        try {
        	TimeUnit.MILLISECONDS.sleep(D);
        }
        catch(InterruptedException e){}

	}

	
}