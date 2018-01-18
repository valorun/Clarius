package vue;
import javax.swing.*;

import control.MenuControl;
import robot.Configuration;

public class MenuBar extends JMenuBar{
	
	MenuControl control;
	JMenuItem shutdownCorps;
	JMenuItem shutdownChar;
	JMenuItem shutdownCam;
	JMenuItem shutdownAll;
	JMenuItem restartCorps;
	JMenuItem restartChar;
	JMenuItem restartCam;
	JMenuItem restartAll;
	
	public MenuBar(MenuControl control) {
		this.control=control;
		JMenu menu1 = new JMenu("Panneaux");
		/*différents choix de ce menu*/
		JMenuItem debug = new JMenuItem("Debug"); //affiche le panneau de debug avec les commandes
		JMenuItem config = new JMenuItem("Fichier de configuration"); //ouvre le fichier de configuration
		JMenuItem quitter = new JMenuItem("Quitter"); //quitte le programme

		JMenu menu2 = new JMenu("Connexions");
		shutdownCorps = new JMenuItem("Shutdown raspi corps"); //eteint le raspberry du corps
		shutdownChar = new JMenuItem("Shutdown raspi chariot"); //eteint le raspberry du chariot
		shutdownCam = new JMenuItem("Shutdown raspi caméra"); //eteint le raspberry de la caméra
		shutdownAll = new JMenuItem("Shutdown all raspis"); //eteint tout les raspberries
		restartCorps = new JMenuItem("Restart raspi corps"); //redémarre le raspberry du corps
		restartChar = new JMenuItem("Restart raspi chariot"); //redémarre le raspberry du chariot
		restartCam = new JMenuItem("Restart raspi caméra"); //redémarre le raspberry de la caméra
		restartAll = new JMenuItem("Restart all raspis"); //redémarre tout les raspberries

		menu1.add(debug);
		menu1.add(config);
		menu1.add(quitter);
		menu2.add(shutdownCorps);
		menu2.add(shutdownChar);
		menu2.add(shutdownCam);
		menu2.add(restartCorps);
		menu2.add(restartChar);
		menu2.add(restartCam);
	    menu2.add(new JSeparator()); // SEPARATOR
		menu2.add(shutdownAll);
		menu2.add(restartAll);
		debug.addActionListener(control);
		config.addActionListener(control);
		quitter.addActionListener(control);
		shutdownCorps.addActionListener(control);
		shutdownChar.addActionListener(control);
		shutdownCam.addActionListener(control);
		shutdownAll.addActionListener(control);
		restartCorps.addActionListener(control);
		restartChar.addActionListener(control);
		restartCam.addActionListener(control);
		restartAll.addActionListener(control);
		/*ajout des sous-menus dans le menu */
		this.add(menu1);
		this.add(menu2);
		update();
	}
	public void update() {
		shutdownCorps.setEnabled(Configuration.rasPiCorps.checkConnection());
		shutdownChar.setEnabled(Configuration.rasPiChar.checkConnection());
		shutdownCam.setEnabled(Configuration.rasPiCam.checkConnection());
		shutdownAll.setEnabled(Configuration.rasPiCam.checkConnection() || Configuration.rasPiCorps.checkConnection() || Configuration.rasPiChar.checkConnection());
		restartCorps.setEnabled(Configuration.rasPiCorps.checkConnection());
		restartChar.setEnabled(Configuration.rasPiChar.checkConnection());
		restartCam.setEnabled(Configuration.rasPiCam.checkConnection());
		restartAll.setEnabled(Configuration.rasPiCam.checkConnection() || Configuration.rasPiCorps.checkConnection() || Configuration.rasPiChar.checkConnection());
	}
	
	

}