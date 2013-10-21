package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import serverSerice.ServerService;

public class ObjectOutThread extends Thread {

	private ObjectOutputStream out;
	private boolean running;
	private ServerService serverservice;
	private String ip;

	
	public ObjectOutThread(ObjectOutputStream out, String ip){
		this.out = out;
		running = true;
		serverservice = ServerService.getInstance();
	}

	public void run(){
		try{
			while(running){
				out.writeObject(serverservice.getPlayerMap());
				out.flush();
			}

		} catch(IOException e){
			e.printStackTrace();
		}
		// Mangler Exception med IP (2 spillere med samme navn)
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
