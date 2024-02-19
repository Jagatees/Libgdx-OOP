package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AI.AIControlManagement;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();
    private float aiNewX;
    private float aiNewY;

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void render(SpriteBatch spriteBatch) {
        for (Entity entity : entities) {
            entity.render(spriteBatch);
        }
    }

    public <T extends Entity> List<T> getEntitiesOfTypeList(Class<T> type) {
        List<T> matchingEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                matchingEntities.add(type.cast(entity));
            }
        }
        return matchingEntities;
    }

    public <T extends Entity> T getEntity(Class<T> type) {
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                return type.cast(entity);
            }
        }
        return null;
    }


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
    
    // Primarily needed for AI Controller
    public void setAIXCords(nonPlayer entity, float aiNewX) {
    	entity.setxCords(aiNewX);
    }
    
    public void setAIYCords(nonPlayer entity, float aiNewY) {
    	entity.setyCords(aiNewY);
    }
    
    // Primary Getters & Setters for PlayerController & AIController
    
    public PlayerController getPlayerController(Player entity) {
        return entity.getPlayerController();
    }
    
    public void setPlayerController(Player entity, PlayerController playerController) {
        entity.setPlayerController(playerController);
    }
    
    public AIControlManagement getAIController(nonPlayer entity) {
        return entity.getAIController();
    }
    
    public void setAIController(nonPlayer entity, AIControlManagement aiControlManagement) {
    	entity.setAIController(aiControlManagement);
    }

    public float getSpeed(Entity entity) {
    	return entity.getSpeed();
    }

    public void movement(Entity entity) {
       entity.movement();
    }


    public Entity.EntityType getType(Entity entity) {
        return entity.getEntityType();
    }
}