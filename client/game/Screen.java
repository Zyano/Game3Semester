package game;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Player;

import observer.Observer;

@SuppressWarnings("serial")
public class Screen extends JFrame implements Observer{
	// player loc.
	private JLabel[][] labels = new JLabel[20][20];
	// the map 
	private String[][] level;


	public Screen(String[][] level,int posX,int posY)
	{
		super("TKgame v. 1.0");
		this.level = level;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
//		draw(posX,posY);
		this.setAlwaysOnTop(true);
	}
	
	public Screen(String[][] level) {
		super("TKgame v. 1.0");
		this.level = level;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.setAlwaysOnTop(true);
		draw();
	}
	
	/**
	 * updates a player location.
	 * @param oldX
	 * @param oldY
	 * @param x
	 * @param y
	 * @param playerDirection
	 */
	public void movePlayerOnScreen(int oldX, int oldY, int x, int y,String playerDirection) {
		//		System.out.println("OldX " + oldX + " OldY " + oldY + " X " + x + " Y " + y + " Direction: " + playerDirection);
		labels[oldX][oldY].setIcon(new ImageIcon("./Image/Gulv2.png"));

		if (playerDirection.equals("right")) {
			labels[x][y].setIcon(
					new ImageIcon("./Image/Helthoejre.png"));
		};
		if (playerDirection.equals("left")) {
			labels[x][y].setIcon(
					new ImageIcon("./Image/Heltvenstre.png"));
		};
		if (playerDirection.equals("up")) {
			labels[x][y].setIcon(
					new ImageIcon("./Image/HeltOp.png"));
		};
		if (playerDirection.equals("down")) {
			labels[x][y].setIcon(
					new ImageIcon("./Image/HeltNed.png"));
		};

	}
	
	/**
	 * Draws the default map and the player in it's location specified in the parameters.
	 * @param posX
	 * @param posY
	 */
	public void draw() {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 20; i++) {
				if (level[i][j].equalsIgnoreCase("w")) {
					JLabel l = new JLabel(new ImageIcon("./Image/mur1.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				} else if (level[i][j].equalsIgnoreCase("e")) {
					JLabel l = new JLabel(new ImageIcon("./Image/gulv2.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				}

			}

		}
//		labels[posX][posY].setIcon(new ImageIcon("./Image/HeltOp.png"));
	}

	@Override
	public void update(List<Player> list) {
		for(Player p : list) {
//			System.out.println("update for: " + p.ToString());
			movePlayerOnScreen(p.getOldXPos(), p.getOldYPos(), p.getXpos(), p.getYpos(), p.getDirection());
		}
	}

}
