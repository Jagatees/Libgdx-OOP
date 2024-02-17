package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.InputOutput.InputOutputManger;

public class PlayerController{

    private Player player;
    private InputOutputManger inputManager;
    private CollisionManager collisionManager;
    private EntityManager entityManager;

    private float newX;
    private float newy;


    public PlayerController(Player player) {
        this.player = player;
        this.inputManager = new InputOutputManger();
        this.collisionManager = new CollisionManager(); // Initialize CollisionManager

    }

    Player getPlayer(){
        return this.player;
    }

    public void update() {
        if(inputManager.isLeftPressed()) {
            left();
        } else if(inputManager.isRightPressed()) {
            right();
        } else if(inputManager.isUpPressed()) {
            up();
        } else if(inputManager.isDownPressed()) {
            down();
        } else {
            stop();
        }
    }



    public void right() {
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

