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

	public synchronized void updateScore(Player p) {
		JLabel l = new JLabel(p.getName() + ": " + p.getPoint());
		l.setSize(100,200);
		score.addComponent(l);
	}		

	public synchronized void clearScore() {
		score.clearAll();
	}
	
	public synchronized void validateAll(){
		score.validate();
	}
}