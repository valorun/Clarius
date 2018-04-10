package control;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import robot.Robot;

public class SliderDebugControl implements ChangeListener{
	Robot robot;
	int valeur;
	public SliderDebugControl(Robot robot){
		this.robot=robot;
		valeur=0;
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		String name = source.getName();
		int value = source.getValue();

		if(value!=valeur) {
			System.out.println(name+" a bougé");
			source.setEnabled(false);
			switch(name){
			case "jambes":
			{
				if (value==-1)robot.getJambes().baisser();
				if (value==0)robot.getJambes().stop();
				if (value==1)robot.getJambes().lever();
				break;
			}
			case "chassis":
			{
				if (value==-1)robot.getChassis().baisser();
				if (value==0)robot.getChassis().stop();
				if (value==1)robot.getChassis().lever();
				break;
			}
			case "buste rotation":
			{
				if (value==-1)robot.getBuste().tournerGauche();
				if (value==0)robot.getBuste().stop();
				if (value==1)robot.getBuste().tournerDroite();
				break;
			}
			case "buste inclinaison":
			{
				if (value==-1)robot.getBuste().inclinerArriere();
				if (value==0)robot.getBuste().stop();
				if (value==1)robot.getBuste().inclinerAvant();
				break;
			}
			case "t�te":
			{
				if (value==-1)robot.getTete().tournerGauche();
				if (value==0)robot.getTete().stop();
				if (value==1)robot.getTete().tournerDroite();
				break;
			}
			case "caméra horizontale":
			{
				if (value==-1)robot.getCamera().tournerGauche();
				if (value==0)robot.getCamera().stop();
				if (value==1)robot.getCamera().tournerDroite();
				break;
			}
			case "caméra verticale":
			{
				if (value==-1)robot.getCamera().baisser();
				if (value==0)robot.getCamera().stop();
				if (value==1)robot.getCamera().lever();
				break;
			}
			//bras gauche
			case "épaule gauche écart":
			{
				if (value==-1)robot.getBrasGauche().getEpaule().ecarter();
				if (value==0)robot.getBrasGauche().getEpaule().stop();
				if (value==1)robot.getBrasGauche().getEpaule().ramener();
				break;
			}
			case "épaule gauche hauteur":
			{
				if (value==-1)robot.getBrasGauche().getEpaule().baisser();
				if (value==0)robot.getBrasGauche().getEpaule().stop();
				if (value==1)robot.getBrasGauche().getEpaule().lever();
				break;
			}
			case "avant-bras gauche":
			{
				if (value==-1)robot.getBrasGauche().getAvBras().baisser();
				if (value==0)robot.getBrasGauche().getAvBras().stop();
				if (value==1)robot.getBrasGauche().getAvBras().lever();
				break;
			}
			case "poignet gauche":
			{
				if (value==-1)robot.getBrasGauche().getPoignet().tournerDroite();
				if (value==0)robot.getBrasGauche().getPoignet().stop();
				if (value==1)robot.getBrasGauche().getPoignet().tournerGauche();
				break;
			}
			case "main gauche":
			{
				if (value==-1)robot.getBrasGauche().getMain().fermer();
				if (value==0)robot.getBrasGauche().getMain().stop();
				if (value==1)robot.getBrasGauche().getMain().ouvrir();
				break;
			}
			//bras droit
			case "épaule droite écart":
			{
				if (value==-1)robot.getBrasDroit().getEpaule().ramener();
				if (value==0)robot.getBrasDroit().getEpaule().stop();
				if (value==1)robot.getBrasDroit().getEpaule().ecarter();
				break;
			}
			case "épaule droite hauteur":
			{
				if (value==-1)robot.getBrasDroit().getEpaule().baisser();
				if (value==0)robot.getBrasDroit().getEpaule().stop();
				if (value==1)robot.getBrasDroit().getEpaule().lever();
				break;
			}
			case "avant-bras droit":
			{
				if (value==-1)robot.getBrasDroit().getAvBras().baisser();
				if (value==0)robot.getBrasDroit().getAvBras().stop();
				if (value==1)robot.getBrasDroit().getAvBras().lever();
				break;
			}
			case "poignet droit":
			{
				if (value==-1)robot.getBrasDroit().getPoignet().tournerGauche();
				if (value==0)robot.getBrasDroit().getPoignet().stop();
				if (value==1)robot.getBrasDroit().getPoignet().tournerDroite();
				break;
			}
			case "main droite":
			{
				if (value==-1)robot.getBrasDroit().getMain().fermer();
				if (value==0)robot.getBrasDroit().getMain().stop();
				if (value==1)robot.getBrasDroit().getMain().ouvrir();
				break;
			}	
			}
			source.setEnabled(true);
			valeur=value;
		}
	}

}