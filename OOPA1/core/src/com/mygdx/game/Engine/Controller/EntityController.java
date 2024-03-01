package com.mygdx.game.Engine.Controller;

public interface EntityController {
    void left();
    void right();
    void up();
    void down();
    boolean checkFutureCollision(int direction);
}

