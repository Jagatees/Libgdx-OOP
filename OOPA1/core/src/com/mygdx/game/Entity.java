package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements objectMovement {
	
	protected float xCoords;
	protected float yCoords;
	protected float speed;
	protected String state;
	protected boolean isAI;
	
	Entity(float xCoords, float yCoords, float speed, String state, boolean isAI) {
		
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.speed = speed;
		this.state = state;
		this.isAI = isAI;
		
	}
	
	// Getter & Setters for (x,y) coordinates
	
	public float getxCoords() {
		return xCoords;
	}
	
	void setxCoords(float x) {
		xCoords = x;
	}
	
	public float getyCoords() {
		return yCoords;
	}
	
	void setyCoords(float y) {
		yCoords = y;
	}
	
	// Getter & Setters for Speed
	
	public float getSpeed() {
		return speed;
	}
	
	void setSpeed(float newSpeed) {
		speed = newSpeed;
	}
 	
	// Getter & Setters for State
	
	public String getState() {
		return state;
	}
	
	void setState(String newState) {
		state = newState;
	}
	
	// Getter & Setters for isAI
	
	public boolean getisAI() {
		return isAI;
	}
	
	// Probably not required to change state of isAI
	
	void setisAI(boolean newisAI) {
		isAI = newisAI;
	}
	
	// Overloading for drawMethod from individual sub-class
	
	public void draw(SpriteBatch batch) {
		// Do nothing, sub-class will handle this according to their own logic
	}
	
	//
	
	public void movement() {
		if (isAI) {
			AIControlledMovement();
		}
		
		else {
			userControlledMovement();
		}
	}

}
