package serverService;

import game.Player;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ServerService {
	
	private static ServerService service ;
	private Map<InetAddress,Player> playerMap;
	
	/**
	 * Creates the single ServerService instance. 
	 * the creation of the concurrentHashMap happens here. 
	 * The map uses InetAdress as key and Player as the value.
	 */
	private ServerService() {
		playerMap = new ConcurrentHashMap<InetAddress, Player>();
	}
	
	/**
	 * creates an instance of the service class if non is currently in existence.
	 * @return ServerService  
	 * We could use a argument of DCL - Double Checked Locking
	 */
	public synchronized static ServerService getInstance() {
		if(service == null) {
			service = new ServerService();
		}
		return service;
	}
	
	/**
	 * adds a player to the map using the InetAdress as the key.
	 * if a player is already saved by the key that player is overwritten.
	 * @param ip
	 * @param p1
	 */
	public void addPlayer(InetAddress ip, Player p1) {
		playerMap.put(ip, p1);
	}
	
	/**
	 * remove the key and the underlying value from the map.
	 * @param ip
	 */
	public void removePlayer(String ip) {
		playerMap.remove(ip);
	}
	
	/**
	 * return a HashMap of the concurrentmap with the same keys and underlying values.
	 * @return HashMap<Inetadress, Player>
	 */
	public HashMap<InetAddress, Player> getPlayerMap() {
		return new HashMap<InetAddress,Player>(playerMap);
	}
}
