package com.mygdx.game.Scenes;

public class SceneManager {
    private Scene currentScene;

    public void setScene(Scene scene) {
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scene;
        currentScene.create();
    }

    public void render() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    public void update(float delta) {
        if (currentScene != null) {
            currentScene.update(delta);
        }
    }

    public void dispose() {
        if (currentScene != null) {
            currentScene.dispose();
        }
    }
}
