package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {

    private Texture entityTexture;
    private PlayerController playerController;

    public Player(String playerAsset, float xCords, float yCords, float speed,
                  EntityState state, boolean isAI, int score, int numLives,
                  float width, float height, EntityType entityType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType);
        setTexture(new Texture(Gdx.files.internal(playerAsset)));
    }

    @Override
    protected void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
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
        if (!isAI) {
            userControlledMovement();
        } else if (isAI) {
            AIControlledMovement();
        }
    }

    @Override
    public void AIControlledMovement() {
        // Do nothing, as non-player but methods needs to be implemented
    }

    @Override
    public void userControlledMovement() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerController.move(Input.Keys.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerController.move(Input.Keys.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerController.move(Input.Keys.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerController.move(Input.Keys.DOWN);
        }
    }
}
