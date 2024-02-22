package com.mygdx.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;
import com.mygdx.game.Input.InputOutputManager;
import com.mygdx.game.Input.Keyboard;
import com.mygdx.game.audio.AudioManager;

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
        	if (!entityManager.getisRemoved(entity)) {
	            if (collisionManager.checkCollision(
	                    futureX, futureY,
	                    entityManager.getWidth(player), entityManager.getHeight(player),
	                    entityManager.getxCords(entity), entityManager.getyCords(entity),
	                    entityManager.getWidth(entity), entityManager.getHeight(entity))) {
	
	                collisionManager.checkResponse(entityManager.getType(player), entityManager.getType(entity));
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
	
	                collisionManager.checkResponse(entityManager.getType(player), entityManager.getType(otherPlayer));
	                return true;
	            }
        	}
        }

        return false; // no hit
    }

    public void right() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newX =  entityManager.getxCords(player) + moveAmount;
        entityManager.setxCords(player, newX);
    }

    public void left() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newX =  entityManager.getxCords(player) - moveAmount;
        entityManager.setxCords(player, newX);
    }

    public void up() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newY =  entityManager.getyCords(player) + moveAmount;
        entityManager.setyCords(player, newY);
    }

    public void down() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newY =  entityManager.getyCords(player) - moveAmount;
        entityManager.setyCords(player, newY);
    }



}
