package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class nonPlayer extends Entity {

    private Texture entityTexture;
    private float entityHeight;
    private float entityWidth;
    private boolean isWall;


    public nonPlayer(String nonPlayerAsset, float xCords, float yCords, float speed, EntityState state, boolean isAI, boolean isWall) {
        super(xCords, yCords, speed, state, isAI);
        this.entityTexture = new Texture(Gdx.files.internal(nonPlayerAsset));
        this.entityHeight = entityTexture.getHeight();
        this.entityWidth = entityTexture.getWidth();
        this.isWall = isWall;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
        spriteBatch.draw(entityTexture, xCords, yCords, entityHeight, entityWidth);
    }

    // Getter & Setters for Players
    public Texture getTexture() {
        return entityTexture;
    }

    void setTexture(Texture tex) {
        entityTexture = tex;
    }

    // Getter & Setters for Height, Width
    public float getHeight() {
        return entityHeight;
    }

    void setHeight(float height) {
        entityHeight = height;
    }

    public float getWidth() {
        return entityWidth;
    }

    void setWidth(float width) {
        entityWidth = width;
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