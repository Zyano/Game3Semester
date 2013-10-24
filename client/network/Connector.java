package network;

import java.io.IOException;
import java.net.Socket;

import model.Player;

public class Connector {
	
	private String ip;
	private int port;
	private ObjectStreamer os;
	private Socket s;
	private PlayerListContainer plc;
	
	/**
	 * Set the IP/Hostname and port used when the connect method is called.
	 * @param ip - String
	 * @param port - String
	 */
	public Connector(String ip, String port) {
		this.port = Integer.parseInt(port);
		this.ip = ip;
		connect();
	}
	
	/**
	 * Creates the socket with the ip and port provided in the constructor.
	 * if the connection is successfully created and the connection is alive.
	 * Then ObjectReciver and ObjectStreamer is spawned and stated with the each of the streams from the socket.
	 */
	public void connect() {
		try {
			s = new Socket(ip, port);
			
			if(s.isConnected() && !s.isClosed()) {
				os = new ObjectStreamer(s.getOutputStream());
				
				plc = new PlayerListContainer();
				ObjectReceiver or = new ObjectReceiver(s.getInputStream(), plc);
				or.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a player to the ObjectOutputStream
	 * @param p
	 */
	public void sendPlayer(Player p){
		os.streamPlayer(p);
	}
	
	public PlayerListContainer getPlc() {
		return plc;
	}
}