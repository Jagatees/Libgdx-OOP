package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AI.AIControlManagement;

public class nonPlayer extends Entity {

    private Texture entityTexture;
    private boolean isWall;
    private com.mygdx.game.AI.AIControlManagement AIControlManagement;

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
    
    protected AIControlManagement getAIController() {
        return AIControlManagement;
    }
    
    protected void setAIController(AIControlManagement aiControlManagement) {
        this.AIControlManagement = aiControlManagement;
    }
    
    @Override
    public void AIControlledMovement() {
    	AIControlManagement.aiMove();
    }

    @Override
    public void userControlledMovement() {
        // Implementation in Player class, but methods still needs to be present here due to interface
    }
}