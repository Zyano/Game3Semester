package storage;

import model.Player;

public class Dao {
	
	private static Dao dao;
	
	private Player me;
	
	private Dao(){
	}
	
	public static Dao getInstance(){
		synchronized (Dao.class) {
			if(dao == null){
				dao = new Dao();
			}
		}
		return dao;
	}
	
	
	/**
	 * Method for storing the Me player
	 * @param me
	 */
	public void storeMePlayer(Player me){
		this.me = me;
	}
	
	/**
	 * Method for getting the Me player
	 * @return
	 */
	public Player getMePlayer(){
		return me;
	}
}
