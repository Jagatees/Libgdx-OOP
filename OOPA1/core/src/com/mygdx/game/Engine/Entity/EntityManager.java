package com.mygdx.game.Engine.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Engine.Controller.AIControlManagement;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();

    // Method to append entity into ArrayList consisting of Entity type
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    // Renders method for either SpriteBatch or ShapeRenderer
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        // Iterate through entities and render only if not marked as removed
        for (Entity entity : entities) {
            if (!entity.getisRemoved()) { // Check if the entity is not marked as removed
                if (entity.getRenderType() == Entity.RenderType.SPRITE) {
                    entity.render(spriteBatch);
                } else if (entity.getRenderType() == Entity.RenderType.SHAPE) {
                    entity.render(shapeRenderer);
                }
            }
        }
    }


    // Generic method to differentiate the entities of different types into different ArrayList
    public <T extends Entity> List<T> getEntitiesOfTypeList(Class<T> type) {
        List<T> matchingEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                matchingEntities.add(type.cast(entity));
            }
        }
        return matchingEntities;
    }
    
    // Generic method that accepts forms of Entity
    public <T extends Entity> T getEntity(Class<T> type) {
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                return type.cast(entity);
            }
        }
        return null;
    }
    
    
    // The following are public methods accessible for API calls by other managers:
    
    // Getter for retrieving x-coordinate of entity
    public float getxCords(Entity entity) {
		return entity.getxCords();
    }
    
    // Setter for amending x-coordinate of entity
    public void setxCords(Entity entity, float newxCords) {
		entity.setxCords(newxCords);
    }
    
    // Getter for retrieving y-coordinate of entity
    
    public float getyCords(Entity entity) {
    	return entity.getyCords();
    }
    
    // Setter for amending y-coordinate of entity
    public void setyCords(Entity entity, float newyCords) {
		entity.setyCords(newyCords);
    }
    
    // Getter to retrieve width of entity object
    public float getWidth(Entity entity) {
    	return entity.getWidth(); 
    }
    
    // Setter for amending Width of entity
    public void setWidth(Entity entity, float newWidth) {
		entity.setWidth(newWidth);
    }
    
    // Getter to retrieve Height of entity object
    public float getHeight(Entity entity) {
    	return entity.getHeight();
    }
    
    // Setter for amending Width of entity
    public void setHeight(Entity entity, float newHeight) {
		entity.setHeight(newHeight);
    }
    
    // Primary Getter & Setter for PlayerController (to be used for Player objects ONLY)
    public PlayerControllerManagement getPlayerController(Player entity) {
        return entity.getPlayerController();
    }
    
    public void setPlayerController(Player entity, PlayerControllerManagement playerControllerManagement) {
        entity.setPlayerController(playerControllerManagement);
    }
    
    // Primary Getter & Setter for AIController (to be used for AI/nonPlayer objects ONLY)
    public AIControlManagement getAIController(nonPlayer entity) {
        return entity.getAIController();
    }
    
    public void setAIController(nonPlayer entity, AIControlManagement aiControlManagement) {
    	entity.setAIController(aiControlManagement);
    }
    
    
    // Getter to retrieve entity's speed
    public float getSpeed(Entity entity) {
    	return entity.getSpeed();
    }
    
    // Setter to amend entity's speed
    public void setSpeed(Entity entity, float newSpeed) {
    	entity.setSpeed(newSpeed);
    }
    
    // Entity update to be handled by subclass
    public void update(Entity entity, float deltaTime) {
    	entity.update(deltaTime);
    }
    
    // Entity movement to be handled by subclass
    public void movement(Entity entity) {

        if (!SimulationLifecycleManagement.getInstance().isPaused()){
            if (!getisRemoved(entity)) {
                entity.movement();
            }
        }

    }
    
    // Getter & Setter to retrieve and set Entity state
    public Entity.EntityState getState(Entity entity) {
    	return entity.getState();
    }
    
    public void setState(Entity entity, Entity.EntityState state) {
    	entity.setState(state);
    }

    // Getter to retrieve the Entity Type of a particular Entity object
    public Entity.EntityType getType(Entity entity) {
        return entity.getEntityType();
    }
    
    // Setter to set the Entity Type
    public void setType(Entity entity, Entity.EntityType type) {
    	entity.setEntityType(type);
    }
    
    // Getter & Setter for RenderType
    
    public Entity.RenderType getRenderType(Entity entity) {
    	return entity.getRenderType();
    }
    
    public void setRenderType(Entity entity, Entity.RenderType renderType) {
    	entity.setRenderType(renderType);
    }
    
    // Getter & Setter for ShapeRenderer objects
    
    public Color getColor(Entity entity) {
    	return entity.getColor();
    }
    
    public void setColor(Entity entity, Color newColor) {
    	entity.setColor(newColor);
    }
    
    // Method to remove entity
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    	entity.setisRemoved(true);
    }
    
    // Getter to check entity is indeed removed, to be used by other classes
    
    public boolean getisRemoved(Entity entity) {
    	return entity.getisRemoved();
    }
    
    
    // Methods to let EntityManager handle entities movement instead of controllers
    
    public void signalMoveEntity(Entity entity, int direction, float newCords) {
    	// Direction: 1 - Up, 2 - Down, 3 - Left, 4 - Right
    	
    	if (direction == 3 || direction == 4) {
    		entity.setxCords(newCords);
    	}
    	
    	else if (direction == 1 || direction == 2) {
    		entity.setyCords(newCords);
    	}

    }


}