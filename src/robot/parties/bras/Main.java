package robot.parties.bras;
import robot.*;

public class Main{
	Relay fermer;
	Relay ouvrir;
	
	public Main(Relay fermer, Relay ouvrir){
		this.fermer=fermer;
		this.ouvrir=ouvrir;
	}
	public Main(String fermer, String ouvrir){
		this.fermer=new Relay(fermer);
		this.ouvrir=new Relay(ouvrir);
	}
	public Relay getOuvrir(){
		return ouvrir;
	}
	public Relay getFermer(){
		return fermer;
	}
	
	//commandes de mouvement
	public void fermer(){
		if(ouvrir.getState()==0) fermer.enableRelay();
	}
	public void ouvrir(){
		if(fermer.getState()==0) ouvrir.enableRelay();
	}
	public void stop(){
		fermer.enableRelay(0);
		ouvrir.enableRelay(0);
	}
	
}