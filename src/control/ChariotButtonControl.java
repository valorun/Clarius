package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JSlider;

import robot.Robot;

public class ChariotButtonControl implements MouseListener {

	Robot robot;
	JSlider vitesseSlider;
	public ChariotButtonControl(Robot robot, JSlider vitesseSlider) {
		this.robot=robot;
		this.vitesseSlider=vitesseSlider;
	}
	public void mousePressed(MouseEvent e) {
    	JButton source = (JButton)e.getSource();
	    if (e.getButton() == MouseEvent.BUTTON1 && source.isEnabled()==true) {
	    	//JButton source = (JButton)e.getSource();
			String name = source.getName();
			if (name=="Avant") robot.getChariot().bougerAvant(vitesseSlider.getValue());
			else if (name=="Arrière")robot.getChariot().bougerArriere(vitesseSlider.getValue());
			else if (name=="Avant gauche")robot.getChariot().tournerGauche(vitesseSlider.getValue());
			else if (name=="Avant droite")robot.getChariot().tournerDroite(vitesseSlider.getValue());
			else if (name=="Arrière gauche")robot.getChariot().tournerArriereGauche(vitesseSlider.getValue());
			else if (name=="Arrière droite")robot.getChariot().tournerArriereDroite(vitesseSlider.getValue());
	    }
	}

	public void mouseReleased(MouseEvent e) {
		JButton source = (JButton)e.getSource();
	    if (e.getButton() == MouseEvent.BUTTON1 && source.isEnabled()==true) {
	    	robot.getChariot().stop();
	    }
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
