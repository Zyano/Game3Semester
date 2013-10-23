package game;
import java.util.ArrayList;
import java.util.List;

import observer.Observer;

import clientService.ClientService;
import model.Player;
public class GamePlayer implements Observer{

	// Players start values
	//private String playerDirection = "up";

	Player me;

	private String wall = "w";
	private KeyClass ko;
	ScoreList slist;


	// level is defined column by column, the first row is the first column on the screen.
	private String[][] level = {
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
				"w", "w", "w", "w", "w", "w", "w", "w" },
				{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e",
					"e", "e", "e", "e", "e", "e", "w" },
					{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w",
						"e", "e", "w", "e", "e", "w", "w" },
						{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w",
							"e", "e", "w", "e", "e", "w", "w" },
							{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e",
								"e", "e", "e", "e", "e", "e", "w" },
								{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w",
									"e", "e", "w", "e", "e", "w", "w" },
									{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w",
										"e", "e", "w", "e", "e", "w", "w" },
										{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w",
											"e", "e", "w", "e", "e", "w", "w" },
											{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w",
												"e", "e", "w", "e", "e", "e", "w" },
												{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w",
													"e", "e", "w", "e", "e", "e", "w" },
													{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e",
														"e", "e", "w", "e", "e", "w", "w" },
														{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e",
															"e", "e", "w", "e", "e", "w", "w" },
															{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w",
																"e", "e", "w", "e", "e", "w", "w" },
																{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w",
																	"e", "e", "w", "e", "e", "w", "w" },
																	{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e",
																		"e", "e", "w", "e", "e", "w", "w" },
																		{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e",
																			"e", "e", "e", "e", "e", "w", "w" },
																			{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w",
																				"e", "e", "w", "w", "e", "w", "w" },
																				{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w",
																					"e", "e", "e", "e", "e", "w", "w" },
																					{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w",
																						"e", "e", "e", "e", "e", "e", "w" },
																						{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
																							"w", "w", "w", "w", "w", "w", "w", "w" }, };
	// level is defined column by column
	private Screen screen; 
	private ArrayList<Player> players;
	private ClientService service;



	public GamePlayer(Player me, ScoreList s,ArrayList<Player> players) {

		this.players =players;
		this.me = me;
		this.slist = s;
		screen = new Screen(level,me.getXpos(),me.getYpos());
		screen.setVisible(true);	
		ko = new KeyClass(this);
		screen.addKeyListener(ko);


		service = ClientService.getInstance();
		service.sendPlayerObject(me);
	}




	public void PlayerMoved(String direction) {
		me.setDirection(direction);

		if (direction.equals("right")) {
			me.setOldXPos(me.getXpos());
			me.setXpos(me.getXpos()+1);
			System.out.println(me.getXpos());
		};

		if (direction.equals("left")) {
			me.setOldXPos(me.getXpos());
			me.setXpos(me.getXpos()-1);
			System.out.println(me.getXpos());
		};

		if (direction.equals("up")) {
			me.setOldYPos(me.getYpos());
			me.setYpos(me.getYpos()-1);
			System.out.println(me.getYpos());
		};

		if (direction.equals("down")) {
			me.setOldYPos(me.getYpos());
			me.setYpos(me.getYpos()+1);
			System.out.println(me.getYpos());
		};

		if (level[me.getXpos()][me.getYpos()].equals(wall)) {
			me.subOnePoint();
			slist.updateScoreOnScreenAll();
		} else {
			me.addOnePoint();
			slist.updateScoreOnScreenAll();
			screen.movePlayerOnScreen(me.getOldXPos(), me.getOldYPos(), me.getXpos(), me.getYpos(),me.getDirection());
			System.out.println("Old X: "+me.getOldXPos() + " Old Y: " + me.getOldYPos() + " new X " + me.getXpos() + " new Y: " + me.getYpos());
		}
		service.sendPlayerObject(me);
	}

	@Override
	public void update(List<Player> list) {

	}
}
