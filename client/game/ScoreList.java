package game;

import guiService.ScorelistService;

import java.awt.GridLayout;
import javax.swing.JFrame;


public class ScoreList extends JFrame {

	private static final long serialVersionUID = -7895544666870389766L;
	private ScorelistService service;
	
	public ScoreList() {
		super("Scores");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(600,100);
		this.setSize(100, 500);
		this.setResizable(false);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		service = ScorelistService.getInstances();
		service.setScore(this);
		
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
}