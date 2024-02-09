package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {

    private Texture entityTexture;
    private float entityHeight;
    private float entityWidth;
    private int score;
    private int numLives;

    public Player(String playerAsset, float xCords, float yCords, float speed, EntityState state, boolean isAI, int score, int numLives) {
        super(xCords, yCords, speed, state, isAI);
        this.entityTexture = new Texture(Gdx.files.internal(playerAsset));
        this.entityHeight = entityTexture.getHeight();
        this.entityWidth = entityTexture.getWidth();

        this.score = score;
        this.numLives = numLives;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(entityTexture, xCords, yCords, entityHeight, entityWidth);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
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


    // Getters & Setters for Score

    public int getScore() {
        return score;
    }

    void setScore(int newScore) {
        score = newScore;
    }


    // Getters & Setters for No. of Lives
    public int getLives() {
        return numLives;
    }

    void setLives(int newLifeCount) {
        numLives = newLifeCount;
    }

    public void movement() {
        if (!isAI) {
            userControlledMovement();
        }
    }

    @Override
    public void AIControlledMovement() {
        // Do nothing, as non-player but methods needs to be implemented
    }

    @Override
    public void userControlledMovement() {
        // Actions for User Controls
    }
}
