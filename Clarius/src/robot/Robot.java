package robot;
import robot.parties.*;
import robot.parties.bras.AvantBras;
import robot.parties.bras.Epaule;
import robot.parties.bras.Main;
import robot.parties.bras.Poignet;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
/**
* Classe permettant d'instancier l'abstraction du robot. Cette classe fournit les méthodes pour obtenir chaque partie du robot, mais aussi lier chaques relai à un pin.
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
	Relay onOffCartes;

	/**
     	* Constructeur de la classe Robot.
     	* @param relaysMap représente la liste des pins du raspberry avec le nom du relai associé. 
     	*/
	public Robot(HashMap<String, Relay> relaysMap){
		robotOn=false;
		this.relaysMap=relaysMap;
		createParts();
	}
	/**
	 * Méthode permettant d'instancier chaque partie du robot à partir de la hashmap. 
	 */
	private void createParts() {
		eclairage=relaysMap.get("eclairage");
		onOffCartes=relaysMap.get("onOffCartes");
		
		Epaule epauleG=new Epaule((TemporizedRelay)relaysMap.get("brasFrontalG-lever"), (TemporizedRelay)relaysMap.get("brasFrontalG-baisser"), (TemporizedRelay)relaysMap.get("brasLateralG-ecarter"), (TemporizedRelay)relaysMap.get("brasLateralG-ramener"));
		AvantBras avBrasG=new AvantBras((TemporizedRelay)relaysMap.get("avantBrasG-lever"), (TemporizedRelay)relaysMap.get("avantBrasG-baisser"));
		Poignet poignetG=new Poignet((TemporizedRelay)relaysMap.get("poignetG-exterieur"), (TemporizedRelay)relaysMap.get("poignetG-interieur"));
		Main mainG=new Main( (TemporizedRelay)relaysMap.get("mainG-fermer"), (TemporizedRelay)relaysMap.get("mainG-ouvrir"));
		brasGauche=new Bras(epauleG,avBrasG,poignetG,mainG);

		Epaule epauleD=new Epaule((TemporizedRelay)relaysMap.get("brasFrontalD-lever"), (TemporizedRelay)relaysMap.get("brasFrontalD-baisser"), (TemporizedRelay)relaysMap.get("brasLateralD-ecarter"), (TemporizedRelay)relaysMap.get("brasLateralD-ramener"));
		AvantBras avBrasD=new AvantBras((TemporizedRelay)relaysMap.get("avantBrasD-lever"), (TemporizedRelay)relaysMap.get("avantBrasD-baisser"));
		Poignet poignetD=new Poignet((TemporizedRelay)relaysMap.get("poignetD-interieur"), (TemporizedRelay)relaysMap.get("poignetD-exterieur"));
		Main mainD=new Main( (TemporizedRelay)relaysMap.get("mainD-fermer"), (TemporizedRelay)relaysMap.get("mainD-ouvrir"));
		brasDroit=new Bras(epauleD,avBrasD,poignetD,mainD);

		tete=new Tete((TemporizedRelay) relaysMap.get("tete-gauche"),(TemporizedRelay) relaysMap.get("tete-droite"), relaysMap.get("lumiereYeux"), (TemporizedRelay)relaysMap.get("paupieres-ouvrir"), (TemporizedRelay)relaysMap.get("paupieres-fermer"));
		jambes=new Jambes((TemporizedRelay)relaysMap.get("jambes-haut"), (TemporizedRelay)relaysMap.get("jambes-bas"));
		chassis=new Chassis((TemporizedRelay)relaysMap.get("chassis-haut"), (TemporizedRelay)relaysMap.get("chassis-bas"));
		buste= new Buste((TemporizedRelay)relaysMap.get("busteIncli-avant"), (TemporizedRelay) relaysMap.get("busteIncli-arriere"), (TemporizedRelay) relaysMap.get("busteRota-gauche"), (TemporizedRelay) relaysMap.get("busteRota-droite"));
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
			getTete().getLumiereYeux().enableRelay(0);//extinction des lumières des yeux par défaut
			robotOn = false;
		}

	}
	
	/**
	 * Méthode permettant d'exécuter la séquence de redressage du robot.
	 */
	public void redresser(){
		try{
			Sequence seq=new Sequence();
			seq.addRelay( chassis.getHaut() , chassis.getHaut().getTempo());
			seq.addRelay( "x", 100);
			seq.addRelay( buste.getAvant(), buste.getAvant().getTempo());
			seq.addRelay( "x", 100);
			seq.addRelay( jambes.getHaut(), jambes.getHaut().getTempo());
			seq.execute();
			TimeUnit.MILLISECONDS.sleep(seq.getTimeTotal());
		}
		catch(InterruptedException e){}
	}
	/**
	 * Méthode permettant d'exécuter la séquence pour asseoir le robot.
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
