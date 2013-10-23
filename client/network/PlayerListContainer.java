package network;

import java.util.ArrayList;
import java.util.List;

import model.Player;

public class PlayerListContainer {
	private List<Player> list;
	
	public PlayerListContainer() {
		list = new ArrayList<>();
	}

	public List<Player> getList() {
		return new ArrayList<>(list);
	}

	public void setList(List<Player> list) {
		this.list = list;
		fireEvent();
	}
	
	
	
}