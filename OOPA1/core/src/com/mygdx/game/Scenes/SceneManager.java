package com.mygdx.game.Scenes;


/**
 * Manages the current scene within the game, handling transitions between scenes
 * and delegating the lifecycle management (creation, rendering, updating, and disposal)
 * of the active scene.
 */

public class SceneManager {

    /*** Holds the currently active scene, managing its lifecycle.*/
    private Scene currentScene;
    private static SceneManager instance;


    private SceneManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    /**
     * Sets the current scene, disposes of the existing scene if one is active,
     * and initializes the new scene.
     *
     * @param scene The new scene to set as the current scene.
     */
    public void setScene(Scene scene) {
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scene;
        currentScene.create();
    }

    /**
     * Returns the currently active scene.
     *
     * @return The current scene.
     */
    public Scene getScene() {
        return currentScene;
    }

    /**
     * Renders the current scene if it is not null. This method should be called
     * every frame to draw the scene's contents.
     */
    public void render() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    /**
     * Updates the current scene's state. This method should be called every frame
     * to update the scene's logic based on the time passed since the last frame.
     *
     * @param delta The time in seconds since the last update.
     */
    public void update(float delta) {
        if (currentScene != null) {
            currentScene.update(delta);
        }
    }


    /**
     * Disposes of the current scene's resources. This method should be called
     * when the scene manager is no longer needed or when the game is closing,
     * to ensure all resources are properly freed.
     */
    public void dispose() {
        if (currentScene != null) {
            currentScene.dispose();
        }
    }
}
