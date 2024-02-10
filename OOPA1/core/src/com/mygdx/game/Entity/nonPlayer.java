package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class nonPlayer extends Entity {

    private Texture entityTexture;

    private boolean isWall;


    public nonPlayer(String nonPlayerAsset, float xCords, float yCords, float speed,
                     EntityState state, boolean isAI, boolean isWall, float width, float height) {
        super(xCords, yCords, speed, state, isAI, width, height);
        setTexture(new Texture(Gdx.files.internal(nonPlayerAsset)));
        setWall(isWall);
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
        spriteBatch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
    }

    // Getter & Setters for Players
    public Texture getTexture() {
        return entityTexture;
    }

    void setTexture(Texture tex) {
        entityTexture = tex;
    }





    public void movement() {
        if (isAI && !isWall) {
            AIControlledMovement();
        }
    }

    @Override
    public void AIControlledMovement() {
        // Actions for AI Controls
    }

    @Override
    public void userControlledMovement() {
        // Do nothing, as non-player but methods needs to be implemented
    }
}