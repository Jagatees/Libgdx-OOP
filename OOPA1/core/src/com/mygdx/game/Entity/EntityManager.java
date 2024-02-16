package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();


    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities); // Return a copy to prevent external modifications
    }

    public void render(SpriteBatch spriteBatch) {
        for (Entity entity : entities) {
            entity.render(spriteBatch);
        }
    }

    // Method to get the first entity of a specific type
    public <T extends Entity> T getEntity(Class<T> type) {
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                return type.cast(entity);
            }
        }
        return null; // or throw an exception if you prefer
    }
    
    // Methods required to let Game.java see & communicate between different managers
    
    public float getxCords(Entity entity) {
		return entity.getxCords();
    }
    
    public float getyCords(Entity entity) {
    	return entity.getyCords();
    }
    
    public float getWidth(Entity entity) {
    	return entity.getWidth(); 
    }
    
    public float getHeight(Entity entity) {
    	return entity.getHeight();
    }
    
    public float getSpeed(Entity entity) {
    	return entity.getSpeed();
    }

}