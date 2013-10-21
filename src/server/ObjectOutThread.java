package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutThread extends Thread {

	private ObjectOutputStream out;
	private boolean running;
	private ServerService serverservice;
	private String ip;

	
	public ObjectOutThread(ObjectOutputStream out, String ip){
		this.out = out;
		running = true;
	}

	public void run(){
		try{
			while(running){
				out.writeObject(ServerService.getPlayerList());
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
