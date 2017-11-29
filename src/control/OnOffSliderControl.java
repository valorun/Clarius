package control;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import robot.Robot;
import vue.Application;

public class OnOffSliderControl implements ChangeListener{
	Application app;
	Robot robot;
	int valeur;
	public OnOffSliderControl(Application app, Robot robot){
		this.app=app;
		this.robot=robot;
		valeur=0;
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		int value = source.getValue();
		if(value!=valeur) {
			if (value==1) {
				System.out.println(">>>Robot ON");
				robot.getOnOffCartes().enableRelay(1);
			}
			else {
				System.out.println(">>>Robot OFF");
				robot.getOnOffCartes().enableRelay(0);
			}
			app.updatePanels();
		}
		valeur=value;
	}

}