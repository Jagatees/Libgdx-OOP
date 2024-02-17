package com.mygdx.game.InputOutput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputOutputManger {
    public boolean isLeftPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    public boolean isRightPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public boolean isUpPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    public boolean isDownPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }



}
