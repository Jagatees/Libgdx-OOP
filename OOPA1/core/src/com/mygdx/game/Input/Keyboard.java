package com.mygdx.game.Input;
import com.badlogic.gdx.Gdx;

public class Keyboard {

    public Keyboard() {
        System.out.println("Init Keyboard");
    }

    public boolean isKeyPressedBool(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }

    public int isKeyPressedInt(int keycode) {
        return Gdx.input.isKeyPressed(keycode) ? 1 : 0;
    }

}
