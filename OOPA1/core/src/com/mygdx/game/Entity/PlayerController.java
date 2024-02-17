package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Collision.CollisionManager;

import java.util.List;

public class PlayerController{

    private Player player;
    private float newX;
    private float newy;

    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private List<nonPlayer> entities;


    public PlayerController(Player player, EntityManager entityManager, CollisionManager collisionManager) {
        this.player = player;
        this.entityManager = entityManager;
        this.player = entityManager.getEntity(Player.class);
        this.collisionManager = collisionManager;
        this.entities = entityManager.getEntitiesOfTypeList(nonPlayer.class);
    }

    Player getPlayer(){
        return this.player;
    }



    public void move(int direction) {
        if (!checkFutureCollision(direction)) {
            switch (direction) {
                case Input.Keys.LEFT: left(); break;
                case Input.Keys.RIGHT: right(); break;
                case Input.Keys.UP: up(); break;
                case Input.Keys.DOWN: down(); break;
            }
        }
    }

    private boolean checkFutureCollision(int direction) {
        float futureX = entityManager.getxCords(getPlayer());
        float futureY = entityManager.getyCords(getPlayer());
        float speed = entityManager.getSpeed(getPlayer());

        // Calculate future position based on direction
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

        for (nonPlayer entity : entities) {
            if (collisionManager.checkCollision(futureX, futureY, player.getWidth(), player.getHeight(),
                    entityManager.getxCords(entity), entityManager.getyCords(entity), entityManager.getWidth(entity), entityManager.getHeight(entity))) {
                // Later one handle if some collision happen
                return true;
            }

        }

        return false; // no hit
    }



    public void right(){
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        newX = getPlayer().getxCords();
        newX += moveAmount;
        getPlayer().setxCords(newX);
    }

    public void left(){
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        newX = getPlayer().getxCords();
        newX -= moveAmount;
        getPlayer().setxCords(newX);
    }

    public void up(){
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        newy = getPlayer().getyCords();
        newy += moveAmount;
        getPlayer().setyCords(newy);
    }


    public void down(){
        float moveAmount = 200 * Gdx.graphics.getDeltaTime();
        newy = getPlayer().getyCords();
        newy -= moveAmount;
        getPlayer().setyCords(newy);
    }

    public void stop(){
        getPlayer().setxCords(getPlayer().xCords);
        getPlayer().setyCords(getPlayer().yCords);
    }



}

