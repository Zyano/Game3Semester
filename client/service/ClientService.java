package service;


import model.Player;
import network.Connector;
import network.PlayerListContainer;

public class ClientService {

	private static ClientService service;
	private static Connector connection; 


	private ClientService() {
		connection = new Connector("localhost", "8888");
	}

	/**
	 * Returns an instance of the ClientService class used to for the basic communication between the GUI and the network layer.
	 * @return ClientService
	 */
	public static synchronized ClientService getInstance() {
		synchronized (ClientService.class) {
			if(service == null){
				service = new ClientService();
			}
		}
		return service;
	}

	/**
	 * Stream the player to the server triggering the update on the server.
	 * @param Player
	 */
	public void sendPlayerObject(Player p) {
		connection.sendPlayer(p);
	}

	public PlayerListContainer getPlayerListContainer() {
		return connection.getPlc();
	}
	
}