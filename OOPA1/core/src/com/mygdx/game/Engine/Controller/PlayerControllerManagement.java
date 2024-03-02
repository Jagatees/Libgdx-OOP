package com.mygdx.game.Engine.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Engine.Collision.CollisionManager;
import com.mygdx.game.Engine.Entity.EntityManager;
import com.mygdx.game.Engine.Entity.Player;
import com.mygdx.game.Engine.Entity.nonPlayer;
import com.mygdx.game.Engine.Input.InputOutputManager;

import java.util.List;

public class PlayerControllerManagement implements EntityController {

    private Player player;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private List<nonPlayer> entitiesNonPlayer;
    private List<Player> entitiesPlayer;
    private InputOutputManager inputOutputManager ;
    
    public PlayerControllerManagement(Player player, EntityManager entityManager, CollisionManager collisionManager) {
        this.player = player;
        this.entityManager = entityManager;
        this.collisionManager = collisionManager;
        this.entitiesNonPlayer = entityManager.getEntitiesOfTypeList(nonPlayer.class);
        this.entitiesPlayer = entityManager.getEntitiesOfTypeList(Player.class);
        this.inputOutputManager = new InputOutputManager();
    }

    public void move(int direction) {
        if (!checkFutureCollision(direction)) {
            switch (direction) {
                case Input.Keys.LEFT:
                    left();
                    break;
                case Input.Keys.RIGHT:
                    right();
                    break;
                case Input.Keys.UP:
                    up();
                    break;
                case Input.Keys.DOWN:
                    down();
                    break;
            }
        }
    }

    @Override
    public boolean checkFutureCollision(int direction) {
    	
    	// // Ignore collision detection if entity is removed from Scene
		if (entityManager.getisRemoved(player)) { 
			return false;
		}
		
        float futureX =  entityManager.getxCords(player);
        float futureY =  entityManager.getyCords(player) ;
        float speed = ( entityManager.getSpeed(player) + 125) * Gdx.graphics.getDeltaTime();

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

     // Collision Detection for Non-Player Entities Entities
        for (nonPlayer entity : entitiesNonPlayer) {
        	
        	// Only run the checks if entity is not removed, 2nd layer of check just in case
        	if (!entityManager.getisRemoved(entity)) {
	            if (collisionManager.checkCollision(
	                    futureX, futureY,
	                    entityManager.getWidth(player), entityManager.getHeight(player),
	                    entityManager.getxCords(entity), entityManager.getyCords(entity),
	                    entityManager.getWidth(entity), entityManager.getHeight(entity))) {
	
	                collisionManager.checkResponse(player, entity);
	                return true;
	            }
        	}
        }
        
        // Collision Detection for Player Entities
        for (Player otherPlayer : entitiesPlayer) {
        	if (!entityManager.getisRemoved(otherPlayer)) {
	            if (otherPlayer != player && collisionManager.checkCollision(
	                    futureX, futureY,
	                    entityManager.getWidth(player), entityManager.getHeight(player),
	                    entityManager.getxCords(otherPlayer), entityManager.getyCords(otherPlayer),
	                    entityManager.getWidth(otherPlayer), entityManager.getHeight(otherPlayer))) {
	
	                collisionManager.checkResponse(player, otherPlayer);
	                return true;
	            }
        	}
        }

        return false; // no hit
    }
    
    
    // These methods will signal entityManager to move the entity, movement handled 
    public void up() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newY =  entityManager.getyCords(player) + moveAmount;
        entityManager.signalMoveEntity(player, 1, newY);
    }

    public void down() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newY =  entityManager.getyCords(player) - moveAmount;
        entityManager.signalMoveEntity(player, 2, newY);
    }
    
    public void left() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newX =  entityManager.getxCords(player) - moveAmount;
        entityManager.signalMoveEntity(player, 3, newX);
    }
    
    public void right() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newX =  entityManager.getxCords(player) + moveAmount;
        entityManager.signalMoveEntity(player, 4, newX);
    }

}
