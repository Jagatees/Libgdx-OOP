package com.mygdx.game.Engine.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import java.util.HashMap;
import java.util.Map;

public class Keyboard {

    private static Keyboard instance;
    private HashMap<String, Integer> keyBindings;

    public Keyboard() {
        System.out.println("done One");
        keyBindings = new HashMap<String, Integer>();
        // Default key bindings can be set here
        bindDefaultKeys();
    }

    public static synchronized Keyboard getInstance() {
        if (instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }

    private void bindDefaultKeys() {
        // Default key bindings
        keyBindings.put("MOVE_LEFT", Keys.A);
        keyBindings.put("MOVE_RIGHT", Keys.D);
        keyBindings.put("MOVE_UP", Keys.W);
        keyBindings.put("MOVE_DOWN", Keys.S);

    }

    public void setKeyBinding(String action, int keycode) {
        keyBindings.put(action, keycode);
    }

    public boolean isActionPressed(String action) {
        Integer keycode = keyBindings.get(action);
        if (keycode != null) {
            return Gdx.input.isKeyPressed(keycode);
        }
        return false;
    }


    public int convertLetterToLibGDXKeycode(char letter) {
        // LibGDX's Keys.A to Keys.Z correspond to ASCII 'A' to 'Z'
        if (letter >= 'A' && letter <= 'Z') {
            return letter - 'A' + Input.Keys.A;
        }
        // Add similar conversion for lowercase letters if necessary
        return -1; // Return an invalid keycode by default
    }

    public void printKeyBindings() {
        System.out.println("Current Key Bindings:");
        for (Map.Entry<String, Integer> entry : keyBindings.entrySet()) {
            String action = entry.getKey();
            int keycode = entry.getValue();
            System.out.println(action + " : " + Keys.toString(keycode));
        }
    }


    // Additional methods for checking key press status can be kept
    // or modified as per your requirements.

    public boolean isKeyPressedBool(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }

    public int isKeyPressedInt(int keycode) {
        return Gdx.input.isKeyPressed(keycode) ? 1 : 0;
    }

    public Integer getPressedKeycode() {
        for (String action : keyBindings.keySet()) {
            int keycode = keyBindings.get(action);
            if (Gdx.input.isKeyPressed(keycode)) {
                return keycode;
            }
        }
        return null;
    }
}
