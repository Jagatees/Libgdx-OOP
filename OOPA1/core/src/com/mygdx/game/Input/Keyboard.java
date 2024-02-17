package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {

    // Boolean methods for checking key presses
    public boolean isKeyPressed(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }

    // Integer methods for converting key presses to 1 or 0
    public int getKeyState(int keycode) {
        return isKeyPressed(keycode) ? 1 : 0;
    }

}
