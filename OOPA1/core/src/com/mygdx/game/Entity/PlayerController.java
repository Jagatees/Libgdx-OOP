package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;

public class PlayerController{

    private Player player;
    private float newX;
    private float newy;



    public PlayerController(Player player) {
        this.player = player;
    }

    Player getPlayer(){
        return this.player;
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



}

