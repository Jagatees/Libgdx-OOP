package com.mygdx.game.InputOutput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputOutputManger {
    // Boolean return functions
    public boolean isUpPressedBool() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    public boolean isDownPressedBool() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    public boolean isRightPressedBool() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public boolean isLeftPressedBool() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    // Integer return functions
    public int isUpPressedInt() {
        return Gdx.input.isKeyPressed(Input.Keys.UP) ? 1 : 0;
    }

    public int isDownPressedInt() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN) ? 1 : 0;
    }

    public int isRightPressedInt() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) ? 1 : 0;
    }

    public int isLeftPressedInt() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) ? 1 : 0;
    }


}
