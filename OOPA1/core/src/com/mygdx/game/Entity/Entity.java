package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity implements objectMovement {

    protected float xCords, yCords, speed;
    protected EntityState entityState;
    protected EntityType entityType;
    protected RenderType renderType;

    protected boolean isAI;
    protected float width, height;


    public enum EntityType {
        NULL,
    }

    public enum EntityState {
        NULL,
    }

    public enum RenderType {
        SHAPE, SPRITE
    }
    
    Entity(float xCords, float yCords, float speed, EntityState state,
           boolean isAI, float width, float height, EntityType entityType, RenderType renderType) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
        setWidth(width);
        setHeight(height);
        setEntityType(entityType);
        setRenderType(renderType);
    }

    public void setRenderType(Entity.RenderType renderType) {
        this.renderType = renderType;
    }

    public RenderType getRenderType() {
       return renderType;
    }


    protected void update(float deltaTime) {
        // Implement logic updates here
    }

    protected void render(SpriteBatch spriteBatch) {
        // Subclass to take over
    }

    
    protected void render(ShapeRenderer shape) {
    	// Subclass to take over
    }
    
    
    // Getter & Setters for (x,y) coordinates

    protected float getxCords() {
        return xCords;
    }

    void setxCords(float x) {
        xCords = x;
    }

    protected float getyCords() {
        return yCords;
    }

    void setyCords(float y) {
        yCords = y;
    }

    // Getter & Setters for Speed

    protected float getSpeed() {
        return speed;
    }

    protected EntityState getEntityState() {
        return entityState;
    }

    protected void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

    protected EntityType getEntityType() {
        return entityType;
    }

    protected void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    // Getter & Setters for State

    protected EntityState getState() {
        return entityState;
    }

    void setState(EntityState state) {
        this.entityState = state;
    }

    // Getter & Setters for isAI

    protected boolean getisAI() {
        return isAI;
    }

    // Probably not required to change state of isAI

    void setisAI(boolean newisAI) {
        isAI = newisAI;
    }

    protected void movement() {
        if (isAI) {
            AIControlledMovement();
        }

        else {
            userControlledMovement();
        }
    }

    protected boolean isAI() {
        return isAI;
    }

    protected void setAI(boolean AI) {
        isAI = AI;
    }

    protected float getWidth() {
        return width;
    }

    protected void setWidth(float width) {
        this.width = width;
    }

    protected float getHeight() {
        return height;
    }

    protected void setHeight(float height) {
        this.height = height;
    }
}