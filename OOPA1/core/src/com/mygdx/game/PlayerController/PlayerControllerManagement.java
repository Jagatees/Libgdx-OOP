package com.mygdx.game.PlayerController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;

import java.util.List;

public class PlayerControllerManagement {

    private Player player;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private List<nonPlayer> entitiesNonPlayer;
    private List<Player> entitiesPlayer;


    public PlayerControllerManagement(Player player, EntityManager entityManager, CollisionManager collisionManager) {
        this.player = player;
        this.entityManager = entityManager;
        this.collisionManager = collisionManager;
        this.entitiesNonPlayer = entityManager.getEntitiesOfTypeList(nonPlayer.class);
        this.entitiesPlayer = entityManager.getEntitiesOfTypeList(Player.class);
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

    private boolean checkFutureCollision(int direction) {

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
            if (collisionManager.checkCollision(
                    futureX, futureY,
                    entityManager.getWidth(player), entityManager.getHeight(player),
                    entityManager.getxCords(entity), entityManager.getyCords(entity),
                    entityManager.getWidth(entity), entityManager.getHeight(entity))) {

//                collisionManager.checkResponse(player.getEntityType(), entity.getEntityType());
                return true;
            }
        }
        
        // Collision Detection for Player Entities
        for (Player otherPlayer : entitiesPlayer) {
            if (otherPlayer != player && collisionManager.checkCollision(
                    futureX, futureY,
                    entityManager.getWidth(player), entityManager.getHeight(player),
                    entityManager.getxCords(otherPlayer), entityManager.getyCords(otherPlayer),
                    entityManager.getWidth(otherPlayer), entityManager.getHeight(otherPlayer))) {

//                collisionManager.checkResponse(player.getEntityType(), otherPlayer.getEntityType());
                return true;
            }
        }

        return false; // no hit
    }

    private void right() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newX =  entityManager.getxCords(player) + moveAmount;
        entityManager.setxCords(player, newX);
    }

    private void left() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newX =  entityManager.getxCords(player) - moveAmount;
        entityManager.setxCords(player, newX);
    }

    private void up() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newY =  entityManager.getyCords(player) + moveAmount;
        entityManager.setyCords(player, newY);
    }

    private void down() {
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        float newY =  entityManager.getyCords(player) - moveAmount;
        entityManager.setyCords(player, newY);
    }

}
