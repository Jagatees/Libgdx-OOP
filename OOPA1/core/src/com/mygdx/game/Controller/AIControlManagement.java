package com.mygdx.game.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;


/**
 * Manages the AI controls and behaviors for non-player entities within the game,
 * including movement and collision detection.
 */
public class AIControlManagement implements EntityController {
	
	private nonPlayer nonPlayer;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private List<nonPlayer> entitiesNonPlayer;
    private List<Player> entitiesPlayer;
    private int currentDirection = MathUtils.random(1,4);
    private float directionChangeTimer;

    /**
     * Constructs an AIControlManagement instance.
     *
     * @param nonPlayer The non-player entity to be controlled by AI.
     * @param entityManager The manager for all entities in the game.
     * @param collisionManager The manager for handling collisions.
     */
    public AIControlManagement(nonPlayer nonPlayer, EntityManager entityManager, CollisionManager collisionManager) {
    	this.nonPlayer = nonPlayer;
        this.entityManager = entityManager;
        this.collisionManager = collisionManager;
        this.entitiesNonPlayer = entityManager.getEntitiesOfTypeList(nonPlayer.class);
        this.entitiesPlayer = entityManager.getEntitiesOfTypeList(Player.class);
    }


    /**
     * Controls the AI movement, including direction changes and collision avoidance.
     */
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
        float moveAmount = Math.min(entityManager.getSpeed(nonPlayer) * Gdx.graphics.getDeltaTime(), 0.2f);
		if (!checkFutureCollision(currentDirection)) {
			// Move in the current direction if no collision is detected
			moveNonPlayer(currentDirection);
		} else {
			// If a collision is detected, get a new direction that is not the one that caused the collision
			currentDirection = getNewDirection(currentDirection);
			// Then move in the new direction
			moveNonPlayer(currentDirection);
		}
	}

    /**
     * Moves the non-player entity in the specified direction by the given amount.
     *
     * @param direction The direction to move.
     */
	private void moveNonPlayer(int direction) {
        switch (direction) {
            case 1: // UP
                up();
                break;
            case 2: // DOWN
                down();
                break;
            case 3: // LEFT
                left();
                break;
            case 4: // RIGHT
                right();
                break;
        }
	}

    private int lastDirection = -1; // Initialize with an invalid direction

    /**
     * Selects a new direction for movement, excluding the direction that caused a collision.
     *
     * @param excludedDirection The direction to be excluded.
     * @return A new direction different from the excluded one.
     */
    private int getNewDirection(int excludedDirection) {
        List<Integer> possibleDirections = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        possibleDirections.remove((Integer) excludedDirection); // Exclude the current direction
        if (lastDirection != -1) {
            possibleDirections.remove((Integer) lastDirection); // Exclude the last direction if it exists
        }
        int newDirection = possibleDirections.get(MathUtils.random(0, possibleDirections.size() - 1));
        lastDirection = newDirection; // Update the last direction moved
        return newDirection;
    }

   
    /**
     * Checks if moving in the given direction would cause a collision.
     *
     * @param direction The direction to check for a future collision.
     * @return true if a collision would occur, false otherwise.
     */
	public boolean checkFutureCollision(int direction) {
		
		// Ignore collision detection if entity is removed from Scene
		if (entityManager.getisRemoved(nonPlayer)) { 
			return false;
		}
		
        float futureX = entityManager.getxCords(nonPlayer);
        float futureY = entityManager.getyCords(nonPlayer);
        float speed = (entityManager.getSpeed(nonPlayer) + 150) * Gdx.graphics.getDeltaTime();

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
        	
        	// // Only run the checks if entity is not removed, 2nd layer of check just in case
        	if (!entityManager.getisRemoved(entity)) {
	            if (collisionManager.checkCollision(
	                    futureX, futureY,
	                    entityManager.getWidth(nonPlayer), entityManager.getHeight(nonPlayer),
	                    entityManager.getxCords(entity), entityManager.getyCords(entity),
	                    entityManager.getWidth(entity), entityManager.getHeight(entity))) {
	
	                collisionManager.checkResponse(entityManager.getType(nonPlayer), entityManager.getType(entity));
	                return true;
	            }
        	}
        }
        
        for (nonPlayer othernonPlayers : entitiesNonPlayer) {
        	if (!entityManager.getisRemoved(othernonPlayers)) {
	            if (othernonPlayers != nonPlayer && collisionManager.checkCollision(
	                    futureX, futureY,
	                    entityManager.getWidth(nonPlayer), entityManager.getHeight(nonPlayer),
	                    entityManager.getxCords(othernonPlayers), entityManager.getyCords(othernonPlayers),
	                    entityManager.getWidth(othernonPlayers), entityManager.getHeight(othernonPlayers))) {
	            	
	                collisionManager.checkResponse(entityManager.getType(nonPlayer), entityManager.getType(othernonPlayers));
	                return true;
	            }
        	}
        }

        return false; // no hit
    }
	

    public void up() {
        float moveAmount = Math.min(entityManager.getSpeed(nonPlayer) * Gdx.graphics.getDeltaTime(), 0.2f);
        float newY = entityManager.getyCords(nonPlayer) + moveAmount;
        entityManager.signalMoveEntity(nonPlayer, 1, newY);
    }


    public void down() {
        float moveAmount = Math.min(entityManager.getSpeed(nonPlayer) * Gdx.graphics.getDeltaTime(), 0.2f);
        float newY = entityManager.getyCords(nonPlayer) - moveAmount;
        entityManager.signalMoveEntity(nonPlayer, 2, newY);
    }
    
	public void left() {
        float moveAmount = Math.min(entityManager.getSpeed(nonPlayer) * Gdx.graphics.getDeltaTime(), 0.2f);
        float newX = entityManager.getxCords(nonPlayer) - moveAmount;
        entityManager.signalMoveEntity(nonPlayer, 3, newX);
    }

    public void right() {
        float moveAmount = Math.min(entityManager.getSpeed(nonPlayer) * Gdx.graphics.getDeltaTime(), 0.2f);
        float newX = entityManager.getxCords(nonPlayer) + moveAmount;
        entityManager.signalMoveEntity(nonPlayer, 3, newX);
    }


}
