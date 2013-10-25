package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;

import model.Player;

import serverService.ServerService;

public class ObjectInThread extends Thread {
	
	private ObjectInputStream ois;
	private volatile boolean running;
	private ServerService service;
	private InetAddress ip;
	
	/**
	 *  Object stream used for receiving player objects.
	 *  ip used as key for the object.
	 * @param ois
	 * @param ip
	 */
	public ObjectInThread(ObjectInputStream ois, InetAddress ip) {
		this.ois = ois;
		running = true;
		this.ip = ip;
		service = ServerService.getInstance();
	}

	
	/**
	 * waits for input on the object stream if the object is of the type player 
	 * then it's added/updated in collection on ServerService.
	 */
	public void run() {
		while(running) {
			try {
				Object obj = ois.readObject();
				if(obj instanceof Player) {
					Player p1 = (Player) obj;
					System.out.println(p1.toString());
					System.out.println("Object received from IP: " + ip.toString());
					service.addPlayer(ip, p1);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
				System.err.println("IOException in ObjectInThread");
				running=false;
//				System.exit(-1);
			} catch (ClassNotFoundException e) {
				System.err.println("Object is not of class Player");
			}
		}
	}

	@Override
	public String toString() {
		return "ObjectInThread [running=" + running + ", ip=" + ip + "]";
	}
}
