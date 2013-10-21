package serverService;

import game.Player;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ServerService {
	
	private static ServerService service;
	private ConcurrentMap<InetAddress,Player> playerMap;
	
	/**
	 * Creates the single ServerService instance. 
	 * the creation of the concurrentHashMap happens here. 
	 * The map uses InetAdress as key and Player as the value.
	 */
	private ServerService() {
		playerMap = new ConcurrentHashMap<>(5, 0.9f, 4);
	}
	
	/**
	 * creates an instance of the service class if non is currently in existence.
	 * @return ServerService  
	 * We could use a argument of DCL - Double Checked Locking
	 */
	public static ServerService getInstance() {
		synchronized (ServerService.class) {
			if(service == null) {
				service = new ServerService();
			}
			return service;
		}
	}
	
	/**
	 * Adds a player to the ConcurrentHashMap, we first check if the
	 * player already is stored in the map, if not we thereby create a
	 * reference to the player and call the atomic thread safe method
	 * pufIfAbsent so the new player is being stored correctly.
	 * @param ip
	 * @param player
	 */
	public void addPlayer(InetAddress ip, Player player) {
		Player p = playerMap.get(ip);
		if(p == null){
			p = player;
			playerMap.putIfAbsent(ip, p);
		}
	}
	
	/**
	 * remove the key and the underlying value from the map.
	 * @param ip
	 */
	public void removePlayer(String ip) {
		playerMap.remove(ip);
	}
	
	/**
	 * return a HashMap of the concurrent map with the same keys and underlying values.
	 * @return HashMap<Inetadress, Player>
	 */
	public HashMap<InetAddress, Player> getPlayerMap() {
		return new HashMap<InetAddress,Player>(playerMap);
	}
}
