package com.mygdx.game.Controller;

public interface EntityController {
    void left();
    void right();
    void up();
    void down();
    boolean checkFutureCollision(int direction);
}

