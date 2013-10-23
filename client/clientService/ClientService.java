package clientService;


import game.GamePlayer;

import java.util.List;

import network.Connector;
import model.Player;

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

	/**
	 * Update the UI with the newly received player list
	 * TODO: create the method in the gui for updating the list and the methods called for making sure the scorelist is up to date.
	 * @param list<player>
	 */
	public void updatePlayerList(List<Player> list) {
		System.out.println(list);
	}

	public void subscribeForPlayerListUpdates(GamePlayer gp) {
		
	}

}