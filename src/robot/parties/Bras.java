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

}