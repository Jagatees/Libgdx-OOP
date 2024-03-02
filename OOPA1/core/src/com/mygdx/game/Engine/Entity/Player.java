package com.mygdx.game.Engine.Entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.Input.Keyboard;

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
        if (InputOutputManager.getInstance().getKeyboard().getInstance().isActionPressed("MOVE_RIGHT")) {
            getPlayerController().move(Input.Keys.RIGHT);
        } else if (InputOutputManager.getInstance().getKeyboard().getInstance().isActionPressed("MOVE_LEFT")) {
            getPlayerController().move(Input.Keys.LEFT);
        } else if (InputOutputManager.getInstance().getKeyboard().getInstance().isActionPressed("MOVE_UP")) {
            getPlayerController().move(Input.Keys.UP);
        } else if (InputOutputManager.getInstance().getKeyboard().getInstance().isActionPressed("MOVE_DOWN")) {
            getPlayerController().move(Input.Keys.DOWN);
        }
    }
}
