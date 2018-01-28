package robot.parties.bras;
import robot.*;

public class Epaule{
	TemporizedRelay haut;
	TemporizedRelay bas;
	TemporizedRelay exterieur;
	TemporizedRelay interieur;
	int positionY;
	public Epaule(TemporizedRelay haut, TemporizedRelay bas, TemporizedRelay exterieur, TemporizedRelay interieur){
		this.haut=haut;
		this.bas=bas;
		this.exterieur=exterieur;
		this.interieur=interieur;
	}
	
	public TemporizedRelay getHaut(){
		return haut;
	}
	public TemporizedRelay getBas(){
		return bas;
	}
	public TemporizedRelay getExterieur(){
		return exterieur;
	}
	public TemporizedRelay getInterieur(){
		return interieur;
	}
	//commandes de mouvement
	public void lever(){
		if(bas.getState()==0) haut.enableRelay();
	}
	public void baisser(){
		if(haut.getState()==0)bas.enableRelay();
	}
	public void ecarter(){
		if(interieur.getState()==0)exterieur.enableRelay();
	}
	public void ramener(){
		if(exterieur.getState()==0)interieur.enableRelay();
	}
	public void stop(){
		haut.enableRelay(0);
		bas.enableRelay(0);
		exterieur.enableRelay(0);
		interieur.enableRelay(0);
	}
	public void lever_baisser(int value) {
		TemporizedRelay relay = bas;
        if (value - positionY < 0) relay = bas;// si on va vers la gauche, c'est le relai 1 qu'on choisi
        if (value - positionY > 0) relay = haut;// sinon on choisis le relai 2        
        int D = relay.getTempo() * Math.abs(value - positionY);
        relay.enableRelayFor(D);// le delai est ici calculé en fonction de la durée D d'un pas de rotation et de la longueur du trajet.
        positionY = value;
	}
	
}