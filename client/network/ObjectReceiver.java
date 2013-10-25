package network;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Player;

public class ObjectReceiver extends Thread {
	
	private ObjectInputStream objectStream;
//	private ClientService service;
	private volatile boolean running;
	
	/**
	 * Given an input stream the constructor creates the ObjectInputStream used in the run method of receiver.
	 * The client service is requested in this method as well as the running attribute is set to true in preparation for the run method.  
	 * @param ObjectInputStream
	 * @throws IOException 
	 */
	public ObjectReceiver(InputStream ois) throws IOException {
		objectStream = new ObjectInputStream(ois);
//		service = ClientService.getInstance();
		running = true;
	}
	
	/**
	 * Continuously receives object from the object stream if the object received is an instance of ArrayList.
	 * Then the object is provided to the client service for drawing on the gui.
	 */
	public void run() {
		
		while(running) {
			Object obj = null;
			try {
				obj = objectStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			if(obj instanceof ArrayList<?>) {
				//We know that it is always an ArrayList containing players
				@SuppressWarnings("unchecked")
				List<Player> list = (ArrayList<Player>) obj;
			}
		}
	}

}
