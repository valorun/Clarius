package robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.jcraft.jsch.JSchException;

/**
 * Classe permettant de gérer le fichier de configuration.
 *
 * @author Ronan COLLIER
 *@version 1.0
 */

public class Configuration{
	public static Configuration instance;
	public static String videoUrl;
	public static boolean cameraEnabled;
	public static Raspberry rasPiCorps;
	public static Raspberry rasPiChar;
	public static Raspberry rasPiCam;
	public static File configFile;
	private Properties properties;
	public static HashMap<String, Relay> relaysMap;

	public Configuration() {
		if(instance==null)instance=this;
		configFile=new File(System.getProperty("user.dir")+"/config.txt");
		properties=new Properties();

		if(!configFile.exists()){ //si le fichier de config n'existe pas, on en creer un par défaut
		}
		try {
			//charge le fichier de propriétés
			properties.load(new FileInputStream(configFile));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		readConnectionsProperties();
		readRelaysProperties();
		readMiscProperties();
		try {
			properties.store(new FileOutputStream(configFile), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		uploadConfigFile();
	}
	private void readConnectionsProperties() {
		//cherche la clé correspondante, et lui donne une valeur par défaut si la clé n'est pas trouvée.
		String username=getProperty("username", "pi");
		String password=getProperty("password", "raspberry");
		String ipCorps=getProperty("ip_corps", "192.168.1.16");
		String ipChar=getProperty("ip_chariot", "192.168.1.17");
		String ipCam=getProperty("ip_camera", "192.168.1.15");
		videoUrl=getProperty("video_url");
		System.out.println(videoUrl);
		System.out.println(ipCorps);

		rasPiCorps=new Raspberry(ipCorps, username, password);
		rasPiChar=new Raspberry(ipChar, username, password);
		rasPiCam=new Raspberry(ipCam, username, password);
	}
	private void readRelaysProperties() {
		relaysMap = new HashMap<String, Relay>();
		getRelay("tete-gauche", "4", 500);
		getRelay("tete-droite", "18", 400);
		getRelay("busteRota-gauche", "17", 1000);
		getRelay("busteRota-droite", "23", 1000);
		getRelay("cameraHori-gauche", "27", 300);
		getRelay("cameraHori-droite", "24", 300);
		getRelay("cameraVert-bas", "22", 100);
		getRelay("cameraVert-haut", "25", 100);
		getRelay("chassis-bas", "13", 10000);
		getRelay("chassis-haut", "16", 11000);
		getRelay("jambes-bas", "19", 5000);
		getRelay("jambes-haut", "20", 7000);
		getRelay("busteIncli-arriere", "26", 6000);
		getRelay("busteIncli-avant", "21", 2500);

		getRelay("onOffCartes", "5");
		getRelay("eclairage", "6");
		getRelay("lumiereYeux", "B15");
		getRelay("diodesBuste", "B17");
		/*##### pins bras gauche #####*/
		getRelay("brasFrontalG-lever", "A18", 3000);
		getRelay("brasFrontalG-baisser", "A17",2000);
		getRelay("brasLateralG-ecarter", "A16", 100);
		getRelay("brasLateralG-ramener", "A15", 100);
		getRelay("avantBrasG-lever", "A14", 4000);
		getRelay("avantBrasG-baisser", "A13", 4000);
		getRelay("mainG-fermer", "A12", 500);
		getRelay("mainG-ouvrir", "A11", 500);
		getRelay("poignetG-exterieur", "B24", 200);
		getRelay("poignetG-interieur", "B23", 200);
		/*##### pins bras droit #####*/
		getRelay("brasFrontalD-lever", "A28", 2500);
		getRelay("brasFrontalD-baisser", "A27", 1750);
		getRelay("brasLateralD-ecarter", "A26", 100);
		getRelay("brasLateralD-ramener", "A25", 100);
		getRelay("avantBrasD-lever", "A24", 4000);
		getRelay("avantBrasD-baisser", "A23", 4000);
		getRelay("mainD-fermer", "A22", 500);
		getRelay("mainD-ouvrir", "A21", 500);
		getRelay("poignetD-exterieur", "B22", 200);
		getRelay("poignetD-interieur", "B21", 200);

	}
	private void readMiscProperties() {
		cameraEnabled=Boolean.parseBoolean(getProperty("cameraEnabled", "true"));
	}
	/**
	 * Méthode permettant de lire une propriété dans le fichier de configuration.
	 * @param key la clé identifiant la propriété recherchée.
	 * @return la valeur de la propriété.
	 */
	private String getProperty(String key){
		String value = properties.getProperty(key);
		return value;
	}
	/**
	 * Méthode permettant de lire une propriété dans le fichier de configuration, et lui attribut une valeur si la propriété n'existe pas.
	 * @param key la clé identifiant la propriété recherchée.
	 * @param defaultVal la valeur par défaut assigné à la propriété si cette dernière n'existe pas.
	 * @return la valeur de la propriété.
	 */
	private String getProperty(String key, String defaultVal){
		String value = properties.getProperty(key, defaultVal);
		if(!properties.containsKey(key)) {
			properties.setProperty(key, defaultVal);
			System.out.println(">>>Config file edited.");
		}
		return value;
	}
	/**
	 * Méthode permettant de rechercher un relai dans le fichier de configuration, et l'ajoute dans la liste des relais.
	 * @param relayName le nom du relai désiré.
	 * @param defaultPin le pin par défaut du relai si ce dernier n'est pas présent dans le fichier de configuration.
	 */
	private void getRelay(String relayName, String defaultPin ){
		Relay relay=new Relay(relayName, getProperty(relayName+".pin",defaultPin));
		relaysMap.put(relayName, relay);
	}
	/**
	 * Méthode permettant de rechercher un relai temporisé dans le fichier de configuration, et l'ajoute dans la liste des relais.
	 * @param relayName le nom du relai désiré.
	 * @param defaultPin le pin par défaut du relai si le pin n'est pas présent dans le fichier de configuration.
	 * @param defaultTempo la temporisation par défaut du relai si la tempo n'est pas présente dans le fichier de configuration.
	 */
	private void getRelay(String relayName, String defaultPin, int defaultTempo ){
		TemporizedRelay relay=new TemporizedRelay(relayName, getProperty(relayName+".pin",defaultPin),
				Integer.parseInt(getProperty(relayName+".tempo",defaultTempo+"")));
		relaysMap.put(relayName, relay);
	}
	/**
	 * Méthode permettant d'obtenir la liste des relais sous forme d'une hasmap de la forme |nom_du_relai, relai|.
	 * @return la liste des relais sous la forme d'une hashmap.
	 */
	public HashMap<String, Relay> getRelaysMap(){
		return relaysMap;
	}
	/**
	 * Méthode permettant de télécharer le fichier de configuration sur le raspberry du corps.
	 */
	public void uploadConfigFile() {
		String str="";
		if(rasPiCorps.checkConnection()) {
			for (String key : relaysMap.keySet()) {
				Relay value = relaysMap.get(key);
				str+=key+".pin="+value.getPin()+"\n";
				if(TemporizedRelay.class.isInstance(value))str+=key+".tempo="+((TemporizedRelay)value).getTempo()+"\n";
			}

			System.out.println(str);
			rasPiCorps.sendCommand("echo \'"+str+"\' > /home/pi/Scripts/config.txt");
		}


	}
	/**
	 * Méthode permettant de mettre à jour l'état d'une connection. Si la connexion est établie, on l'éteint, sinon on l'allume.
	 * @param connection le nom de la connection dont on veut mettre à jour l'état.
	 */
	public void updateConnections(String connection) {
		if (connection=="Connexion raspberry corps") {
			if(rasPiCorps.checkConnection()==true) {
				rasPiCorps.disconnect();
			}
			else{
				try {
					rasPiCorps.connect();
					uploadConfigFile();
				}
				catch(JSchException | IOException ex) {
					System.err.println("Error: connection failed");
					ex.printStackTrace();
				}
			}
		}

		else if (connection=="Connexion raspberry chariot") {
			if(rasPiChar.checkConnection()==true) {
				rasPiChar.disconnect();
			}
			else{
				try {
					rasPiChar.connect();
				}
				catch(JSchException | IOException ex) {
					System.err.println("Error: connection failed");
					ex.printStackTrace();
				}
			}
		}
		else if (connection=="Connexion raspberry caméra") {
			if(rasPiCam.checkConnection()==true) {
				rasPiCam.disconnect();
			}
			else{
				try {
					rasPiCam.connect();
				}
				catch(JSchException | IOException ex) {
					System.err.println("Error: connection failed");
					ex.printStackTrace();
				}
			}
		}
	}

}