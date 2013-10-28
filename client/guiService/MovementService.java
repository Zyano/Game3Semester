package guiService;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import network.ConnectionService;
import game.Screen;
import model.Player;
import service.ClientService;

public class MovementService {

	private static MovementService service;

	private ClientService clientService;

	private ConnectionService connectService;


	private MovementService(){
		clientService = ClientService.getInstance();
		connectService = ConnectionService.getInstance();
	}

	public static MovementService getInstance(){
		synchronized (MovementService.class) {
			if(service == null){
				service = new MovementService();
			}
		}
		return service;
	}

	public synchronized void UpdateAllPlayersMovement(List<Player> players){
		for(int i=0; i<players.size(); i++){
			PlayerMoved(players.get(i).getDirection(), Screen.labels);
		}
	}


	public void PlayerMoved(String direction, JLabel[][] labels) {
		Player me = clientService.getMePlayer();
		me.setDirection(direction);
		int x = me.getXpos();
		int y = me.getYpos();
		me.setOldXPos(x);
		me.setOldYPos(y);

		if (direction.equals("right")) {
			x += 1;
		}else if (direction.equals("left")) {
			x -= 1;
		}else if (direction.equals("up")) {
			y -= 1;
		}else if (direction.equals("down")) {
			y += 1;
		}

		if (LevelManager.getLocation(x, y).equals(LevelManager.wall)) {
			me.subOnePoint();
		} 
		else {
			me.addOnePoint();
			movePlayer(me.getOldXPos(), me.getOldYPos(), x, y, direction, labels);
			me.setXpos(x);
			me.setYpos(y);
		}

		//Send Player after update
		connectService.sendPlayer(me);
	}

	/**
	 * updates a player location.
	 * @param oldX
	 * @param oldY
	 * @param x
	 * @param y
	 * @param playerDirection
	 */
	private void movePlayer(int oldX, int oldY, int x, int y, String playerDirection, JLabel[][] labels) {
		labels[oldX][oldY].setIcon(new ImageIcon("./Image/Gulv2.png"));
		if (playerDirection.equals("right")) {
			labels[x][y].setIcon(new ImageIcon("./Image/Helthoejre.png"));
		}else if (playerDirection.equals("left")) {
			labels[x][y].setIcon(new ImageIcon("./Image/Heltvenstre.png"));
		}else if (playerDirection.equals("up")) {
			labels[x][y].setIcon(new ImageIcon("./Image/HeltOp.png"));
		}else if (playerDirection.equals("down")) {
			labels[x][y].setIcon(new ImageIcon("./Image/HeltNed.png"));
		}
	}
}
