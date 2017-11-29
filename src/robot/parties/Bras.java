package robot.parties;
import robot.parties.bras.*;
import robot.Sequence;

import java.util.concurrent.TimeUnit;

public class Bras{
	Epaule epaule;
	AvantBras avBras;
	Poignet poignet;
	Main main;

	public Bras(Epaule epaule, 
			AvantBras avBras, 
			Poignet poignet, 
			Main main){
		this.epaule=epaule;
		this.avBras=avBras;
		this.poignet=poignet;
		this.main=main;
	}

	public Epaule getEpaule(){
		return epaule;
	}
	public AvantBras getAvBras(){
		return avBras;
	}
	public Poignet getPoignet(){
		return poignet;
	}
	public Main getMain(){
		return main;
	}
	public void lever(){
		try{
			Sequence seq=new Sequence();
			seq.addRelay( epaule.getExterieur(), 10);
			seq.addRelay( epaule.getHaut(), 10);
			seq.addRelay( avBras.getHaut(), 10);
			seq.execute();
			TimeUnit.MILLISECONDS.sleep(seq.getTimeTotal());
		}
		catch(InterruptedException e){}
	}
	public void baisser(){
		try{
			Sequence seq=new Sequence();
			seq.addRelay( epaule.getInterieur(), 10);
			seq.addRelay( epaule.getBas(), 10);
			seq.addRelay( avBras.getBas(), 10);
			seq.execute();
			TimeUnit.MILLISECONDS.sleep(seq.getTimeTotal());
		}
		catch(InterruptedException e){}
	}

}