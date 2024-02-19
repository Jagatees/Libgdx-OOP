package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Controller.PlayerControllerManagement;

public class Player extends Entity {

    private Texture entityTexture;
    private PlayerControllerManagement playerControllerManagement;

    // Method overloading to accept either SpriteBatch or ShapeRenderer arguments 
    public Player(String playerAsset, float xCords, float yCords, float speed, EntityState state, boolean isAI, float width, float height, EntityType entityType,
                  RenderType renderType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
        setTexture(new Texture(Gdx.files.internal(playerAsset)));
    }

    public Player(Color color, float xCords, float yCords, float speed, EntityState state, boolean isAI, float width, float height, EntityType entityType, RenderType renderType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType, color);
    }
    
    // Setter for color

    @Override
    protected void render(SpriteBatch batch) {
    	batch.begin();
        batch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
        batch.end();
    }
    
    @Override
    protected void render(ShapeRenderer shape) {
    	shape.begin(ShapeRenderer.ShapeType.Filled); // Start drawing lines
        shape.setColor(getColor()); // Set the color for rendering
        shape.rect(getxCords(),getyCords(),getWidth(),getHeight());
        shape.end();
    }

    @Override
    protected void update(float deltaTime) {
        super.update(deltaTime);
    }

    protected Texture getTexture() {
        return entityTexture;
    }

    void setTexture(Texture tex) {
        entityTexture = tex;
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
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            getPlayerController().move(Input.Keys.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            getPlayerController().move(Input.Keys.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            getPlayerController().move(Input.Keys.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            getPlayerController().move(Input.Keys.DOWN);
        }
    }
}
