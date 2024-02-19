package com.mygdx.game.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


/**
 * Handles keyboard input detection for the game. Provides methods to check
 * if specific keys are pressed and to identify any key press events.
 */
public class Keyboard {

    /**
     * Initializes a new instance of the Keyboard class.
     * Prints a message to the console upon initialization for debugging purposes.
     */
    public Keyboard() {
        System.out.println("Keyboard Class Init");
    }

    /**
     * Checks if a specific key is currently pressed.
     *
     * @param keycode The keycode of the key to check. Use constants from {@link Input.Keys}.
     * @return true if the specified key is pressed, false otherwise.
     */
    public boolean isKeyPressedBool(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }


    /**
     * Checks if a specific key is currently pressed, returning an integer result.
     *
     * @param keycode The keycode of the key to check. Use constants from {@link Input.Keys}.
     * @return 1 if the specified key is pressed, 0 otherwise.
     */
    public int isKeyPressedInt(int keycode) {
        return Gdx.input.isKeyPressed(keycode) ? 1 : 0;
    }


}
