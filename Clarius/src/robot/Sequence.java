package robot;

import java.util.ArrayList;

/**
* Classe permettant d'instancier une séquence de pins à activer et désactiver successivement.
* @author Ronan COLLIER
* @version 1.0
*/
public class Sequence{

	private ArrayList<String> relays;
	private int timeTotal;

	/**
     	* Constructeur de la classe Sequence.
     	*/
	public Sequence() {
		relays = new ArrayList<String>();
		timeTotal=0;
	}
	/**
	 * Méthode permettant d'ajouter relai à la séquence.
	 * @param relay représente le pin du relay à activer.
	 * @param time représente le temps en millisecondes, pendant lequel on active le relay.
	 */
	public void addRelay(String relay, int time) {
		relays.add(" "+relay+" "+time);
		timeTotal+=time;
	}
	/**
	 * Méthode permettant d'ajouter relai à la séquence.
	 * @param relay représente le relay à activer.
	 * @param time représente le temps en millisecondes, pendant lequel on active le relay.
	 */
	public void addRelay(Relay relay, int time) {
		relays.add(" "+relay.getName() +" "+time);
		timeTotal+=time;
	}
	/**
	 * Méthode permettant d'obtenir la durée totale de la séquence.
	 * @return La durée totale de la séquence en millisecondes.
	 */
	public int getTimeTotal() {
		return timeTotal;
	}
	/**
	 * Méthode permettant d'exécuter la séquence.
	 */
	public void execute() {
		String command= "/home/pi/Scripts/sequence.bash";
		for (String r: relays) {
			command+=r;
		}
		Configuration.rasPiCorps.sendCommand(command);

	}
}
