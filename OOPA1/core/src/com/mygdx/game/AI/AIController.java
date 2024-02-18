package com.mygdx.game.AI;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;

public class AIController {
	
	private nonPlayer nonPlayer;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private List<nonPlayer> entitiesNonPlayer;
    private List<Player> entitiesPlayer;
    private int currentDirection = MathUtils.random(1,4);
    private float directionChangeTimer;
    
    public AIController(nonPlayer nonPlayer, EntityManager entityManager, CollisionManager collisionManager) {
    	this.nonPlayer = nonPlayer;
        this.entityManager = entityManager;
        this.collisionManager = collisionManager;
        this.entitiesNonPlayer = entityManager.getEntitiesOfTypeList(nonPlayer.class);
        this.entitiesPlayer = entityManager.getEntitiesOfTypeList(Player.class);
    }
    
    public void aiMove() {
    	// Actions for AI Controls
        // Update timer
        directionChangeTimer += Gdx.graphics.getDeltaTime();

        // Check if it's time to change direction
        if (directionChangeTimer >= 1.0f) {
            directionChangeTimer = 0; // Reset timer
            currentDirection = MathUtils.random(1, 4); // Choose a new direction randomly
        }

        // Move in the current direction
        // Amend This (Maybe)
        float moveAmount = (entityManager.getSpeed(nonPlayer) + 20) * Gdx.graphics.getDeltaTime(); // Adjust speed as needed
        switch (currentDirection) {
            case 1: // UP
            	if (!checkFutureCollision(currentDirection)) {
            		entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) + moveAmount);
            		break;
            	}
            	
            	else {
            		// Ensures that it doesn't regenerate same number (exclude 1)
            		
            		int x = MathUtils.random(1,4);
            		
            		if (x == 1) {
            			// Do nothing
            		}
            		
            		else if (x == 2) {
            			entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) + moveAmount);
    	                break;
            		}
            		
            		else if (x == 3) {
    	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) + moveAmount);
    	                break;
            		}
            		
            		else if (x == 4) {
    	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) - moveAmount);
    	                break;
            		}
             	}
            	
            case 2: // DOWN
            	if (!checkFutureCollision(currentDirection)) {
	            	entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) - moveAmount);
	                break;
            	}
            	
            	else {
            		// Ensures that it doesn't regenerate same number (exclude 2)
            		int x = MathUtils.random(1,4);
            		
            		if (x == 2) {
            			// Do nothing
            		}
            		
            		else if (x == 1) {
            			entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) - moveAmount);
                		break;
            		}
            		
            		else if (x == 3) {
    	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) + moveAmount);
    	                break;
            		}
            		
            		else if (x == 4) {
    	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) - moveAmount);
    	                break;
            		}
            	}
            	
            case 3: // LEFT
            	if (!checkFutureCollision(currentDirection)) {
	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) - moveAmount);
	                break;
            	}
            	
            	else {
            		// Ensures that it doesn't regenerate same number (exclude 3)
            		
            		int x = MathUtils.random(1,4);
            		
            		if (x == 3) {
            			// Do nothing
            		}
            		
            		else if (x == 1) {
            			entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) - moveAmount);
                		break;
            		}
            		
            		else if (x == 2) {
            			entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) + moveAmount);
    	                break;
            		}
            		
            		else if (x == 4) {
    	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) - moveAmount);
    	                break;
            		}

            	}
            	
            case 4: // RIGHT
            	if (!checkFutureCollision(currentDirection)) {
	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) + moveAmount);
	                break;
            	}
            	
            	else {
            		// Ensures that it doesn't regenerate same number (exclude 4)
            		
            		int x = MathUtils.random(1,4);
            		
            		if (x == 4) {
            			// Do nothing
            		}
            		
            		else if (x == 1) {
            			entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) - moveAmount);
                		break;
            		}
            		
            		else if (x == 2) {
            			entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) + moveAmount);
    	                break;
            		}
            		
            		else if (x == 3) {
    	            	entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) + moveAmount);
    	                break;
            		}
            		
            	}
        }
    }
    
    private boolean checkFutureCollision(int direction) {
        float futureX = entityManager.getxCords(nonPlayer);
        float futureY = entityManager.getyCords(nonPlayer);
        float speed = (entityManager.getSpeed(nonPlayer) + 125) * Gdx.graphics.getDeltaTime();

        switch (direction) {
            case Input.Keys.LEFT:
                futureX -= speed;
                break;
            case Input.Keys.RIGHT:
                futureX += speed;
                break;
            case Input.Keys.UP:
                futureY += speed;
                break;
            case Input.Keys.DOWN:
                futureY -= speed;
                break;
        }

        // Do it here
        for (Player entity : entitiesPlayer) {
            if (collisionManager.checkCollision(
                    futureX, futureY,
                    entityManager.getWidth(nonPlayer), entityManager.getHeight(nonPlayer),
                    entityManager.getxCords(entity), entityManager.getyCords(entity),
                    entityManager.getWidth(entity), entityManager.getHeight(entity))) {
            	
            	// To be excluded for now <Fix>
                // collisionManager.checkResponse(nonPlayer.getEntityType(), entity.getEntityType());
                return true;
            }
        }
        
        for (nonPlayer othernonPlayers : entitiesNonPlayer) {
            if (othernonPlayers != nonPlayer && collisionManager.checkCollision(
                    futureX, futureY,
                    entityManager.getWidth(nonPlayer), entityManager.getHeight(nonPlayer),
                    entityManager.getxCords(othernonPlayers), entityManager.getyCords(othernonPlayers),
                    entityManager.getWidth(othernonPlayers), entityManager.getHeight(othernonPlayers))) {
            	
            	// To be excluded for now <Fix>
                //collisionManager.checkResponse(nonPlayer.getEntityType(), otherPlayer.getEntityType());
                return true;
            }
        }

        return false; // no hit
    }
}
