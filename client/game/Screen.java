package game;

import guiService.LevelManager;
import guiService.MovementService;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import network.ConnectionService;
import model.Player;
import service.ClientService;

public class Screen extends JFrame {

	private static final long serialVersionUID = 2047917631676789660L;

	// player locations
	public static JLabel[][] labels = new JLabel[20][20];

	// the map 
	private String[][] level;

	//Movement Service
	private MovementService moveService;

	//Client Service
	private ClientService clientService;
	
	//Score list
	private ScoreList scoreList;
	
	//ConnectionService
	private ConnectionService connectService;


	public Screen() {
		super("Game");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.addKeyListener(new KeyListen());
		
		//Getting service classes
		moveService = MovementService.getInstance();
		clientService = ClientService.getInstance();
		connectService = ConnectionService.getInstance();
		
		//creating the Score List
		scoreList = new ScoreList();
		
		//Getting the level to play on
		this.level = LevelManager.level;

		//Drawing the level
		draw();

		//Set the frame visible!
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}

	/**
	 * Method for drawing a new level.
	 */
	private void draw() {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 20; i++) {
				if (level[i][j].equalsIgnoreCase(LevelManager.wall)) {
					JLabel l = new JLabel(new ImageIcon("./Image/mur1.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				} else if (level[i][j].equalsIgnoreCase(LevelManager.floor)) {
					JLabel l = new JLabel(new ImageIcon("./Image/gulv2.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				}
			}
		}
		Player me = clientService.getMePlayer();
		labels[me.getXpos()][me.getYpos()].setIcon(new ImageIcon("./Image/HeltOp.png"));
	}

	//Private class for registering a key pressed
	private class KeyListen implements KeyListener{

		@Override
		public void keyPressed(KeyEvent ke) {
			if (ke.getKeyCode() == KeyEvent.VK_UP) {
				moveService.playerMoved("up", labels);
			}else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
				moveService.playerMoved("down", labels);
			}else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
				moveService.playerMoved("left", labels);
			}else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveService.playerMoved("right", labels);
			}
			connectService.sendPlayer(clientService.getMePlayer());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
}
