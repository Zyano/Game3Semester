package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyClass implements KeyListener {
	private GamePlayer g;

	public KeyClass(GamePlayer g){
		this.g = g;
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			g.PlayerMoved("up");
		}

		if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			g.PlayerMoved("down");
		}
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			g.PlayerMoved("left");
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			g.PlayerMoved("right");
		}
	}

	public void keyReleased(KeyEvent ke) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}