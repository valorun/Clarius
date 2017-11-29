package vue;
import java.awt.*;
import javax.swing.*;

import com.sun.jna.Native;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * Classe permettant d'instancier le composant de caméra.
 * @author Ronan COLLIER
 *@version 1.0
 */
public class CameraComponent extends JComponent{

	EmbeddedMediaPlayerComponent mediaPlayer;
	String url;
	boolean libFound;
	boolean enabled;
	/**
	 * Constructeur de la classe CameraComponent.
	 * @param url qui représente l'url de la transmission de la caméra.
	 */
	public CameraComponent(String url, boolean enabled) {
		super();
		this.url=url;
		/*Get the VLC Libraries*/
		//NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "");
		this.setLayout(new BorderLayout()); 
		this.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		libFound = new NativeDiscovery().discover();
		System.out.println(">>>libVlc found: "+libFound);
		if(libFound && enabled) {
			Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
			mediaPlayer = new EmbeddedMediaPlayerComponent();
			this.add(mediaPlayer);
		}
		/*Place the components*/
		this.setMinimumSize(new Dimension(300, 150));
		this.setVisible(true);
	}
	/**
	 * Méthode permettant de lancer la lecture du flux vidéo de la caméra.
	 */
	public void playStream() {
		System.out.println(url);
		mediaPlayer.getMediaPlayer().playMedia(url);
	}
	/**
	 * Méthode permettant d'arréter la lecture du flux vidéo de la caméra.
	 */
	public void stopStream() {
		mediaPlayer.getMediaPlayer().stop();
	}


}