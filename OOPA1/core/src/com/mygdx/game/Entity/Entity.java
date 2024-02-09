package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements objectMovement {

    protected float xCords, yCords, speed;
    protected EntityState state; // Assuming an enum is used for state management
    protected boolean isAI;

    Entity(float xCords, float yCords, float speed, EntityState state, boolean isAI) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
    }

    public enum EntityState {
        CHASE, PRESENT
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

    // Overloading for drawMethod from individual sub-class

    public void draw(SpriteBatch batch) {
        // Do nothing, sub-class will handle this according to their own logic
    }

    //

    public void movement() {
        if (isAI) {
            AIControlledMovement();
        }

        else {
            userControlledMovement();
        }
    }


}