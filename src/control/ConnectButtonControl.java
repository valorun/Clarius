package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import com.jcraft.jsch.JSchException;

import robot.Configuration;
import vue.Application;

public class ConnectButtonControl implements ActionListener {
	Application app;
	Configuration config;

	public ConnectButtonControl(Application app, Configuration config) {
		this.app=app;
		this.config=config;
	}
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		config.updateConnections(name);
		app.updateConnectButtons();
		app.updatePanels();
	}


}
