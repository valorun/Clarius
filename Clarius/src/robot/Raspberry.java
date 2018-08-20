package robot;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
* Classe permettant d'instancier l'abstraction d'un raspberry. Cette classe fournit les méthodes nécessaires pour gérer une liaison SSH avec le raspberry instancié.
*
* @author Ronan COLLIER
* @version 1.0
*/

public class Raspberry{

	private String ip;
	private String username;
	private String password;

	private JSch jsch;
	private Session session;
	Channel channel;
	PrintStream shellStream;

	/**
     * Constructeur de la classe raspberry.
     * @param ip représente l'ip attribuée au préalable au raspberry.
     * @param username représente le nom d'utilisateur choisi pour se connecter au raspberry.
     * @param password représente le mot de passe correspondant au compte utilisateur choisi pour se connecter.
     */
	public Raspberry(String ip, String username, String password) {
		this.ip=ip;
		this.username=username;
		this.password=password;

		jsch = new JSch();
	}

	/**
	 * Méthode permettant de se connecter au raspberry via SSH. 
	 * @return Le raspberry connecté
	 * @throws JSchException
	 * @throws IOException
	 */
	public Raspberry connect() throws JSchException, IOException{

		session = jsch.getSession(username, ip, 22);
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword(password);
		session.connect();
		channel=session.openChannel("shell");//only shell  
		//channel.setOutputStream(System.out); //affiche les resultats des commandes sur la sortie normale 
		shellStream = new PrintStream(channel.getOutputStream());  // printStream for convenience 
		channel.connect(); 
		System.out.println(">>>"+ip+": connected");

		return this;

	}
	/**
	 * Méthode permettant de se déconnecter de la liaison SSH avec le raspberry.
	 * @return Le raspberry déconnecté
	 */
	public Raspberry disconnect() {
		if(checkConnection()==true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (channel!=null)channel.disconnect();
			if (session!=null)session.disconnect();
			System.out.println(">>>"+ip+": disconnected");
		}
		else System.out.println(">>>"+ip+": already disconnected");
		return this;
	}
	/**
	 * Méthode vérifiant le statut de la connection SSH.
	 * @return True si nous sommes connectés au raspberry, False si ce n'est pas le cas.
	 */
	public boolean checkConnection() {
		if(channel!=null) return channel.isConnected();
		else return false;
	}
	/**
	 * Méthode permettant d'envoyer une commande sur le raspberry.
	 * @param command la commande à éxécuter sur le raspberry
	 */
	public void sendCommand(String command){
		try {
			System.out.println(">>>"+ip+": send\'"+command+"\'");
			shellStream.print(command+"\n"); 
			shellStream.flush();
			Thread.sleep(10);
		} catch (Exception e) {
			System.err.println("Error: Can't send command without etablished connection");
		}
	}
	/**
	 * Méthode permettant d'éteindre le raspberry à distance puis de fermer la liaison SSH avec celui-ci. 
	 */
	public void shutdown() {
		try {
			sendCommand("sudo shutdown -h now");
			disconnect();
		}
		catch(Exception e) {}
	}
	/**
	 * Méthode permettant de redémarrer le raspberry à distance puis de fermer la liaison SSH avec celui-ci.
	 */
	public void restart() {
		try {
			sendCommand("sudo reboot");
			disconnect();
		}
		catch(Exception e) {}
	}
	/**
	 * Méthode permettant de savoir si le raspberry est connecté sur le réseau.
	 * @return True si le raspberry est pingable, False sinon.
	 */
	public boolean isReachable() throws UnknownHostException, IOException {
		return InetAddress.getByName(ip).isReachable(3000);

	}
	/**
	 * Méthode retournant l'IP du raspberry.
	 * @return l'IP du raspberry.
	 */
	public String getIp() {
		return ip;
	}
}
