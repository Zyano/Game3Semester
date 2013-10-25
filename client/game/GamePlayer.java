package game;
import model.Player;
import service.ClientService;
public class GamePlayer{

	// Players start values
	//private String playerDirection = "up";

	Player me;

	private String wall = "w";
	private KeyClass ko;
	private ScoreList slist;

	// level is defined column by column, the first row is the first column on the screen.
	public static final String[][] level = 
		{
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w","w", "w", "w", "w", "w", "w", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e","e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e","e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w","e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w","e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e","e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e","e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w","e", "e", "w", "w", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w","e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w","e", "e", "e", "e", "e", "e", "w" },
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w","w", "w", "w", "w", "w", "w", "w", "w" }, 
	};
	// level is defined column by column
	private Screen screen; 
	private ClientService service;

	public GamePlayer(Player me, ScoreList s, Screen screen) {

		this.me = me;
		this.slist = s;
		this.screen = screen;
		screen.setVisible(true);	
		ko = new KeyClass(this);
		screen.addKeyListener(ko);
		
		service = ClientService.getInstance();
		service.sendPlayerObject(me);
	}

	public void PlayerMoved(String direction) {
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
		if (level[x][y].equals(wall)) {
			me.subOnePoint();
			slist.updateScoreOnScreenAll();
		} 
		else {
			me.addOnePoint();
			slist.updateScoreOnScreenAll();
			screen.movePlayerOnScreen(me.getOldXPos(), me.getOldYPos(), x, y,me.getDirection());
			me.setXpos(x);
			me.setYpos(y);
		}
		service.sendPlayerObject(me);
//		System.out.println(me.getXpos() + " " + me.getYpos());
	}
}