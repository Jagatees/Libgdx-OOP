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
        addScreen("Game", new GameScreen());
        addScreen("Main", new MainScreen());
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

    public static Screen getCurrentScreen() {
        return currentScreen;
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
    }
}
