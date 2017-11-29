package robot.parties;
import robot.*;

public class Chassis{
	Relay haut;
	Relay bas;
	
	public Chassis(Relay haut, Relay bas){
		this.haut=haut;
		this.bas=bas;
	}
	public Chassis(String haut, String bas){
		this.haut=new Relay(haut);
		this.bas=new Relay(bas);
	}
	
	public Relay getHaut(){
		return haut;
	}
	public Relay getBas(){
		return bas;
	}
	
	//commandes de mouvement
	public void lever(){
		if(bas.getState()==0) haut.enableRelay();
	}
	public void baisser(){
		if(haut.getState()==0) bas.enableRelay();
	}
	public void stop(){
		haut.enableRelay(0);
		bas.enableRelay(0);
	}
	
}