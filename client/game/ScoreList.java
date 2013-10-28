package game;

import guiService.ScorelistService;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.ClientService;


public class ScoreList extends JFrame {

	private static final long serialVersionUID = -7895544666870389766L;
	private ScorelistService service;
	private ClientService clientService;
	
	private JPanel panel;
	
	public ScoreList() {
		super("Scores");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(600,100);
		this.setSize(100, 500);
		this.setResizable(true);
		
		
		panel = new JPanel(new GridLayout(20, 20, 0, 0));
		
		this.add(panel);
		
		//Service
		service = ScorelistService.getInstances();
		service.setScore(this);
		clientService = ClientService.getInstance();

		service.updateScore(clientService.getMePlayer());
		
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
	
	public void addComponent(JLabel label) {
		panel.add(label);
	}
	
	public void clearAll() {
		this.remove(panel);
		panel = new JPanel(new GridLayout(20, 20, 0, 0));
		this.add(panel);
	}
}