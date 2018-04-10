package robot.parties.bras;
import robot.*;

public class Main{
	TemporizedRelay fermer;
	TemporizedRelay ouvrir;
	
	public Main(TemporizedRelay fermer, TemporizedRelay ouvrir){
		this.fermer=fermer;
		this.ouvrir=ouvrir;
	}
	public TemporizedRelay getOuvrir(){
		return ouvrir;
	}
	public TemporizedRelay getFermer(){
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