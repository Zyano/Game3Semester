package guiService;

import game.Screen;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Player;
import service.ClientService;

public class MovementService {

	private static MovementService service;

	private ClientService clientService;

	private ScorelistService scorelistService;

	private MovementService(){
		clientService = ClientService.getInstance();
		scorelistService = ScorelistService.getInstances();
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
		scorelistService.clearScore();
		Player me = clientService.getMePlayer();
		for(Player p : players){
			if(p.getChecksum() != me.getChecksum()) {
				movePlayer(p.getOldXPos(), p.getOldYPos(), p.getXpos(), p.getYpos(), p.getDirection(), Screen.labels, false);
			}else if(p.getDead()) {
				movePlayer(p.getOldXPos(), p.getOldYPos(), p.getXpos(), p.getYpos(), p.getDirection(), Screen.labels, true);
				p.setDead(false);
				clientService.savePlayer(p);
			}
			scorelistService.updateScore(p);
		}
		scorelistService.validateAll();
	}

	public void playerMoved(String direction, JLabel[][] labels) {
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
			movePlayer(me.getOldXPos(), me.getOldYPos(), x, y, direction, labels, true);
			me.setXpos(x);
			me.setYpos(y);
			makeDarkness(me, labels);
		}
	}

	/**
	 * updates a player location.
	 * @param oldX
	 * @param oldY
	 * @param x
	 * @param y
	 * @param playerDirection
	 */
	private void movePlayer(int oldX, int oldY, int x, int y, String playerDirection, JLabel[][] labels, boolean clientMovement) {
		Player me = clientService.getMePlayer();
		if(disconnectPlayer(playerDirection)){
			labels[x][y].setIcon(new ImageIcon("./Image/Gulv2.png"));
		}

		Point[] noDarkness = me.visibilityMap();
		int index = 0;
		boolean found = false;
		while(!found && index<noDarkness.length){
			if(x == noDarkness[index].getX() && y == noDarkness[index].getY()){
				found = true;
			}else index++;
		}

		if(found){
			if(!clientMovement && (oldX != me.getXpos() || oldY != me.getYpos())) {
				labels[oldX][oldY].setIcon(new ImageIcon("./Image/Gulv2.png"));
			} else if(clientMovement) {
				labels[oldX][oldY].setIcon(new ImageIcon("./Image/Gulv2.png"));
			}
			drawPlayer(me, playerDirection, x, y, labels);
		}
	}


	private void drawPlayer(Player me, String playerDirection, int x, int y, JLabel[][] labels){
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

	private boolean disconnectPlayer(String playerDirection){
		return playerDirection.equals("disconnect");
	}

	private void makeDarkness(Player me, JLabel[][] labels){
		Point[] noDarkness = me.visibilityMap();

		for(int i=0; i<labels.length; i++){
			for(int j=0; j<labels[i].length; j++){

				//checking all visibility locations for each label location
				int index = 0;
				boolean found = false;
				while(!found && index<noDarkness.length){
					if(i == noDarkness[index].getX() && j == noDarkness[index].getY()){

						if (LevelManager.getLocation(i, j).equals(LevelManager.wall)){
							labels[i][j].setIcon(new ImageIcon("./Image/mur1.png"));
						}else labels[i][j].setIcon(new ImageIcon("./Image/gulv2.png"));
						found = true;

					}else index++;
				}

				if(!found){
					labels[i][j].setIcon(new ImageIcon("./Image/darkness.png"));
				}
				drawPlayer(me, me.getDirection(), me.getXpos(), me.getYpos(), labels);
			}
		}
	}
}