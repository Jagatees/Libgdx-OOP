package com.mygdx.game.Input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Entity.PlayerController;

public class InputManager implements InputProcessor {

    private final PlayerController playerController;

    // Movement flags
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;


    public InputManager(PlayerController playerController) {
        this.playerController = playerController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                moveRight = true;
                break;
            case Input.Keys.LEFT:
                moveLeft = true;
                break;
            case Input.Keys.UP:
                moveUp = true;
                break;
            case Input.Keys.DOWN:
                moveDown = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                moveRight = false;
                break;
            case Input.Keys.LEFT:
                moveLeft = false;
                break;
            case Input.Keys.UP:
                moveUp = false;
                break;
            case Input.Keys.DOWN:
                moveDown = false;
                break;
        }
        return true;
    }

    // Methods to check the movement flags
    public boolean isMovingLeft() {
        return moveLeft;
    }

    public boolean isMovingRight() {
        return moveRight;
    }

    public boolean isMovingUp() {
        return moveUp;
    }

    public boolean isMovingDown() {
        return moveDown;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
