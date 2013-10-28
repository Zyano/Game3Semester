package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.List;

import model.Player;

public class ObjectOut {

	private ObjectOutputStream outStream;

	/**
	 * Object stream used to writing out player objects
	 * ip used as key for the object.
	 * @param out
	 * @param ip
	 */
	public ObjectOut(ObjectOutputStream outStream, InetAddress ip){
		this.outStream = outStream;
	}

	/**
	 * 
	 * Writes out the list of players and flushes it.
	 * TODO: Missing Exception for IP (Map<key, value> - 2 players with same name)
	 */
	public void outStreamPlayers(List<Player> players){
		try {
			outStream.reset();
			outStream.writeUnshared(players);
			outStream.flush();
			outStream.reset();
		} catch (IOException e) {
			System.err.println("IOException in ObjectOutThread");
		}
	}
}
