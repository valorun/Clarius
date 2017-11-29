package robot;

public class TemporizedRelay extends Relay{
	int tempo;
	/**
     * Constructeur de la classe Relay
     * @param pin représente le numéro du pin correspondant au relay.
     */
	public TemporizedRelay(String name, String pin, int tempo){
		super(name, pin);
		this.tempo=tempo;
	}

	/**
	 * Méthode permettant d'allumer un relai pour la temps qui lui est associé puis l'éteint.
	 */
	public void enableRelayFor()
	{
		super.enableRelayFor(tempo);
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}


	public String toString(){
		return name+";"+tempo;
	}
}