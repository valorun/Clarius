package robot.parties.bras;
import robot.*;

public class Epaule{
	Relay haut;
	Relay bas;
	Relay exterieur;
	Relay interieur;
	
	public Epaule(Relay haut, Relay bas, Relay exterieur, Relay interieur){
		this.haut=haut;
		this.bas=bas;
		this.exterieur=exterieur;
		this.interieur=interieur;
	}
	public Epaule(String haut, String bas, String exterieur, String interieur){
		this.haut=new Relay(haut);
		this.bas=new Relay(bas);
		this.exterieur=new Relay(exterieur);
		this.interieur=new Relay(interieur);
	}
	
	public Relay getHaut(){
		return haut;
	}
	public Relay getBas(){
		return bas;
	}
	public Relay getExterieur(){
		return exterieur;
	}
	public Relay getInterieur(){
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
	
}