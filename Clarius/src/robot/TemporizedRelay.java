package robot;

public class TemporizedRelay extends Relay{
	int tempo;
	/**
     * Constructeur de la classe Relay
     * @param name représente le nom de l'action du relai correspondant au relay.
     * @param pin représente le numéro du pin correspondant au relay.
     * @param tempo représente la temporisation pour exécuter un pas de l'action du relay.
     */
	public TemporizedRelay(String name, String pin, int tempo){
		super(name, pin);
		this.tempo=tempo;
	}

	/**
	 * Méthode permettant d'allumer un relai pour la temporisation qui lui est associée puis de l'éteindre.
	 */
	public void enableRelayFor()
	{
		super.enableRelayFor(tempo);
	}
	/**
	 * Méthode permettant d'obtenir la temporisation associée au relai.
	 * @return la temporisation associée au relai.
	 */
	public int getTempo() {
		return tempo;
	}
	/**
	 * Méthode permettant de modifier la temporisation associée au relai.
	 * @param tempo la nouvelle temporisation associée au relai.
	 */
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	/**
	 * Méthode permettant d'obtenir les informations du relai sous forme de String.
	 * @return le nom du relai et sa temporisation associée.
	 */
	public String toString(){
		return name+";"+tempo;
	}
}
