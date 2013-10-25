package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Player;

public class ConnectionService {


	private static ConnectionService connection;

	private String ip;
	private ObjectStreamer os;
	private Socket s;

	/**
	 * Singleton pattern
	 */
	private ConnectionService() {
		ip = "";
	}

	/**
	 * Singleton pattern
	 * @return Connector
	 */
	public static ConnectionService getInstance(){
		synchronized (ConnectionService.class) {
			if(connection == null){
				connection = new ConnectionService();
			}
		}
		return connection;
	}

	public void initConnection(String ip){
		if(this.ip.equals("")){
			this.ip = ip;
			connect(ip);
		}
	}

	/**
	 * Creates the socket with the ip and port provided in the constructor.
	 * if the connection is successfully created and the connection is alive.
	 * Then ObjectReciver and ObjectStreamer is spawned and stated with the each of the streams from the socket.
	 */
	private void connect(String ip) {
		try {
			s = new Socket(ip, 8888);
		} catch (UnknownHostException e) {
			System.err.println("UnknownHostException in ConnectionService");
		} catch (IOException e) {
			System.err.println("IOException in ConnectionService");
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

}