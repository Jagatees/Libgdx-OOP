package com.mygdx.game.Canvas;

public interface Screen {
    void create();
    void render();
    void update(float delta);
    void resize(int width, int height);
    void dispose();
}
