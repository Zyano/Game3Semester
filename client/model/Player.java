package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable {
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
	
	public int getPoint() {
		return point;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		System.out.println("Set X pos " + xpos);
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		System.out.println("Set Y pos " + ypos);
		this.ypos = ypos;
	}
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String ToString () {
		return name + "   " + point;
	}
	
	public void addOnePoint() {
		point ++;
	}

	public void subOnePoint() {
		point --;
	}
	
	public int getOldXPos() {
		return oldXPos;
	}
	
	public void setOldXPos(int oldXPos) {
		System.out.println("Set X old pos " + oldXPos);
		this.oldXPos = oldXPos;
	}
	
	public int getOldYPos() {
		return oldYPos;
	}
	
	public void setOldYPos(int oldYPos) {
		System.out.println("Set Y old pos " + oldYPos);
		this.oldYPos = oldYPos;
	}
}