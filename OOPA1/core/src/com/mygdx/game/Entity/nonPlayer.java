package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.AI.AIController;

public class nonPlayer extends Entity {

    private Texture entityTexture;
    private boolean isWall;
    private AIController AIController;

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
    
    public AIController getAIController() {
        return AIController;
    }
    
    public void setAIController(AIController aiController) {
        this.AIController = aiController;
    }
    
    @Override
    public void AIControlledMovement() {
    	AIController.aiMove();
    }

    @Override
    public void userControlledMovement() {
        // Implementation in Player class, but methods still needs to be present here due to interface
    }
}