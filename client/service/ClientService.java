package service;


import storage.Dao;
import model.Player;

/**
 *	Class for helping with creation and storage of a player 
 */
public class ClientService {

	private static ClientService service;
	private Dao dao;


	private ClientService() {
		dao = Dao.getInstance();
	}

	/**
	 * Returns an instance of the ClientService class used to for 
	 * the basic communication between the GUI and the network layer.
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
	
	public void createMePlayer(String name){
		Player me = new Player(name);
		dao.storeMePlayer(me);
	}
	
	/**
	 * gets the client player.
	 * @return
	 */
	public Player getMePlayer(){
		return dao.getMePlayer();
	}
	
	/**
	 * Saves a player in dao.
	 * @param p
	 */
	public void savePlayer(Player p){
		dao.storeMePlayer(p);
	}
	
}