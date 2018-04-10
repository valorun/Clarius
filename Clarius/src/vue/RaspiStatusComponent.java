package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import robot.Raspberry;

/**
 * Classe permettant d'instancier l'indicateur d'allumage pour un raspberry.
 * @author Ronan COLLIER
 *@version 1.0
 */
public class RaspiStatusComponent extends JComponent{
	Raspberry raspberry;
	Image imageOn;
	Image imageOff;
	boolean status;
	/**
	 * Constructeur de la classe RaspiStatusComponent.
	 * @param raspberry qui représente le raspberry dont on veut verifier l'état.
	 */
	public RaspiStatusComponent(Raspberry raspberry) {
		super();
		try {
			imageOn= ImageIO.read(new File(System.getProperty("user.dir")+"/res/iconeOnline.png"));
			imageOff= ImageIO.read(new File(System.getProperty("user.dir")+"/res/iconeOffline.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.raspberry=raspberry;
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						updateState();
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		this.setPreferredSize(new Dimension(30,30));
		this.setVisible(true);
	}
	/**
	 * Méthode permettant de mettre à jour l'icone de status en fonction de l'état du raspberry.
	 */
	public void updateState() {
		try {
			if(raspberry.isReachable()) {
				status=true;
				//System.out.println(">>>"+raspberry.getIp()+": is up");
				this.repaint();
			}
			else {
				status=false;
				//System.out.println(">>>"+raspberry.getIp()+": is down");
				this.repaint();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Méthode permettant de dessiner l'icone de status d'allumage du raspberry.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //paint background
		if(status==true) {
			if (imageOn != null) { //there is a picture: draw it   
				int height = this.getSize().height;
				int width = this.getSize().width;
				g.drawImage(imageOn,0,0, width, height, this);
			}
		}
		if(status==false) {
			if (imageOff != null) { //there is a picture: draw it   
				int height = this.getSize().height;
				int width = this.getSize().width;
				g.drawImage(imageOff,0,0, width, height, this);
			}
		}
	}


}