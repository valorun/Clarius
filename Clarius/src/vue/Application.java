package vue;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import control.*;
import robot.Robot;
import robot.Configuration;

public class Application extends JFrame{

	JPanel mainPanel;
	JPanel chariotPanel;
	JPanel commandesPanel;

	ConnectButtonControl connectButtonControl;
	ChariotButtonControl chariotButtonControl;
	LightButtonControl lightButtonControl;
	OnOffSliderControl onOffSliderControl;
	MenuControl menuControl;

	JSlider vitesseSlider;

	JSlider onOffSlider;
	CameraComponent camera;
	JButton connectButton1;
	JButton connectButton2;
	JButton connectButton3;

	MenuBar menuBar;
	Configuration configuration;

	public static Robot robot;

	public static void main(String[] args){
		//ConfigSSH.readConfigFile();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Application("Controle Robot SSH");

	}
	public Application(String titre){
		super(titre);
		configuration=new Configuration();
		robot=new Robot(Configuration.relaysMap);
		setUpPanels();

		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if(Configuration.rasPiCorps.checkConnection())robot.getOnOffCartes().enableRelay(0);
				Configuration.rasPiCorps.disconnect();
				Configuration.rasPiChar.disconnect();
				Configuration.rasPiCam.disconnect();
				dispose();
				System.exit(0);
			}
		});
		menuBar=new MenuBar(menuControl);
		this.setJMenuBar(menuBar);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1024,700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	public void setUpPanels() {
		mainPanel = new JPanel(); //creation du panneau principal 
		mainPanel.setLayout(new BorderLayout());

		commandesPanel = new JPanel(); //panneau principal contenant les commandes
		commandesPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(5, 5, 5, 5);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occupent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;
		this.getContentPane().add(mainPanel);

		connectButtonControl=new ConnectButtonControl(this);
		onOffSlider=new JSlider(JSlider.HORIZONTAL, 0, 1, 0);
		vitesseSlider=new JSlider(JSlider.VERTICAL, 1, 2047, 1);
		chariotButtonControl=new ChariotButtonControl(robot, vitesseSlider);
		lightButtonControl=new LightButtonControl(robot);
		onOffSliderControl=new OnOffSliderControl(this, robot);
		menuControl=new MenuControl(this);

		camera=new CameraComponent(Configuration.videoUrl, Configuration.cameraEnabled);

		mainPanel.add(topPanel(), BorderLayout.NORTH );
		mainPanel.add(bottomPanel(), BorderLayout.SOUTH);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		commandesPanel.add(leftPanel(), gc);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 1; 
		commandesPanel.add(centerPanel(), gc);
		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 0; 
		commandesPanel.add(rightPanel(), gc);

		setPanelEnabled(commandesPanel, false);
		onOffSlider.setEnabled(false);

		mainPanel.add(commandesPanel, BorderLayout.CENTER);
	}
	public JPanel centerPanel(){// panel avec la tête, la caméra, le buste et la position du robot
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(5, 5, 5, 5);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occupent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;//l'espace restant après la création des composants est réparti entre les composants 
		gc.weighty = 1;//

		//#####Commandes tête#####\\
		JSlider teteSlider=new JSlider(JSlider.HORIZONTAL, -1, 1, 0);
		constructSlider(teteSlider, "tete");
		teteSlider.setBorder(new TitledBorder(" Tête "));

		//#####Commandes camera#####\\
		JPanel cameraPanel=new JPanel(new GridBagLayout());
		GridBagConstraints gc1 = new GridBagConstraints();
		gc1.fill = GridBagConstraints.BOTH;
		gc1.insets = new Insets(5, 5, 5, 5);
		gc1.ipady = gc1.anchor = GridBagConstraints.CENTER;
		JSlider cameraHoriSlider=new JSlider(JSlider.HORIZONTAL, -1, 1, 0);	
		JSlider cameraVertSlider=new JSlider(JSlider.VERTICAL, -1, 1, 0);
		constructSlider(cameraHoriSlider, "camera horizontale");
		constructSlider(cameraVertSlider, "camera verticale");
		gc1.weightx = 0;
		gc1.weighty = 1;
		gc1.gridx = 0;
		gc1.gridy = 0;
		cameraPanel.add(cameraHoriSlider, gc1);
		gc1.gridx = 1;
		gc1.gridy = 0;
		cameraPanel.add(cameraVertSlider, gc1);
		gc1.gridx = 2;
		gc1.gridy = 0;
		gc1.weightx = 1; 
		cameraPanel.add(camera, gc1);
		cameraPanel.setBorder(new TitledBorder(" Caméra "));	

		//#####Commandes buste#####\\
		JPanel positionPanel=new JPanel(new GridBagLayout());
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.fill = GridBagConstraints.BOTH;
		gc2.insets = new Insets(5, 5, 5, 5);
		gc2.ipady = gc2.anchor = GridBagConstraints.CENTER;
		gc2.weightx = 0;
		gc2.weighty = 1;
		JSlider positionSlider=new JSlider(JSlider.VERTICAL, 0, 1, 0);
		JSlider busteSlider=new JSlider(JSlider.HORIZONTAL, -1, 1, 0);
		constructSlider(positionSlider, "position");
		constructSlider(busteSlider, "buste");
		gc2.gridx = 0;
		gc2.gridy = 0;
		positionPanel.add(positionSlider, gc2);
		gc2.gridx = 1;
		gc2.gridy = 0;
		gc2.weightx = 1; 
		positionPanel.add(busteSlider, gc2);
		positionPanel.setBorder(new TitledBorder(" Position/Buste "));

		//#####Commandes chariot#####\\
		chariotPanel=new JPanel(new GridBagLayout());
		GridBagConstraints gc3 = new GridBagConstraints();
		gc3.fill = GridBagConstraints.VERTICAL;
		gc3.insets = new Insets(5, 5, 5, 5);
		gc3.ipady = gc3.anchor = GridBagConstraints.CENTER;
		gc3.weightx = 1;
		gc3.weighty = 1;
		JButton chariotAvButton=new JButton("↑");
		JButton chariotArButton=new JButton("↓");
		JButton chariotAvGButton=new JButton("↖");
		JButton chariotAvDButton=new JButton("↗");
		JButton chariotArGButton=new JButton("↙");
		JButton chariotArDButton=new JButton("↘");
		chariotAvButton.setName("Avant");
		chariotArButton.setName("Arrière");
		chariotAvGButton.setName("Avant gauche");
		chariotAvDButton.setName("Avant droite");
		chariotArGButton.setName("Arrière gauche");
		chariotArDButton.setName("Arrière droite");
		chariotAvButton.addMouseListener(chariotButtonControl);
		chariotArButton.addMouseListener(chariotButtonControl);
		chariotAvGButton.addMouseListener(chariotButtonControl);
		chariotAvDButton.addMouseListener(chariotButtonControl);
		chariotArGButton.addMouseListener(chariotButtonControl);
		chariotArDButton.addMouseListener(chariotButtonControl);
		gc3.gridx = 1;
		gc3.gridy = 0;
		chariotPanel.add(chariotAvButton, gc3);
		gc3.gridx = 1;
		gc3.gridy = 2;
		chariotPanel.add(chariotArButton, gc3);
		gc3.gridx = 0;
		gc3.gridy = 2;
		chariotPanel.add(chariotArGButton, gc3);
		gc3.gridx = 2;
		gc3.gridy = 2;
		chariotPanel.add(chariotArDButton, gc3);	
		gc3.gridx = 0;
		gc3.gridy = 1;
		chariotPanel.add(chariotAvGButton, gc3);
		gc3.gridx = 2;
		gc3.gridy = 1;
		chariotPanel.add(chariotAvDButton, gc3);
		gc3.gridheight = 3;
		gc3.gridx = 3;
		gc3.gridy = 0;
		chariotPanel.add(vitesseSlider, gc3);
		chariotPanel.setBorder(new TitledBorder(" Chariot "));

		//#####Commandes diverses#####\\
		JPanel miscPanel=new JPanel();
		JButton lightButton=new JButton("Lumière");
		JButton eyesLightButton=new JButton("Lumière yeux");
		lightButton.setBackground(UIConst.RED);
		eyesLightButton.setBackground(UIConst.RED);
		lightButton.setName("Lumière");
		eyesLightButton.setName("Lumière yeux");
		lightButton.addActionListener(lightButtonControl);
		eyesLightButton.addActionListener(lightButtonControl);
		
		JSlider paupieresSlider=new JSlider(JSlider.VERTICAL, 0, 1, 0);
		constructSlider(paupieresSlider, "paupieres");
		
		miscPanel.add(lightButton);
		miscPanel.add(eyesLightButton);
		miscPanel.add(paupieresSlider);
		miscPanel.setBorder(new TitledBorder(" Divers "));

		//#####Ajout des parties au panel principal#####\\
		gc.weightx = 1;//l'espace restant après la création des composants est réparti entre les composants 
		gc.weighty = 1;//
		gc.gridx = 0;
		gc.gridy = 0;
		centerPanel.add(teteSlider, gc);
		gc.gridwidth = 2;//le prochain panel prend deux cases
		gc.gridx = 1;
		gc.gridy = 0;
		centerPanel.add(cameraPanel, gc);
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 1;
		centerPanel.add(positionPanel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		centerPanel.add(chariotPanel, gc);
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 0;
		centerPanel.add(miscPanel,gc);

		return centerPanel;

	}
	public JPanel bottomPanel(){ //panel avec la console
		JPanel bottomPanel=new JPanel();
		JPanel consolePanel=new JPanel();//pas encore créée

		bottomPanel.add(consolePanel);
		return bottomPanel;
	}
	public JPanel leftPanel(){ //panel bras gauche
		JPanel leftPanel=new JPanel(new BorderLayout());
		JSlider brasGSlider=new JSlider(JSlider.VERTICAL, 0, 1, 0);
		constructSlider(brasGSlider, "bras gauche");
		leftPanel.add(brasGSlider);

		leftPanel.setBorder(new TitledBorder(" Commandes bras gauche "));
		leftPanel.setMaximumSize(new Dimension(175,50));
		return leftPanel;
	}
	public JPanel rightPanel(){ //panel bras droit
		JPanel rightPanel=new JPanel(new BorderLayout());
		JSlider brasDSlider=new JSlider(JSlider.VERTICAL, 0, 1, 0);
		constructSlider(brasDSlider, "bras droit");
		rightPanel.add(brasDSlider);

		rightPanel.setBorder(new TitledBorder(" Commandes bras droit "));
		rightPanel.setPreferredSize(new Dimension(175,50));
		return rightPanel;
	}
	public JPanel topPanel(){ //panel avec les boutons de connexion
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 1;
		gc.weighty = 1;

		JPanel panelRaspiCorps=new JPanel();
		JPanel panelRaspiChariot=new JPanel();
		JPanel panelRaspiCamera=new JPanel();
		connectButton1=new JButton("Connexion raspberry corps");
		connectButton2=new JButton("Connexion raspberry chariot");
		connectButton3=new JButton("Connexion raspberry caméra");
		connectButton1.setName("Connexion raspberry corps");
		connectButton2.setName("Connexion raspberry chariot");
		connectButton3.setName("Connexion raspberry caméra");
		connectButton1.addActionListener(connectButtonControl);
		connectButton2.addActionListener(connectButtonControl);
		connectButton3.addActionListener(connectButtonControl);
		onOffSlider.addChangeListener(onOffSliderControl);
		onOffSlider.setBorder(new TitledBorder(" On/Off "));

		panelRaspiCorps.add(connectButton1);
		panelRaspiCorps.add(new RaspiStatusComponent(Configuration.rasPiCorps));
		panelRaspiCorps.add(onOffSlider);

		panelRaspiChariot.add(connectButton2);
		panelRaspiChariot.add(new RaspiStatusComponent(Configuration.rasPiChar));

		panelRaspiCamera.add(connectButton3);
		panelRaspiCamera.add(new RaspiStatusComponent(Configuration.rasPiCam));

		//topPanel.add(new RaspiStatusComponent(new Raspberry("192.168.1.65", "","")));
		gc.gridx = 0;
		gc.gridy = 0;
		topPanel.add(panelRaspiCorps, gc);
		gc.gridx = 2;
		gc.gridy = 0;
		topPanel.add(panelRaspiChariot, gc);
		gc.gridx = 4;
		gc.gridy = 0;
		topPanel.add(panelRaspiCamera, gc);
		return topPanel;
	}
	public void constructSlider(JSlider slider, String name) {
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setName(name);
		slider.setPaintTicks(true);
		slider.addChangeListener(new SliderControl(robot));
	}
	public static void delay(int milliSeconds) {
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.schedule(new Runnable() {
			public void run() {	
			}
		},  1, TimeUnit.MILLISECONDS);
	}

	public void setPanelEnabled(JPanel panel, Boolean isEnabled) {
		panel.setEnabled(isEnabled);

		Component[] components = panel.getComponents();

		for(int i = 0; i < components.length; i++) {
			if(components[i].getClass().getName() == "javax.swing.JPanel") {
				setPanelEnabled((JPanel) components[i], isEnabled);
			}

			components[i].setEnabled(isEnabled);
		}
	}
	public void updateConnectButtons() {
		if(Configuration.rasPiCorps.checkConnection()==true)connectButton1.setText("Deconnexion raspberry corps");
		else connectButton1.setText("Connexion raspberry corps");
		if(Configuration.rasPiChar.checkConnection()==true)connectButton2.setText("Deconnexion raspberry chariot");
		else connectButton2.setText("Connexion raspberry chariot");
		if(Configuration.rasPiCam.checkConnection()==true)connectButton3.setText("Deconnexion raspberry caméra");
		else connectButton3.setText("Connexion raspberry caméra");
		menuBar.update();
	}
	public void updatePanels() {
		if(Configuration.cameraEnabled) {
			if(Configuration.rasPiCorps.checkConnection()==false)getCamera().stopStream();
			else getCamera().playStream();
		}
		getOnOffSlider().setEnabled(Configuration.rasPiCorps.checkConnection());
		setPanelEnabled(commandesPanel, onOffSlider.getValue()==1);
		setPanelEnabled(chariotPanel, Configuration.rasPiChar.checkConnection());
	}
	public MenuBar getMenu() {
		return menuBar;
	}
	public CameraComponent getCamera() {
		return camera;
	}
	public JPanel getCommandesPanel() {
		return commandesPanel;
	}
	public JPanel getChariotPanel() {
		return chariotPanel;
	}
	public JSlider getOnOffSlider() {
		return onOffSlider;
	}



}
