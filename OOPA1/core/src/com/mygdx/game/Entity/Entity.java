package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements objectMovement {

    protected float xCords, yCords, speed;
    protected EntityState state; // Assuming an enum is used for state management
    protected boolean isAI;
    protected float width, height;

    public enum EntityState {
        NULL,
    }

    Entity(float xCords, float yCords, float speed, EntityState state,
           boolean isAI, float width, float height) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
        setWidth(width);
        setHeight(height);
    }
    
    Entity(float xCords, float yCords, float speed, EntityState state,
            boolean isAI, boolean isWall, float width, float height) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
     }

    
    protected void update(float deltaTime) {
        // Implement logic updates here
    }
    
    // Method overriding will handle this portion
    protected void render(SpriteBatch spriteBatch) {
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

    void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    // Getter & Setters for State

    protected EntityState getState() {
        return state;
    }

    void setState(EntityState state) {
        this.state = state;
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