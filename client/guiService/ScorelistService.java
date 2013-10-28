package guiService;

import game.ScoreList;

import javax.swing.JLabel;

import model.Player;

public class ScorelistService {
	private static ScorelistService service;
	private ScoreList score;

	private ScorelistService() {

	}

	public static ScorelistService getInstances() {
		if(service == null) {
			service = new ScorelistService();
		}
		return service;
	}

	public void setScore(ScoreList score) {
		this.score = score;
	}

	public void updateScore(Player p) {
		System.out.println("updating scores");
		JLabel l = new JLabel(p.getName() + ": " + p.getPoint());
		l.setSize(50,200);
		score.add(l);
	}		

	public void clearScore() {
		score.removeAll();
	}
	
	public void repaint() {
		score.repaint();
		System.out.println(score.getComponentCount());
	}
}