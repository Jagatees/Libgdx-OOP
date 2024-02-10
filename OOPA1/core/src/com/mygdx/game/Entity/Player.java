package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {

    private Texture entityTexture;

    public Player(String playerAsset, float xCords, float yCords, float speed,
                  EntityState state, boolean isAI, int score, int numLives,
                  float width, float height) {
        super(xCords, yCords, speed, state, isAI, width, height);
        setTexture(new Texture(Gdx.files.internal(playerAsset)));
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public Texture getTexture() {
        return entityTexture;
    }

    void setTexture(Texture tex) {
        entityTexture = tex;
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
