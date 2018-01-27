package robot.parties;
import robot.*;

public class Jambes{
	TemporizedRelay haut;
	TemporizedRelay bas;
	
	public Jambes(TemporizedRelay haut, TemporizedRelay bas){
		this.haut=haut;
		this.bas=bas;
	}
	
	public TemporizedRelay getHaut(){
		return haut;
	}
	public TemporizedRelay getBas(){
		return bas;
	}

	//commandes de mouvement
	public void lever(){
		if(bas.getState()==0) haut.enableRelay();
	}
	public void baisser(){
		if(haut.getState()==0)bas.enableRelay();
	}
	public void stop(){
		haut.enableRelay(0);
		bas.enableRelay(0);
	}

}