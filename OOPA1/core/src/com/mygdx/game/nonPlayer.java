package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class nonPlayer extends Entity {
	
	private Texture entityTexture;
	private float entityHeight;
	private float entityWidth;
	private boolean isWall;
	
	
	nonPlayer(String nonPlayerAsset, float xCoords, float yCoords, float speed, String state, boolean isAI, boolean isWall) {
		super(xCoords, yCoords, speed, state, isAI);
		this.entityTexture = new Texture(Gdx.files.internal(nonPlayerAsset));
		this.entityHeight = entityTexture.getHeight();
		this.entityWidth = entityTexture.getWidth();
		this.isWall = isWall;
		
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
		
		// Drawing Batch
		public void draw(SpriteBatch batch) {
			// batch.draw(entityTexture, xCoords, yCoords, entityWidth / 2, entityHeight / 2, entityWidth, entityHeight, scale, scale, 0, 0, 0, entityTexture.getWidth(), entityTexture.getHeight(), false, false);
			batch.draw(entityTexture, xCoords, yCoords, entityHeight, entityWidth);
		}
		
		public void movement() {
			if (isAI && !isWall) {
				AIControlledMovement();
			}
		}
		
		@Override
		public void AIControlledMovement() {
			// Actions for AI Controls
		}
		
		@Override
		public void userControlledMovement() {
			// Do nothing, as non-player but methods needs to be implemented
		}
}
