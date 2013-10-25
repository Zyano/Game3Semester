package game;

import java.awt.GridLayout;
import javax.swing.JFrame;


public class ScoreList extends JFrame {

	private static final long serialVersionUID = -7895544666870389766L;

	
	public ScoreList() {
		super("Scores");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(600,100);
		this.setSize(100, 500);
		this.setResizable(false);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}

	
	
	/**
	 * updates the score screen using with the data last set using the updatePlayerScores.
	 */
//	public void updateScoreOnScreenAll() {
//		if (players.size() > labels.size()) {
//			//new players
//			for (int j = labels.size(); j < players.size(); j++) {
//				JLabel l = new JLabel(players.get(j).ToString());
//				l.setSize(50,200);
//				this.add(l);
//				labels.add(l);
//			}		
//		}
//		if (players.size() < labels.size()) {
//			//players left game
//			for (int j = labels.size(); j > players.size(); j--) {
//				System.out.println(j);
//				JLabel lbl = labels.remove(j-1);
//				this.remove(lbl);
//				this.repaint();
//			}	
//		}
//		for (int j = 0; j < players.size(); j++) {
//			labels.get((j)).setText(players.get(j).ToString());
//		}
//	}
}