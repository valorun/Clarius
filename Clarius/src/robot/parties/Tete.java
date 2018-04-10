package robot.parties;
import robot.*;
import java.util.concurrent.TimeUnit;

public class Tete{
	TemporizedRelay gauche;
	TemporizedRelay droite;
	TemporizedRelay paupieresOuvrir;
	TemporizedRelay paupieresFermer;
	Relay lumiereYeux;
	int position;
	
	public Tete(TemporizedRelay gauche, TemporizedRelay droite, Relay lumiereYeux, TemporizedRelay paupieresOuvrir, TemporizedRelay paupieresFermer){
		this.gauche=gauche;
		this.droite=droite;
		this.lumiereYeux=lumiereYeux;
		this.paupieresOuvrir=paupieresOuvrir;
		this.paupieresFermer=paupieresFermer;
		position=0;
	}

	public Relay getGauche(){
		return gauche;
	}
	public Relay getDroite(){
		return droite;
	}
	/**
	 * Méthode permettant d'obtenir le relai d'allumage des yeux du robot.
	 * @return Le relai d'allumage des yeux du robot.
	 */
	public Relay getLumiereYeux(){
		return lumiereYeux;
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
	}
	public void ouverturePaupieres(int value) {
		TemporizedRelay relay = gauche;
        if (value - position < 0) relay = paupieresFermer;// si on va vers le bas, c'est le relai 1 qu'on choisi
        if (value - position > 0) relay = paupieresOuvrir;// sinon on choisis le relai 2        
        int D = relay.getTempo() * Math.abs(value - position);
        relay.enableRelayFor(D);// le delai est ici calculé en fonction de la durée D d'un pas de rotation et de la longueur du trajet.
        position = value;
	}
	
}