package serverService;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import model.Player;
import server.ObjectOut;


public class ServerService {

	private static ServerService service;
	private Map<InetAddress,Player> playerMap;
	private Set<ObjectOut> sendExecuters;

	/**
	 * Creates the single ServerService instance. 
	 * the creation of the concurrentHashMap happens here. 
	 * The map uses InetAdress as key and Player as the value.
	 */
	private ServerService() {
		playerMap = new ConcurrentHashMap<>(5, 0.9f, 4);
		sendExecuters = new HashSet<>();
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
		}
		return service;
	}

	/**
	 * Sets the ObjectOut when creation on connection is done
	 * @param sendExecuter
	 */
	public void addSendExecuter(ObjectOut sendExecuter){
		sendExecuters.add(sendExecuter);
	}

	public void removeSendExecuter(ObjectOut sendExecuter) {
		sendExecuters.remove(sendExecuter);
	}

	/**
	 * Adds a player to the ConcurrentHashMap, we first check if the
	 * player already is stored in the map, if not we thereby create a
	 * reference to the player and call the atomic thread safe method
	 * pufIfAbsent so the new player is being stored correctly.
	 * @param ip
	 * @param player
	 */
	public synchronized void savePlayer(InetAddress ip, Player player) {
		playerMap.put(ip, player);	
		findDeadPlayer(player);
		for(Iterator<ObjectOut> it =sendExecuters.iterator();it.hasNext();) {
			it.next().outStreamPlayers(getPlayers());
		}
	}
	
	private void findDeadPlayer(Player p){
		for(Iterator<Player> it = playerMap.values().iterator(); it.hasNext();){
			Player serverPlayer = it.next();
			if(serverPlayer.getChecksum() != p.getChecksum()){
				if(p.getXpos() == serverPlayer.getXpos() && p.getYpos() == serverPlayer.getYpos()){
					serverPlayer.setXpos(5);
					serverPlayer.setYpos(7);
					serverPlayer.setOldXPos(5);
					serverPlayer.setOldYPos(7);
					serverPlayer.setDirection("up");
					System.out.println("Server: " + serverPlayer);
					System.out.println("Me player " + p);
				}
			}
		}
	}

	/**
	 * remove the key and the underlying value from the map.
	 * @param ip
	 */
	public void removePlayer(InetAddress ip) {
		for(Iterator<InetAddress> it = playerMap.keySet().iterator(); it.hasNext();){
			InetAddress temp = it.next();
			if(temp.equals(ip)){
				it.remove();
			}
		}
	}

	/**
	 * return a HashMap of the concurrent map with the same keys and underlying values.
	 * @return HashMap<Inetadress, Player>
	 */
	private List<Player> getPlayers() {
		List<Player> result = new ArrayList<>();
		for(Iterator<Player> it = playerMap.values().iterator(); it.hasNext();){
			result.add(it.next());
		}
		return result;
	}
}
