package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.Entity.EntityState;

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
    
    public <T extends Entity> List<T> getEntitiesOfType(Class<T> type) {
        List<T> matchingEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                matchingEntities.add(type.cast(entity));
            }
        }
        return matchingEntities;
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
    
    // Public Methods required to let Game.java see & allow calls from different managers
    
    // Getters & Setters for xCoords
    
    // Usage: entityManager.getxCords(entityName);
    public float getxCords(Entity entity) {
		return entity.getxCords();
    }
    
 // Usage: entityManager.setxCords(entityName, newxCords);
    public void setxCords(Entity entity, float newxCords) {
    	entity.setxCords(newxCords);
    }
    
    // Getters & Setters for yCoords
    
    // Usage: entityManager.getyCords(entityName))
    public float getyCords(Entity entity) {
    	return entity.getyCords();
    }
    
    // Usage: entityManager.setyCords(entityName, newyCords))
    public void setyCords(Entity entity, float newyCords) {
    	entity.setxCords(newyCords);
    }
    
    // Getter for Width & Height, not likely to require setter
    public float getWidth(Entity entity) {
    	return entity.getWidth(); 
    }
    
    public float getHeight(Entity entity) {
    	return entity.getHeight();
    }
    
    
    // Getter & Setters for speed, syntax is similar to xCords and yCords
    public float getSpeed(Entity entity) {
    	return entity.getSpeed();
    }
    
    public void setSpeed(Entity entity, float newSpeed) {
    	entity.setSpeed(newSpeed);
    }
    
    // Getter & Setters for state, uses EntityState
    public EntityState getState(Entity entity) {
    	return entity.getState();
    }
    
    public void setState(Entity entity, EntityState state) {
    	entity.setState(state);
    }
    
    
    // Movement & Rendering
    public void movement(Entity entity) {
	    // Call movement for each entity
    	entity.movement();
    }
    
    public void render(Entity entity, SpriteBatch batch) {
    	entity.render(batch);
    }
    
    

}