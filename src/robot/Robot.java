package robot;
import robot.parties.*;
import robot.parties.bras.AvantBras;
import robot.parties.bras.Epaule;
import robot.parties.bras.Main;
import robot.parties.bras.Poignet;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
/**
* Classe permettant d'instancier l'abstraction du robot. Cette lasse fournit les méthodes pour obtenir chaques parties du robot, mais aussi lié chaques relais à un pin.
* @author Ronan COLLIER
* @version 1.0
*/
public class Robot{
	
	boolean robotOn;
	private HashMap<String, Relay> relaysMap;

	Bras brasDroit;
	Bras brasGauche;
	Tete tete;
	Jambes jambes;
	Chassis chassis;
	Buste buste;
	Camera camera;
	Chariot chariot;
	Relay eclairage;
	Relay lumiereYeux;
	Relay onOffCartes;

	/**
     	* Constructeur de la classe Robot.
     	* @param relaysMap représente la liste des pins du raspberry avec le nom du relais associés.
     	*/
	public Robot(HashMap<String, Relay> relaysMap){
		robotOn=false;
		this.relaysMap=relaysMap;
		createParts();
	}
	/**
	 * Méthode permettant d'instancier chaques parties du robot à partir de la hashmap
	 */
	private void createParts() {
		eclairage=relaysMap.get("eclairage");
		lumiereYeux=relaysMap.get("lumiereYeux");
		onOffCartes=relaysMap.get("onOffCartes");
		
		Epaule epauleG=new Epaule(relaysMap.get("brasFrontalG-lever"), relaysMap.get("brasFrontalG-baisser"), relaysMap.get("brasLateralG-ecarter"), relaysMap.get("brasLateralG-ramener"));
		AvantBras avBrasG=new AvantBras(relaysMap.get("avantBrasG-lever"), relaysMap.get("avantBrasG-baisser"));
		Poignet poignetG=new Poignet(relaysMap.get("poignetG-exterieur"), relaysMap.get("poignetG-interieur"));
		Main mainG=new Main(relaysMap.get("mainG-fermer"), relaysMap.get("mainG-ouvrir"));
		brasGauche=new Bras(epauleG,avBrasG,poignetG,mainG);

		Epaule epauleD=new Epaule(relaysMap.get("brasFrontalD-lever"), relaysMap.get("brasFrontalD-baisser"), relaysMap.get("brasLateralD-ecarter"), relaysMap.get("brasLateralD-ramener"));
		AvantBras avBrasD=new AvantBras(relaysMap.get("avantBrasD-lever"), relaysMap.get("avantBrasD-baisser"));
		Poignet poignetD=new Poignet(relaysMap.get("poignetD-interieur"), relaysMap.get("poignetD-exterieur"));
		Main mainD=new Main(relaysMap.get("mainD-fermer"), relaysMap.get("mainD-ouvrir"));
		brasDroit=new Bras(epauleD,avBrasD,poignetD,mainD);

		tete=new Tete((TemporizedRelay) relaysMap.get("tete-gauche"),(TemporizedRelay) relaysMap.get("tete-droite"));
		jambes=new Jambes(relaysMap.get("jambes-haut"), relaysMap.get("jambes-bas"));
		chassis=new Chassis(relaysMap.get("chassis-haut"), relaysMap.get("chassis-bas"));
		buste= new Buste(relaysMap.get("busteIncli-avant"), relaysMap.get("busteIncli-arriere"), relaysMap.get("busteRota-gauche"), relaysMap.get("busteRota-droite"));
		camera= new Camera((TemporizedRelay)relaysMap.get("cameraVert-haut"),(TemporizedRelay) relaysMap.get("cameraVert-bas"), (TemporizedRelay)relaysMap.get("cameraHori-gauche"), (TemporizedRelay)relaysMap.get("cameraHori-droite"));
		chariot=new Chariot();
	}
	/**
	 * Méthode permettant d'obtenir le bras droit du robot.
	 * @return Le bras droit du robot.
	 */
	public Bras getBrasDroit(){
		return brasDroit;
	}
	/**
	 * Méthode permettant d'obtenir le bras gauche du robot.
	 * @return Le bras gauche du robot.
	 */
	public Bras getBrasGauche(){
		return brasGauche;
	}
	/**
	 * Méthode permettant d'obtenir la tête du robot.
	 * @return La tête du robot.
	 */
	public Tete getTete(){
		return tete;
	}
	/**
	 * Méthode permettant d'obtenir les jambes du robot.
	 * @return Les jambes du robot.
	 */
	public Jambes getJambes(){
		return jambes;
	}
	/**
	 * Méthode permettant d'obtenir le chassis du robot.
	 * @return Le chassis du robot.
	 */
	public Chassis getChassis(){
		return chassis;
	}
	/**
	 * Méthode permettant d'obtenir le buste du robot.
	 * @return Le buste du robot.
	 */
	public Buste getBuste(){
		return buste;
	}
	/**
	 * Méthode permettant d'obtenir la camera du robot.
	 * @return La camera du robot.
	 */
	public Camera getCamera(){
		return camera;
	}
	/**
	 * Méthode permettant d'obtenir le relai d'éclairage du robot.
	 * @return Le relai d'éclairage du robot.
	 */
	public Relay getEclairage(){
		return eclairage;
	}
	/**
	 * Méthode permettant d'obtenir le relai d'allumage des yeux du robot.
	 * @return Le relai d'allumage des yeux du robot.
	 */
	public Relay getLumiereYeux(){
		return lumiereYeux;
	}
	/**
	 * Méthode permettant d'obtenir le le relai d'allumage du robot.
	 * @return Le relai d'allumage du robot.
	 */
	public Relay getOnOffCartes(){
		return onOffCartes;
	}
	/**
	 * Méthode permettant d'obtenir le chariot du robot.
	 * @return Le chariot du robot.
	 */
	public Chariot getChariot(){
		return chariot;
	}

	/**
	 * Méthode permettant d'allumer le robot.
	 */
	public void onOff(){
		if(robotOn==false){
			onOffCartes.enableRelay(1);               
			robotOn = true;
		}
		else if(robotOn==true){
			onOffCartes.enableRelay(0);//extinction de l'alimentation par défaut
			eclairage.enableRelay(0);//extinction de l'éclairage par défaut
			lumiereYeux.enableRelay(0);//extinction des lumières des yeux par défaut
			robotOn = false;
		}

	}
	
	/**
	 * Méthode permettant d'executer la séquence de redressage du robot.
	 */
	public void redresser(){
		try{
			Sequence seq=new Sequence();
			seq.addRelay( chassis.getHaut() , 8);
			seq.addRelay( "x", 1);
			seq.addRelay( buste.getAvant(), 6);
			seq.addRelay( "x", 1);
			seq.addRelay( jambes.getHaut(), 8);
			seq.execute();
			TimeUnit.MILLISECONDS.sleep(seq.getTimeTotal());
		}
		catch(InterruptedException e){}
	}
	/**
	 * Méthode permettant d'executer la séquence pour asseoir le robot.
	 */
	public void asseoir(){
		try{
			Sequence seq=new Sequence();
			seq.addRelay( jambes.getBas() , 8);
			seq.addRelay( "x", 1);
			seq.addRelay( buste.getArriere(), 6);
			seq.addRelay( "x", 1);
			seq.addRelay( chassis.getHaut(), 9);
			seq.execute();
			TimeUnit.MILLISECONDS.sleep(seq.getTimeTotal());
		}
		catch(InterruptedException e){}
	}

}
