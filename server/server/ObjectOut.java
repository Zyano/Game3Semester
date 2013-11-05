package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.List;

import serverService.ServerService;

import model.Player;

public class ObjectOut {

	private ObjectOutputStream outStream;
	private ServerService serverService;
	private InetAddress ip;

	/**
	 * Object stream used to writing out player objects
	 * ip used as key for the object.
	 * @param out
	 * @param ip
	 */
	public ObjectOut(ObjectOutputStream outStream, InetAddress ip){
		this.outStream = outStream;
		serverService = ServerService.getInstance();
		this.ip = ip;
	}
	
	public InetAddress getIp(){
		return ip;
	}

	/**
	 * 
	 * Writes out the list of players and flushes it.
	 */
	public void outStreamPlayers(List<Player> players){
		try {
			outStream.reset();
			outStream.writeUnshared(players);
			outStream.flush();
			outStream.reset();
		} catch (IOException e) {
			serverService.removeSendExecuter(this);
		}
	}
}
