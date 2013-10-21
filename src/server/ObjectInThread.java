package server;

import game.Player;

import java.io.IOException;
import java.io.ObjectInputStream;

import serverSerice.ServerService;

public class ObjectInThread extends Thread {
	private ObjectInputStream ois;
	private boolean running;
	private ServerService service;
	private String ip;
	
	public ObjectInThread(ObjectInputStream ois, String ip) {
		this.ois = ois;
		running = true;
		service = ServerService.getInstance();
	}

	public void run() {

		while(running) {
			try {
				Object obj = ois.readObject();
				if(obj instanceof Player) {
					Player p1 = (Player) obj;
					service.addPlayer(ip, p1);
				}
			}
			catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
