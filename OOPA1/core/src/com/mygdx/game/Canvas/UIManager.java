package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;

import java.util.HashMap;

public class UIManager {
    private HashMap<String, Screen> screens;
    private static Screen currentScreen;


    public UIManager() {
        screens = new HashMap<>();
    }

    // Method to change screen based on certain conditions (e.g., player death)
    public void changeScreenOnPlayerDeath(EntityManager entityManager, Player player) {
        if (entityManager.getxCords(player) > 100) {
            setScreen("GameScreen");

        }
    }

    public void addScreen(String name, Screen screen) {
        screens.put(name, screen);
    }

    public void setScreen(String name) {
        if (screens.containsKey(name)) {
            if (currentScreen != null) {
                currentScreen.dispose();
            }
            currentScreen = screens.get(name);
            currentScreen.create();
        } else {
            throw new IllegalArgumentException("Screen with name " + name + " does not exist.");
        }
    }

    public static void render() {
        if (currentScreen != null) {
            currentScreen.render();
        }
    }

    public void update(float delta) {
        if (currentScreen != null) {
            currentScreen.update(delta);
        }
    }

    public void resize(int width, int height) {
        if (currentScreen != null) {
            currentScreen.resize(width, height);
        }
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
    }
}
