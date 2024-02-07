package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	
	private Texture entityTexture;
	private float entityHeight;
	private float entityWidth;
	private float xCoords;
	private float yCoords;
	private float speed;
	
	// State & isAI will be protected in parent Entity class
	
	protected String state;
	protected boolean isAI;
	
	Player (String playerAsset, float xCoords, float yCoords, float speed, String state, boolean isAI) {
		
		this.entityTexture = new Texture(Gdx.files.internal(playerAsset));
		this.entityHeight = entityTexture.getHeight();
		this.entityWidth = entityTexture.getWidth();
		
		// These will be inherited from parent Entity class later
		
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.speed = speed;
		this.state = state;
		this.isAI = isAI;
	}
	
	// Getter & Setters for Players
	
	public Texture getTexture() {
		return entityTexture;
	}
	
	void setTexture(Texture tex) {
		entityTexture = tex;
	}
	
	
	// Getter & Setters for Height, Width
	
	public float getHeight() {
		return entityHeight;
	}
	
	void setHeight(float height) {
		entityHeight = height;
	}
	
	public float getWidth() {
		return entityWidth;
	}
	
	void setWidth(float width) {
		entityWidth = width;
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
	
	// Drawing Batch
	public void draw(SpriteBatch batch) {
		batch.draw(entityTexture, xCoords, yCoords, entityHeight, entityWidth);
	}
}
