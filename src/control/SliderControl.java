package control;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import robot.Robot;

public class SliderControl implements ChangeListener{
	Robot robot;
	int valeur;
	public SliderControl(Robot robot){
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
			case "position":
			{
				if (value==0)robot.asseoir();
				if (value==1)robot.redresser();
				break;
			}
			case "bras gauche":
			{
				robot.getBrasGauche().getEpaule().lever_baisser(value);
				break;
			}
			case "bras droit":
			{
				robot.getBrasDroit().getEpaule().lever_baisser(value);
				break;
			}
			case "buste":
			{
				robot.getBuste().tourner(value);
				break;
			}
			case "camera horizontale":
			{
				robot.getCamera().tourner(value);
				break;
			}
			case "camera verticale":
			{
				robot.getCamera().incliner(value);
				break;
			}
			case "tete":
			{
				robot.getTete().tourner(value);
				break;
			}
			case "paupieres":
			{
				robot.getTete().ouverturePaupieres(value);
			}
			}
			source.setEnabled(true);
			valeur=value;
		}
	}

}