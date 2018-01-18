package control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JMenuItem;

import robot.Configuration;
import vue.Application;
import vue.Debug;


public class MenuControl implements ActionListener {
	Application app;
	public MenuControl(Application app) {
		this.app=app;

	}
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)e.getSource();
		String name = source.getText();
		switch(name){
		case "Quitter":{
			app.dispatchEvent(new WindowEvent(app, WindowEvent.WINDOW_CLOSING)); //on active l'évènement de fermeture de fenètre
			break;
		}
		case "Fichier de configuration":{
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().edit(Configuration.configFile);
				} catch (IOException ex) {
					System.err.println("Error: unable to open config file");
					//ex.printStackTrace();
				}
			} else {
				System.out.println("Error: Impossible d'ouvrir le fichier avec ce système");
			}
			break;
		}
		case "Debug":{
			new Debug("Debug panel");
			break;
		}
		case "Shutdown raspi corps":{
			Configuration.rasPiCorps.shutdown();
			break;
		}
		case "Shutdown raspi chariot":{
			Configuration.rasPiChar.shutdown();
			break;
		}
		case "Shutdown raspi caméra":{
			Configuration.rasPiCam.shutdown();
			break;
		}
		case "Shutdown all raspis":{
			if(Configuration.rasPiCorps.checkConnection())Configuration.rasPiCorps.shutdown();
			if(Configuration.rasPiChar.checkConnection())Configuration.rasPiChar.shutdown();
			if(Configuration.rasPiCam.checkConnection())Configuration.rasPiCam.shutdown();
			break;
		}
		
		case "Restart raspi corps":{
			Configuration.rasPiCorps.restart();
			break;
		}
		case "Restart raspi chariot":{
			Configuration.rasPiChar.restart();
			break;
		}
		case "Restart raspi caméra":{
			Configuration.rasPiCam.restart();
			break;
		}
		case "Restart all raspis":{
			if(Configuration.rasPiCorps.checkConnection())Configuration.rasPiCorps.restart();
			if(Configuration.rasPiChar.checkConnection())Configuration.rasPiChar.restart();
			if(Configuration.rasPiCam.checkConnection())Configuration.rasPiCam.restart();
			break;
		}
		}
		app.updateConnectButtons();
		app.updatePanels();
	}


}
