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




    public void update(float deltaTime) {
        // Implement logic updates here
    }

    public void render(SpriteBatch spriteBatch) {
        // Implement rendering logic here
    }

    // Getter & Setters for (x,y) coordinates

    public float getxCords() {
        return xCords;
    }

    void setxCords(float x) {
        xCords = x;
    }

    public float getyCords() {
        return yCords;
    }

    void setyCords(float y) {
        yCords = y;
    }

    // Getter & Setters for Speed

    public float getSpeed() {
        return speed;
    }

    void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    // Getter & Setters for State

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    // Getter & Setters for isAI

    public boolean getisAI() {
        return isAI;
    }

    // Probably not required to change state of isAI

    void setisAI(boolean newisAI) {
        isAI = newisAI;
    }

    public void movement() {
        if (isAI) {
            AIControlledMovement();
        }

        else {
            userControlledMovement();
        }
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}