package guiService;

import game.ScoreList;

import javax.swing.JLabel;

import model.Player;

/**
 * Class for updating the score list of the all players 
 *
 */
public class ScorelistService {
	private static ScorelistService service;
	private ScoreList score;

	private ScorelistService() {
	}

	/**
	 * Singleton
	 * @return ScoreListService
	 */
	public static ScorelistService getInstances() {
		if(service == null) {
			service = new ScorelistService();
		}
		return service;
	}

	/**
	 * Sets the score list used for updates.
	 * @param score
	 */
	public void setScore(ScoreList score) {
		this.score = score;
	}

	/**
	 * Updates a specific player on the score list.
	 * @param p
	 */
	public synchronized void updateScore(Player p) {
		JLabel l = new JLabel(p.getName() + ": " + p.getPoint());
		l.setSize(100,200);
		score.addComponent(l);
	}		

	/**
	 * Clears the score.
	 */
	public synchronized void clearScore() {
		score.clearAll();
	}
	
	/**
	 * validates the objects on the ScoreList.
	 */
	public synchronized void validateAll(){
		score.validate();
	}
}