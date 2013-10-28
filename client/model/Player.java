package model;

import java.io.Serializable;


public class Player implements Serializable {
	
	private static final long serialVersionUID = -7067161449558273773L;

	private String name;
	private int xpos;
	private int ypos;
	private int point;
	private int oldXPos;
	private int oldYPos;
	private String direction;

	public Player (String name) {
		this.name = name;
		xpos = 5;
		ypos = 7;
		oldXPos = 5;
		oldYPos= 7;
		point = 0;
		direction = "up";
	}
	
	public long getSerialVersionUID(){
		return serialVersionUID;
	}
	
	//Points
	
	public int getPoint() {
		return point;
	}
	
	public void addOnePoint() {
		point ++;
	}

	public void subOnePoint() {
		point --;
	}

	
	//Actual position 
	
	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	//Direction
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	//Old positions
	
	public int getOldXPos() {
		return oldXPos;
	}
	
	public void setOldXPos(int oldXPos) {
		this.oldXPos = oldXPos;
	}
	
	public int getOldYPos() {
		return oldYPos;
	}
	
	public void setOldYPos(int oldYPos) {
		this.oldYPos = oldYPos;
	}
	
	
	@Override
	public String toString () {
		return "old X: " + oldXPos + " old Y: " + oldYPos +  " new X: " + xpos + " new Y: " + ypos;
	}
}