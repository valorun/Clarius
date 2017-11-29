package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;

import robot.Robot;
import vue.UIConst;
public class DebugButtonControl implements ActionListener {
	Robot robot;

	public DebugButtonControl(Robot robot) {
		this.robot=robot;
	}
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		if(source.getBackground()!=UIConst.RED)source.setBackground(UIConst.RED);
		else if(source.getBackground()==UIConst.RED)source.setBackground(UIConst.GREEN);
		switch(name){
		case "jambes baisser":{
			robot.getJambes().baisser();
			break;
		}
		case "jambes lever":{
			robot.getJambes().lever();
			break;
		}

		case "chassis baisser":{
			robot.getChassis().baisser();
			break;
		}
		case "chassis lever":{
			robot.getChassis().lever();
			break;
		}
		case "buste rota gauche":{
			robot.getBuste().tournerGauche();
			break;
		}
		case "buste rota droite":{
			robot.getBuste().tournerDroite();
			break;
		}
		case "buste incli arriere":{
			robot.getBuste().inclinerArriere();
			break;
		}
		case "buste incli avant":{
			robot.getBuste().inclinerAvant();
			break;
		}
		case "tete rota gauche":{
			robot.getTete().tournerGauche();
			break;
		}
		case "tete rota droite":{
			robot.getTete().tournerDroite();
			break;
		}
		case "camera rota gauche":{
			robot.getCamera().tournerGauche();
			break;
		}
		case "camera rota droite":{
			robot.getCamera().tournerDroite();
			break;
		}
		case "camera lever":{
			robot.getCamera().lever();
			break;
		}
		case "camera baisser":{
			robot.getCamera().baisser();
			break;
		}

		//bras gauche
		case "epaule gauche ecarter":{
			robot.getBrasGauche().getEpaule().ecarter();
			break;
		}
		case "epaule gauche ramener":{
			robot.getBrasGauche().getEpaule().ramener();
			break;
		}
		case "epaule gauche baisser":{
			robot.getBrasGauche().getEpaule().baisser();
			break;
		}
		case "epaule gauche lever":{
			robot.getBrasGauche().getEpaule().lever();
			break;
		}
		case "avant-bras gauche baisser":{
			robot.getBrasGauche().getAvBras().baisser();
			break;
		}
		case "avant-bras gauche lever":{
			robot.getBrasGauche().getAvBras().lever();
			break;
		}
		case "poignet gauche rota gauche":{
			robot.getBrasGauche().getPoignet().tournerGauche();
			break;
		}
		case "poignet gauche rota droite":{
			robot.getBrasGauche().getPoignet().tournerDroite();
			break;
		}
		case "main gauche fermer":{
			robot.getBrasGauche().getMain().fermer();
			break;
		}
		case "main gauche ouvrir":{
			robot.getBrasGauche().getMain().ouvrir();
			break;
		}

		//bras droit
		case "epaule droit ecarter":{
			robot.getBrasDroit().getEpaule().ecarter();
			break;
		}
		case "epaule droit ramener":{
			robot.getBrasDroit().getEpaule().ramener();
			break;
		}
		case "epaule droit baisser":{
			robot.getBrasDroit().getEpaule().baisser();
			break;
		}
		case "epaule droit lever":{
			robot.getBrasDroit().getEpaule().lever();
			break;
		}
		case "avant-bras droit baisser":{
			robot.getBrasDroit().getAvBras().baisser();
			break;
		}
		case "avant-bras droit lever":{
			robot.getBrasDroit().getAvBras().lever();
			break;
		}
		case "poignet droit rota gauche":{
			robot.getBrasDroit().getPoignet().tournerGauche();
			break;
		}
		case "poignet droit rota droite":{
			robot.getBrasDroit().getPoignet().tournerDroite();
			break;
		}
		case "main droit fermer":{
			robot.getBrasDroit().getMain().fermer();
			break;
		}
		case "main droit ouvrir":{
			robot.getBrasDroit().getMain().ouvrir();
			break;
		}




		}
	}


}
