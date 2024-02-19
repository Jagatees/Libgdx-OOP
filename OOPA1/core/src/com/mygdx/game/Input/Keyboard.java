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

    protected int getKeyPressed() {
        for (int keycode = Input.Keys.A; keycode <= Input.Keys.Z; keycode++) {
            if (Gdx.input.isKeyPressed(keycode)) {
                return keycode; // Return the first pressed key found
            }
        }
        return -1;
    }

}
