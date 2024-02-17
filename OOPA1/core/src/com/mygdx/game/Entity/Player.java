package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Player extends Entity {

    private Texture entityTexture;
    private PlayerController playerController;
    private int currentDirection; // 1=UP, 2=DOWN, 3=LEFT, 4=RIGHT
    private float directionChangeTimer;
    public Player(String playerAsset, float xCords, float yCords, float speed,
                  EntityState state, boolean isAI, float width, float height, EntityType entityType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType);
        setTexture(new Texture(Gdx.files.internal(playerAsset)));
        currentDirection = MathUtils.random(1, 4);

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
        if (!getisAI()) {
            userControlledMovement();
        } else if (getisAI()) {
            AIControlledMovement();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    @Override
    public void AIControlledMovement() {

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

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
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
