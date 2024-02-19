package com.mygdx.game.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {

    protected boolean isKeyPressedBool(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }

    protected int isKeyPressedInt(int keycode) {
        return Gdx.input.isKeyPressed(keycode) ? 1 : 0;
    }

    // New method to get directional input
    protected Integer getDirectionalInput() {
        if (isKeyPressedBool(Input.Keys.LEFT)) {
            return Input.Keys.LEFT;
        } else if (isKeyPressedBool(Input.Keys.RIGHT)) {
            return Input.Keys.RIGHT;
        } else if (isKeyPressedBool(Input.Keys.UP)) {
            return Input.Keys.UP;
        } else if (isKeyPressedBool(Input.Keys.DOWN)) {
            return Input.Keys.DOWN;
        }
        return null; // No directional input detected
    }

}
