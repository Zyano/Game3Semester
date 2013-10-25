package network;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.Player;

/**
 * @author Stefan
 */
public class ObjectStreamer {
	
	private ObjectOutputStream oos;
	
	/**
	 * Creates an ObjectOutputStream using the OutputStream parameter.
	 * @param OutputStream
	 */
	public ObjectStreamer (OutputStream os) {
		try {
			oos = new ObjectOutputStream(os);
			oos.flush();
		} catch (IOException e) {
			throw new RuntimeException("Failed to create ObjectOutputStream");
		}
	}
	
	/**
	 * Streams the provided player object using the ObjectOutputStream created in the constructor.
	 * @param player
	 */
	public void streamPlayer (Player p) {
		try {
			oos.writeObject(p);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
