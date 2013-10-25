package guiService;

import game.Screen;
import model.Player;
import service.ClientService;

public class MovementService {

	private static MovementService service;

	private ClientService clientService;
	
	
	private MovementService(){
		clientService = ClientService.getInstance();
	}

	public static MovementService getInstance(){
		synchronized (MovementService.class) {
			if(service == null){
				service = new MovementService();
			}
		}
		return service;
	}

	public void PlayerMoved(String direction, Screen screen) {
		Player me = clientService.getMePlayer();
		me.setDirection(direction);
		int x = me.getXpos();
		int y = me.getYpos();
		me.setOldXPos(x);
		me.setOldYPos(y);
		if (direction.equals("right")) {
			x = me.getXpos() + 1;
		};
		if (direction.equals("left")) {
			x = me.getXpos() - 1;
		};
		if (direction.equals("up")) {
			y = me.getYpos() - 1;
		};
		if (direction.equals("down")) {
			y = me.getYpos() + 1;
		};
		if (LevelManager.getLocation(x, y).equals(LevelManager.wall)) {
			me.subOnePoint();
//			slist.updateScoreOnScreenAll();
		} 
		else {
			me.addOnePoint();
//			slist.updateScoreOnScreenAll();
			screen.movePlayerOnScreen(me.getOldXPos(), me.getOldYPos(), x, y,me.getDirection());
			me.setXpos(x);
			me.setYpos(y);
		}
	}
}
