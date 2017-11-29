package vue;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import control.*;
import vue.UIConst;
public class Debug extends JFrame{

	SliderDebugControl sliderDebugControl;
	DebugButtonControl debugButtonControl;
	public Debug(String titre){
		super(titre);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;
		this.getContentPane().add(mainPanel);

		//sliderDebugControl=new SliderDebugControl(Application.robot);
		debugButtonControl=new DebugButtonControl(Application.robot);

		gc.gridx = 0;
		gc.gridy = 0;
		mainPanel.add(createBrasGPanel(), gc);
		gc.gridx = 1;
		gc.gridy = 0;
		mainPanel.add(createZone1(), gc);
		gc.gridx = 2;
		gc.gridy = 0;
		mainPanel.add(createBrasDPanel(), gc);

		this.setPreferredSize(new Dimension(1024, 370));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public JPanel createZone1(){


		JPanel zone1=new JPanel();
		zone1.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		//#####Ajout des composants au panel principal#####\\
		gc.gridx = 0;
		gc.gridy = 0;
		zone1.add(createTetePanel(), gc);
		gc.gridx = 1;
		gc.gridy = 0;
		zone1.add(createCameraPanel(), gc);
		gc.gridx = 2;
		gc.gridy = 0;
		zone1.add(createChassisPanel(), gc);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 2;
		zone1.add(createBustePanel(), gc);
		gc.gridx = 2;
		gc.gridy = 1;
		zone1.add(createJambesPanel(), gc);


		zone1.setBorder(new TitledBorder(" Commandes principales "));

		return zone1;
	}

	public JPanel createCameraPanel() {
		JPanel cameraPanel=new JPanel();
		cameraPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc1 = new GridBagConstraints();
		gc1.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc1.insets = new Insets(0,0,0,0);//marge entre les composants
		gc1.ipady = gc1.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc1.weightx = 1;
		gc1.weighty = 1;

		JButton hautButton=new JButton("↑");
		JButton basButton=new JButton("↓");
		JButton gaucheButton=new JButton("←");
		JButton droiteButton=new JButton("→");
		hautButton.setBackground(UIConst.RED);
		basButton.setBackground(UIConst.RED);
		gaucheButton.setBackground(UIConst.RED);
		droiteButton.setBackground(UIConst.RED);
		
		hautButton.setName("camera lever");
		basButton.setName("camera baisser");
		gaucheButton.setName("camera rota gauche");
		droiteButton.setName("camera rota droite");
		
		hautButton.addActionListener(debugButtonControl);
		basButton.addActionListener(debugButtonControl);
		gaucheButton.addActionListener(debugButtonControl);
		droiteButton.addActionListener(debugButtonControl);

		gc1.gridx = 1;
		gc1.gridy = 0;
		cameraPanel.add(hautButton, gc1);
		gc1.gridx = 0;
		gc1.gridy = 1;
		cameraPanel.add(gaucheButton, gc1);
		gc1.gridx = 2;
		gc1.gridy = 1;
		cameraPanel.add(droiteButton, gc1);
		gc1.gridx = 1;
		gc1.gridy = 2;
		cameraPanel.add(basButton, gc1);
		cameraPanel.setBorder(new TitledBorder(" Caméra "));
		return cameraPanel;
	}
	public JPanel createJambesPanel() {
		JPanel jambesPanel=new JPanel();
		jambesPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		JButton leverButton=new JButton("lever");
		JButton baisserButton=new JButton("baisser");
		leverButton.setBackground(UIConst.RED);
		baisserButton.setBackground(UIConst.RED);
		leverButton.setName("jambes lever");
		baisserButton.setName("jambes baisser");
		leverButton.addActionListener(debugButtonControl);
		baisserButton.addActionListener(debugButtonControl);
		gc.gridx = 0;
		gc.gridy = 0;
		jambesPanel.add(leverButton, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		jambesPanel.add(baisserButton, gc);

		jambesPanel.setBorder(new TitledBorder(" Jambes "));
		return jambesPanel;
	}

	public JPanel createChassisPanel() {
		JPanel chassisPanel=new JPanel();
		chassisPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		JButton leverButton=new JButton("lever");
		JButton baisserButton=new JButton("baisser");
		leverButton.setBackground(UIConst.RED);
		baisserButton.setBackground(UIConst.RED);
		leverButton.setName("chassis lever");
		baisserButton.setName("chassis baisser");
		leverButton.addActionListener(debugButtonControl);
		baisserButton.addActionListener(debugButtonControl);
		gc.gridx = 0;
		gc.gridy = 0;
		chassisPanel.add(leverButton, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		chassisPanel.add(baisserButton, gc);

		chassisPanel.setBorder(new TitledBorder(" Chassis "));
		return chassisPanel;
	}
	public JPanel createBustePanel() {
		JPanel bustePanel=new JPanel();
		bustePanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		JButton avantButton=new JButton("avant");
		JButton arriereButton=new JButton("arriere");
		JButton gaucheButton=new JButton("gauche");
		JButton droiteButton=new JButton("droite");
		avantButton.setBackground(UIConst.RED);
		arriereButton.setBackground(UIConst.RED);
		gaucheButton.setBackground(UIConst.RED);
		droiteButton.setBackground(UIConst.RED);
		avantButton.setName("buste incli avant");
		arriereButton.setName("buste incli arriere");
		gaucheButton.setName("buste rota gauche");
		droiteButton.setName("buste rota droite");

		avantButton.addActionListener(debugButtonControl);
		arriereButton.addActionListener(debugButtonControl);
		gaucheButton.addActionListener(debugButtonControl);
		droiteButton.addActionListener(debugButtonControl);

		gc.gridx = 0;
		gc.gridy = 0;
		bustePanel.add(avantButton, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		bustePanel.add(arriereButton, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		bustePanel.add(gaucheButton, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		bustePanel.add(droiteButton, gc);

		bustePanel.setBorder(new TitledBorder(" Buste "));
		return bustePanel;

	}
	public JPanel createTetePanel() {
		JPanel tetePanel=new JPanel();
		tetePanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		JButton gaucheButton=new JButton("←");
		JButton droiteButton=new JButton("→");
		gaucheButton.setBackground(UIConst.RED);
		droiteButton.setBackground(UIConst.RED);
		gaucheButton.setName("tete rota gauche");
		droiteButton.setName("tete rota droite");
		gaucheButton.addActionListener(debugButtonControl);
		droiteButton.addActionListener(debugButtonControl);
		tetePanel.add(gaucheButton, gc);
		tetePanel.add(droiteButton, gc);

		tetePanel.setBorder(new TitledBorder(" Tête "));
		return tetePanel;
	}

	public JPanel createBrasGPanel(){

		JPanel brasGPanel=new JPanel();
		brasGPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridy = 0;
		brasGPanel.add(createEpaulePanel("gauche"), gc);
		gc.gridx = 1;
		gc.gridy = 0;
		brasGPanel.add(createAvBrasPanel("gauche"), gc);
		gc.gridx = 0;
		gc.gridy = 1;
		brasGPanel.add(createPoignetPanel("gauche"), gc);
		gc.gridx = 1;
		gc.gridy = 1;
		brasGPanel.add(createMainPanel("gauche"), gc);

		brasGPanel.setBorder(new TitledBorder(" Commandes bras gauche "));

		return brasGPanel;
	}

	public JPanel createEpaulePanel(String bras) {
		JPanel epaulePanel=new JPanel();
		epaulePanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;
		
		JButton ecarterButton=new JButton("ecarter");
		JButton ramenerButton=new JButton("ramener");
		JButton leverButton=new JButton("lever");
		JButton baisserButton=new JButton("baisser");
		ecarterButton.setBackground(UIConst.RED);
		ramenerButton.setBackground(UIConst.RED);
		leverButton.setBackground(UIConst.RED);
		baisserButton.setBackground(UIConst.RED);
		ecarterButton.setName("epaule "+bras+" ecarter");
		ramenerButton.setName("epaule "+bras+" ramener");
		leverButton.setName("epaule "+bras+" lever");
		baisserButton.setName("epaule "+bras+" baisser");

		ecarterButton.addActionListener(debugButtonControl);
		ramenerButton.addActionListener(debugButtonControl);
		leverButton.addActionListener(debugButtonControl);
		baisserButton.addActionListener(debugButtonControl);

		gc.gridx = 0;
		gc.gridy = 0;
		epaulePanel.add(ecarterButton, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		epaulePanel.add(ramenerButton, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		epaulePanel.add(leverButton, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		epaulePanel.add(baisserButton, gc);
		
		epaulePanel.setBorder(new TitledBorder(" Epaule "));
		return epaulePanel;
		
	}
	public JPanel createAvBrasPanel(String bras) {
		JPanel avBrasPanel=new JPanel();
		avBrasPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;
		
		JButton leverButton=new JButton("lever");
		JButton baisserButton=new JButton("baisser");
		leverButton.setBackground(UIConst.RED);
		baisserButton.setBackground(UIConst.RED);
		
		leverButton.setName("avant-bras "+bras+" lever");
		baisserButton.setName("avant-bras "+bras+" baisser");

		leverButton.addActionListener(debugButtonControl);
		baisserButton.addActionListener(debugButtonControl);

		gc.gridx = 0;
		gc.gridy = 0;
		avBrasPanel.add(leverButton, gc);
		gc.gridy = 1;
		avBrasPanel.add(baisserButton, gc);
		
		avBrasPanel.setBorder(new TitledBorder(" Avant-bras "));
		return avBrasPanel;
		
	}

	public JPanel createPoignetPanel(String bras) {
		JPanel poignetPanel=new JPanel();
		poignetPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;
		
		JButton gaucheButton=new JButton("gauche");
		JButton droiteButton=new JButton("droite");
		gaucheButton.setBackground(UIConst.RED);
		droiteButton.setBackground(UIConst.RED);
		gaucheButton.setName("poignet "+bras+" rota gauche");
		droiteButton.setName("poignet "+bras+" rota droite");

		gaucheButton.addActionListener(debugButtonControl);
		droiteButton.addActionListener(debugButtonControl);

		poignetPanel.add(gaucheButton, gc);
		poignetPanel.add(droiteButton, gc);
		
		poignetPanel.setBorder(new TitledBorder(" Poignet "));
		return poignetPanel;
	}
	public JPanel createMainPanel(String bras) {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;
		
		JButton fermerButton=new JButton("fermer");
		JButton ouvrirButton=new JButton("ouvrir");
		fermerButton.setBackground(UIConst.RED);
		ouvrirButton.setBackground(UIConst.RED);
		fermerButton.setName("main "+bras+" fermer");
		ouvrirButton.setName("main "+bras+" ouvrir");

		fermerButton.addActionListener(debugButtonControl);
		ouvrirButton.addActionListener(debugButtonControl);

		mainPanel.add(fermerButton, gc);
		mainPanel.add(ouvrirButton, gc);
		
		mainPanel.setBorder(new TitledBorder(" Main "));
		return mainPanel;
	}



	public JPanel createBrasDPanel(){
		
		JPanel brasDPanel=new JPanel();
		brasDPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;//ordonne d'étirer les composants dans les deux sens quand ils le peuvent
		gc.insets = new Insets(0,0,0,0);//marge entre les composants
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;//si les composants n'occuppent pas tout l'espace, ils sont placés au milieu
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridy = 0;
		brasDPanel.add(createEpaulePanel("droit"), gc);
		gc.gridx = 1;
		gc.gridy = 0;
		brasDPanel.add(createAvBrasPanel("droit"), gc);
		gc.gridx = 0;
		gc.gridy = 1;
		brasDPanel.add(createPoignetPanel("droit"), gc);
		gc.gridx = 1;
		gc.gridy = 1;
		brasDPanel.add(createMainPanel("droit"), gc);

		brasDPanel.setBorder(new TitledBorder(" Commandes bras droit "));

		return brasDPanel;
	}



}