package network;

import guiService.MovementService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Player;

public class ObjectInThread extends Thread {


	private ObjectInputStream inStream;
	private volatile boolean running;
	private MovementService moveService;

	public ObjectInThread(ObjectInputStream inStream){
		this.inStream = inStream;
		running = true;
		moveService = MovementService.getInstance();
	}

	public void run(){
		while(running){
			try {
				Object ob = inStream.readObject();
				if(ob instanceof ArrayList<?>){
					@SuppressWarnings("unchecked")
					List<Player> players = (ArrayList<Player>) ob;
					moveService.UpdateAllPlayersMovement(players);
				}
			} catch (ClassNotFoundException e) {
				System.err.println("ClassNotFoundException in ObjectInThread Client");
			} catch (IOException e) {
				System.err.println("IOException in ObjectInThread Client");
				System.exit(-1);
			}
		}
	}
}
