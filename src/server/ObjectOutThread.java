package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import serverSerice.ServerService;

public class ObjectOutThread extends Thread {

	private ObjectOutputStream out;
	private boolean running;
	private ServerService serverservice;
	private String ip;

	/**
	 * Object stream used to writing out player objects
	 * ip used as key for the object.
	 * @param out
	 * @param ip
	 */
	public ObjectOutThread(ObjectOutputStream out, String ip){
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
		try{
			while(running){
				out.writeObject(serverservice.getPlayerMap());
				out.flush();
			}

		} catch(IOException e){
			e.printStackTrace();
		}
		
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
