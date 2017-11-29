package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import robot.Robot;
import vue.UIConst;


public class LightButtonControl implements ActionListener {
	Robot robot;
	public LightButtonControl(Robot robot) {
		this.robot=robot;
	}
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		if (name=="Lumière") robot.getEclairage().enableRelay();	
		else if (name=="Lumière yeux")robot.getLumiereYeux().enableRelay();	
		
		if(source.getBackground()!=UIConst.RED)source.setBackground(UIConst.RED);
		else if(source.getBackground()==UIConst.RED)source.setBackground(UIConst.GREEN);

	}


}
