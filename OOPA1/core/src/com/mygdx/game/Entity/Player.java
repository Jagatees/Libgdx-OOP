package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Controller.PlayerControllerManagement;
import com.mygdx.game.Input.Keyboard;

public class Player extends Entity {

    private PlayerControllerManagement playerControllerManagement;

    // Method overloading to accept either SpriteBatch or ShapeRenderer arguments
    public Player(String tex, float xCords, float yCords, float speed, EntityState state, boolean isAI, float width, float height, EntityType entityType,
                  RenderType renderType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType, tex);
    }

    public Player(Color color, float xCords, float yCords, float speed, EntityState state, boolean isAI, float width, float height, EntityType entityType, RenderType renderType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType, color);
    }

    @Override
    protected void update(float deltaTime) {
        System.out.println("Example player updated.");
        
        // TODO: Implement actual update to nonPlayer during Part 2
    }

    protected void movement() {
        if (!getisAI()) {
            userControlledMovement();
        } else if (getisAI()) {
            AIControlledMovement();
        }
    }

    protected PlayerControllerManagement getPlayerController() {
        return playerControllerManagement;
    }
    
    protected void setPlayerController(PlayerControllerManagement playerControllerManagement) {
        this.playerControllerManagement = playerControllerManagement;
    }

    @Override
    public void AIControlledMovement() {
    	// Implementation in nonPlayer class
    }

    @Override
    public void userControlledMovement() {
        if (Keyboard.getInstance().isKeyPressedBool(Input.Keys.RIGHT)) {
            getPlayerController().move(Input.Keys.RIGHT);
        } else if (Keyboard.getInstance().isKeyPressedBool(Input.Keys.LEFT)) {
            getPlayerController().move(Input.Keys.LEFT);
        } else if (Keyboard.getInstance().isKeyPressedBool(Input.Keys.UP)) {
            getPlayerController().move(Input.Keys.UP);
        } else if (Keyboard.getInstance().isKeyPressedBool(Input.Keys.DOWN)) {
            getPlayerController().move(Input.Keys.DOWN);
        }
    }
}
