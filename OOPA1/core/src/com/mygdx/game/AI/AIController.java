package com.mygdx.game.AI;

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
		if (directionChangeTimer >= 2.0f) {
			directionChangeTimer = 0; // Reset timer
			currentDirection = MathUtils.random(1, 4); // Choose a new direction randomly
		}

		// Move in the current direction
		float moveAmount = entityManager.getSpeed(nonPlayer) * Gdx.graphics.getDeltaTime();
		if (!checkFutureCollision(currentDirection)) {
			// Move in the current direction if no collision is detected
			moveNonPlayer(currentDirection, moveAmount);
		} else {
			// If a collision is detected, get a new direction that is not the one that caused the collision
			currentDirection = getNewDirection(currentDirection);
			// Then move in the new direction
			moveNonPlayer(currentDirection, moveAmount);
		}
	}

	private void moveNonPlayer(int direction, float moveAmount) {
		// This method moves the nonPlayer in the given direction by the moveAmount
		switch (direction) {
			case 1: // UP
				entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) + moveAmount);
				break;
			case 2: // DOWN
				entityManager.setAIYCords(nonPlayer, entityManager.getyCords(nonPlayer) - moveAmount );
				break;
			case 3: // LEFT
				entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) - moveAmount);
				break;
			case 4: // RIGHT
				entityManager.setAIXCords(nonPlayer, entityManager.getxCords(nonPlayer) + moveAmount);
				break;
		}
	}

    private int lastDirection = -1; // Initialize with an invalid direction

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


	private boolean checkFutureCollision(int direction) {
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
            if (collisionManager.checkCollision(
                    futureX, futureY,
                    entityManager.getWidth(nonPlayer), entityManager.getHeight(nonPlayer),
                    entityManager.getxCords(entity), entityManager.getyCords(entity),
                    entityManager.getWidth(entity), entityManager.getHeight(entity))) {
            	
            	// To be excluded for now <Fix>
//                collisionManager.checkResponse(nonPlayer, entity);
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
