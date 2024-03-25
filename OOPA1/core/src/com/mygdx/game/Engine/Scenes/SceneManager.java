package com.mygdx.game.Engine.Scenes;


import com.mygdx.game.LearningGame.Scene.*;
import com.mygdx.game.LearningGame.Scene.GameScene;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the current scene within the game, handling transitions between scenes
 * and delegating the lifecycle management (creation, rendering, updating, and disposal)
 * of the active scene.
 */

public class SceneManager {

    /*** Holds the currently active scene, managing its lifecycle.*/
    private Scene currentScene;
    private static SceneManager instance;
    private Map<String, Scene> scenes;



    private SceneManager() {
        // Private constructor to prevent instantiation
        scenes = new HashMap<>();
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
     */
    public void setScene(String sceneId) {
        Scene scene = scenes.get(sceneId);
        // Check if the scene already exists
        if (scene == null) {
            // If the scene does not exist, create it and add it to the map
            // This assumes a method to create scenes based on an identifier
            scene = createSceneById(sceneId);
            scenes.put(sceneId, scene);
        }

//        Remove the nul setting somthing i still want to go back to the scene
//        // Dispose of the current scene if one is active
//        if (currentScene != null) {
//            currentScene.dispose();
//        }

        // Set the new scene and initialize it
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


    private Scene createSceneById(String sceneId) {
        // Implement scene creation logic here
        // Example:
         switch (sceneId) {
             case "MainMeun":
                 return new MainMenuScene();
             case "GameStage":
                 return new GameScene();
             case "HardStage":
                 return new HardScene();
             case "GameOver":
                 return new GameOverScene();
         }
        return null; // Placeholder
    }
}
