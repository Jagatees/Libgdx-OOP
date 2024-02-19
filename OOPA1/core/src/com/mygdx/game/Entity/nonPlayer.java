package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AI.AIControlManagement;
import com.mygdx.game.Entity.Entity.EntityState;
import com.mygdx.game.Entity.Entity.EntityType;

public class nonPlayer extends Entity {

    private Texture entityTexture;
    private boolean isWall;
    private AIControlManagement AIControlManagement;
    private Color color;

    // Method overloading to accept either SpriteBatch or ShapeRenderer arguments 
    public nonPlayer(String nonPlayerAsset, float xCords, float yCords, float speed,
                     EntityState state, boolean isAI, boolean isWall, float width, float height, EntityType entityType, RenderType renderType) {
        super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
        setTexture(new Texture(Gdx.files.internal(nonPlayerAsset)));
        setWall(isWall);
    }
    
    public nonPlayer(Color color, float xCords, float yCords, float speed,
			 EntityState state, boolean isAI, float width, float height, EntityType entityType, RenderType renderType) {
    	super(xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
        setColor(color);

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
    

	protected Color getColor() {
		return color;
	}
	
	void setColor(Color color) {
		this.color = color;;
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