package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class nonPlayer extends Entity {

    private Texture entityTexture;
    private boolean isWall;
    private int currentDirection = MathUtils.random(1,4); // 1=UP, 2=DOWN, 3=LEFT, 4=RIGHT
    private float directionChangeTimer;

    public nonPlayer(String nonPlayerAsset, float xCords, float yCords, float speed,
                     EntityState state, boolean isAI, boolean isWall, float width, float height, EntityType entityType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType);
        setTexture(new Texture(Gdx.files.internal(nonPlayerAsset)));
        setWall(isWall);
    }

    protected boolean isWall() {
        return isWall;
    }

    protected void setWall(boolean wall) {
        isWall = wall;
    }

    @Override
    protected void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    protected void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
        spriteBatch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
    }

    // Getter & Setters for Players
    protected Texture getTexture() {
        return entityTexture;
    }

    void setTexture(Texture tex) {
        entityTexture = tex;
    }

    protected void movement() {
        if (isAI && !isWall) {
            AIControlledMovement();
        }
    }

    @Override
    public void AIControlledMovement() {
        // Actions for AI Controls
        // Update timer
        directionChangeTimer += Gdx.graphics.getDeltaTime();

        // Check if it's time to change direction
        if (directionChangeTimer >= 1.0f) {
            directionChangeTimer = 0; // Reset timer
            currentDirection = MathUtils.random(1, 4); // Choose a new direction randomly
        }

        // Move in the current direction
        float moveAmount = 20 * Gdx.graphics.getDeltaTime(); // Adjust speed as needed
        switch (currentDirection) {
            case 1: // UP
                setyCords(getyCords() + moveAmount);
                break;
            case 2: // DOWN
                setyCords(getyCords() - moveAmount);
                break;
            case 3: // LEFT
                setxCords(getxCords() - moveAmount);
                break;
            case 4: // RIGHT
                setxCords(getxCords() + moveAmount);
                break;
        }
    }

    @Override
    public void userControlledMovement() {
        // Implementation in Player class, but methods still needs to be present here due to interface
    }
}