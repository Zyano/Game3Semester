package serverSerice;

import game.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ServerService {
	private static ServerService service;
	private Map<String,Player> playerMap;
	
	public ServerService() {
		playerMap = new ConcurrentHashMap<String, Player>();
	}
	
	public static ServerService getInstance() {
		if(service == null) {
			service = new ServerService();
		}
		return service;
	}
	
	public void addPlayer(String ip, Player p1) {
		playerMap.put(ip, p1);
	}
	
	public void removePlayer(String ip) {
		playerMap.remove(ip);
	}
	
	public Map<String,Player> getPlayerList() {
		return new HashMap<String,Player>(playerMap);
	}
}
