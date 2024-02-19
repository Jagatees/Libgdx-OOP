package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Entity.Entity.EntityState;
import com.mygdx.game.Entity.Entity.EntityType;

public class Rectangle extends Entity {

	private ShapeRenderer Rectangle;
	

    public Rectangle(String color, float xCords, float yCords, float width, float height, EntityType entityType) {
    	super(xCords, yCords, width, height, entityType);
    }

	
	protected void render(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Line);
		shape.setColor(color);
		
		shape.rect(xCords, yCords, width, height);
		
		shape.end();
	}

	@Override
	public void AIControlledMovement() {
		// Do nothing
	}

	@Override
	public void userControlledMovement() {
		// Do nothing
	}
	
}
