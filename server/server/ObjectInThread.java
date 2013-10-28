package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;

import model.Player;

import serverService.ServerService;

public class ObjectInThread extends Thread {
	
	private ObjectInputStream inStream;
	private volatile boolean running;
	private ServerService service;
	private InetAddress ip;
	
	/**
	 *  Object stream used for receiving player objects.
	 *  ip used as key for the object.
	 * @param ois
	 * @param ip
	 */
	public ObjectInThread(ObjectInputStream inStream, InetAddress ip, ServerService service) {
		this.inStream = inStream;
		running = true;
		this.ip = ip;
		this.service = service;
	}

	
	/**
	 * waits for input on the object stream if the object is of the type player 
	 * then it's added/updated in collection on ServerService.
	 */
	public void run() {
		while(running) {
			try {
				Object obj = inStream.readObject();
				System.out.println("MODTAGET MODTAGET MODTAGET " + obj);
				if(obj instanceof Player) {
					Player p1 = (Player) obj;
					service.savePlayer(ip, p1);
				}
			}
			catch (IOException e) {
				System.err.println("IOException in ObjectInThread");
				running = false;
				System.exit(-1);
			} catch (ClassNotFoundException e) {
				System.err.println("Object is not of class Player");
			}
		}
	}
}
