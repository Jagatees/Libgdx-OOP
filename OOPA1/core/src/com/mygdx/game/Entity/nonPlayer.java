package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Controller.AIControlManagement;

public class nonPlayer extends Entity {

    private boolean isWall;
    private AIControlManagement AIControlManagement;

    // Method overloading to accept either SpriteBatch or ShapeRenderer arguments 
    public nonPlayer(String tex, float xCords, float yCords, float speed,
                     EntityState state, boolean isAI, boolean isWall, float width, float height, EntityType entityType, RenderType renderType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType, tex);
        // setTexture(new Texture(Gdx.files.internal(nonPlayerAsset)));
        setWall(isWall);
    }
    
    public nonPlayer(Color color, float xCords, float yCords, float speed,
			 EntityState state, boolean isAI, boolean isWall, float width, float height, EntityType entityType, RenderType renderType) {
    	super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType, color);
        setColor(color);
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
    protected void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
        batch.end();
    }
    
    protected void render(ShapeRenderer shape) {
    	shape.begin(ShapeRenderer.ShapeType.Filled); // Start drawing lines
        shape.setColor(getColor()); // Set the color for rendering
        shape.rect(getxCords(),getyCords(),getWidth(),getHeight());
        shape.end();
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
        // Implementation in Player class, but methods still
    	// needs to be presenthere due to interface
    }
}