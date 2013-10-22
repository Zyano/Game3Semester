package network;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.Player;

/**
 * Thread extendtion needed ???? 
 * @author Stefan
 */
public class ObjectStreamer extends Thread {
	private ObjectOutputStream oos;
	
	/**
	 * Creates an ObjectOutputStream using the OutputStream parameter.
	 * @param OutputStream
	 */
	public ObjectStreamer(OutputStream os) {
		try {
			oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			throw new RuntimeException("Failed to create ObjectOutputStream");
		}
	}
	
	/**
	 * Streams the provided player object using the ObjectOutputStream created in the constructor.
	 * @param player
	 */
	public void streamPlayer(Player p) {
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
