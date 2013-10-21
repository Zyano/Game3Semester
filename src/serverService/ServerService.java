package serverService;

import game.Player;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ServerService {
	private static ServerService service;
	private Map<InetAddress,Player> playerMap;
	
	public ServerService() {
		playerMap = new ConcurrentHashMap<InetAddress, Player>();
	}
	
	public static ServerService getInstance() {
		if(service == null) {
			service = new ServerService();
		}
		return service;
	}
	
	public void addPlayer(InetAddress ip, Player p1) {
		playerMap.put(ip, p1);
	}
	
	public void removePlayer(String ip) {
		playerMap.remove(ip);
	}
	
	public HashMap<InetAddress, Player> getPlayerMap() {
		return new HashMap<InetAddress,Player>(playerMap);
	}
}
