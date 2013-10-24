package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Player;
import service.ClientService;
import service.GuiService;

public class Game {

	/**
	 * @param args
	 *
	 */
	public static ArrayList<Player> players;
	public static Player me;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Indtast dit spillernavn");
		BufferedReader b = new BufferedReader (new InputStreamReader(System.in));
		String in = b.readLine();
		 
		players = new ArrayList<Player>();
		me = new Player(in);
		players.add(me);
		
		ScoreList s = new ScoreList(players);
		s.setVisible(true);
		Screen screen = new Screen(GamePlayer.level);
		GamePlayer g = new GamePlayer(me,s, screen);
		GuiService gs = GuiService.getInstance();
		ClientService cs = ClientService.getInstance();
		
		gs.setConnection(cs.getPlayerListContainer());
		gs.subcribePlayerListContainer(screen);
	}

}
