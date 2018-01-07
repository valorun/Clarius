package robot;

/**
* Classe permettant d'instancier l'abstraction d'un relai. Cette classe fournit les méthodes nécessaires pour gérer allumer ou éteindre un relay situé sur le raspberry gérant le corps du robot.
* @author Ronan COLLIER
*@version 1.0
*/
public class Relay{
	String name;
	String pin;
	byte state;

	/**
     * Constructeur de la classe Relay
     * @param name représente le nom de l'action du relai correspondant au relay.
     * @param pin représente le numéro du pin correspondant au relai (ne sert que de stockage pour le fichier de configuration).
     */
	public Relay(String name, String pin){
		this.name=name;
		this.pin=pin;
	}
	/**
     * Constructeur de la classe relay. Instancie un relai sans nom et sans pin défini.
     */
	public Relay(){
		name = "";
		pin="";
	}
	/**
     * Constructeur de la classe Relay
     * @param name représente le nom de l'action du relai correspondant au relay.
     */
	public Relay(String name){
		this.name = name;
		pin="";
	}
	
	/**
	 * Méthode permettant d'allumer ou éteindre un relai.
	 * @param value représente l'état désiré du relai. 0 pour désactivé, 1 pour activé.
	 */
	public void enableRelay(int value){
		if (value == 0 || value == 1){
			Configuration.rasPiCorps.sendCommand("sudo /home/pi/Scripts/enable_relay.bash "+name+" "+value);
			state = (byte)value;
		}

	}
	/**
	 * Méthode permettant d'allumer ou éteindre un relai. Si le relai etait précédement activé, il est alors désactivé. Si au contraire il était désactivé, il est alors activé.
	 */
	public void enableRelay(){
		if (state == 0) state = 1;
		else state = 0;
		Configuration.rasPiCorps.sendCommand("sudo /home/pi/Scripts/enable_relay.bash "+name+" "+state);
	}
	/**
	 * Méthode permettant d'allumer un relai pour un temps donné.
	 * @param D représente le temps en secondes pendant lequel on veut activer le relai.
	 */
	public void enableRelayFor( int D){
		try {
			Configuration.rasPiCorps.sendCommand("sudo /home/pi/Scripts/sequence.bash " + this.name + " " + Integer.toString(D));
		} 
		catch (Exception e) {

		}
	}
	/**
	 * Méthode permettant de retourner l'état du relai.
	 * @return L'état du relai. 0 pour désactivé, 1 pour activé.
	 */
	public byte getState(){
		return state;
	}
	/**
	 * Méthode permettant le numéro du pin associé au relai. 
	 * @return le numéro du pin associé au relai.
	 */
	public String getPin(){
		return pin;
	}
	/**
	 * Méthode permettant le nom du relai. Equivalent à la méthode toString().
	 * @return le nom du relai.
	 */
	public String getName(){
		return name;
	}
	/**
	 * Méthode permettant d'obtenir les informations du relai sous forme de String.
	 * @return le nom du relai.
	 */
	public String toString(){
		return name;
	}
}
