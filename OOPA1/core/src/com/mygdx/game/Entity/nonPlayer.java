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
        System.out.println("NonPlayer Updated");
        
        // TODO: Implement actual update to nonPlayer during Part 2
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
    	// needs to be present here due to interface
    }
}