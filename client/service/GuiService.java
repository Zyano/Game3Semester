package service;

import java.util.List;

import model.Player;
import network.Connector;
import network.PlayerListContainer;
import observer.Observer;
import observer.Subject;

public class GuiService {
	private static GuiService service;
	private Subject playerListContainer;
	
	/**
	 * creates an instance of the GuiService class if one is not already created.
	 * @return {@link GuiService}
	 */
	public static GuiService getInstance() {
		if(service == null) {
			service = new GuiService();
		}
		return service;
	}
	
	/**
	 * Subscribes and observer to a PlayerListContainer subject.
	 * @param o
	 */
	public void subcribePlayerListContainer(Observer o) {
		playerListContainer.addObserver(o);
	}
	
	/**
	 * gets the currently used Connector
	 * @return {@link Connector}
	 */
	public Subject getPlayerListContainer() {
		return playerListContainer;
	}
	
	/**
	 * Sets the Connector which object receiver is used for player list updates.
	 * @param connection
	 */
	public void setConnection(Subject s) {
		this.playerListContainer = s;
	}
	
	/**
	 * Update the UI with the newly received player list
	 * TODO: create the method in the gui for updating the list and the methods called for making sure the scorelist is up to date.
	 * @param list<player>
	 */
	public void updatePlayerList(List<Player> list) {
		System.out.println(list);
	}

}