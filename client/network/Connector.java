package network;

import java.io.IOException;
import java.net.Socket;

public class Connector {
	private String ip;
	private int port;
	
	/**
	 * Set the IP/Hostname and port used when the connect method is called.
	 * @param ip - String
	 * @param port - String
	 */
	public Connector(String ip, String port) {
		this.port = Integer.parseInt(port);
		this.ip = ip;
	}
	
	/**
	 * Creates the socket with the ip and port provided in the constructor.
	 * if the connection is sucessfully created and the connection is alive.
	 * Then ObjectReciver and ObjectStreamer is spawned and stated with the each of the streams from the socket.
	 */
	public void connect() {
		try {
			Socket s = new Socket(ip, port);
			
			if(s.isConnected() && !s.isClosed()) {
				ObjectReceiver or = new ObjectReceiver(s.getInputStream());
				or.start();
				
				ObjectStreamer os = new ObjectStreamer(s.getOutputStream());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
