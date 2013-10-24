package network;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import observer.Observer;
import observer.Subject;

import model.Player;

public class PlayerListContainer implements Subject{
	private List<Player> list;
	private Set<Observer> observers;
	
	public PlayerListContainer() {
		list = new ArrayList<>();
		observers = new HashSet<Observer>();
	}

	public List<Player> getList() {
		return new ArrayList<>(list);
	}

	public void setList(List<Player> list) {
		this.list = list;
		fireEvent();
	}
	
	public void fireEvent(){
		Iterator<Observer> it = observers.iterator();
		while(it.hasNext()){
			it.next().update(list);
		}
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
}