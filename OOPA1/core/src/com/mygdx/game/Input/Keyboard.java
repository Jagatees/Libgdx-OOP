package com.mygdx.game.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {

    private static Keyboard instance;

    public void Keyboard(){

    }

    public static synchronized Keyboard getInstance() {
        if (instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }

    public boolean isKeyPressedBool(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }

    public int isKeyPressedInt(int keycode) {
        return Gdx.input.isKeyPressed(keycode) ? 1 : 0;
    }

    // Function to return the keycode if it is pressed
    public Integer getPressedKeycode(int keycode) {
        if (Gdx.input.isKeyPressed(keycode)) {
            return keycode; // Return the keycode if it is pressed
        }
        return null; // Return null if the key is not pressed
    }





}
