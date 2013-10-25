package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

import serverService.ServerService;

public class ObjectOutThread extends Thread {

	private ObjectOutputStream out;
	private volatile boolean running;
	private ServerService serverservice;

	/**
	 * Object stream used to writing out player objects
	 * ip used as key for the object.
	 * @param out
	 * @param ip
	 */
	public ObjectOutThread(ObjectOutputStream out, InetAddress ip){
		this.out = out;
		running = true;
		serverservice = ServerService.getInstance();
	}

	/**
	 * 
	 * Writes out the list of players and flushes it.
	 * TODO: Missing Exception for IP (Map<key, value> - 2 players with same name)
	 */
	public void run(){
		while(running){
			try {
				out.writeObject(serverservice.getPlayers());
				out.flush();
			} catch (IOException e) {
				System.err.println("IOException in ObjectOutThread");
				running = false;
			}
		}
	}
}
