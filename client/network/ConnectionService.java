package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Player;

public class ConnectionService {


	private static ConnectionService connection;

	private String ip;
	private Socket clientSocket;
	
	private ObjectOutputStream outStream;

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

	/**
	 * sets the IP if non is already set and connects to it.
	 * @param ip
	 */
	public void initConnection(String ip){
		if(this.ip.equals("")){
			this.ip = ip;
			connect(ip);
		}
	}

	/**
	 * Creates the socket with the specific ip.
	 */
	private void connect(String ip) {
		try {
			clientSocket = new Socket(ip, 8888);
			outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			outStream.flush();
			
			ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream());
			(new ObjectInThread(inStream)).start();
			
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}
	
	/**
	 * Disconnect for the connection
	 */
	public void disconnect() {
		try {
			clientSocket.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Sends a player to the ObjectOutputStream
	 * @param p
	 */
	public void sendPlayer(Player p){
		try {
			outStream.reset();
			outStream.writeUnshared(p);
			outStream.flush();
			outStream.reset();
		} catch (IOException e) {
		}
	}

}