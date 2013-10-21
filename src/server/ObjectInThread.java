package server;

import game.Player;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInThread extends Thread {
	private ObjectInputStream ois;
	private boolean running;

	public ObjectInThread(ObjectInputStream ois) {
		this.ois = ois;
		running = true;
	}

	public void run() {

		while(running) {
			try {
				Object obj = ois.readObject();
				if(obj instanceof Player) {
					Player p1 = (Player) obj;
					
				}
			}
			catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
