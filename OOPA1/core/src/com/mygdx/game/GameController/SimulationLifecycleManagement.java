package com.mygdx.game.GameController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Canvas.CanvasManager;
import com.mygdx.game.Canvas.MainMenuCanvas;
import com.mygdx.game.Canvas.OptionsCanvas;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.Scene;
import com.mygdx.game.Scenes.SceneManager;

/**
 * Manages the lifecycle of the simulation or game, including starting, updating, rendering,
 * and closing the game. This class implements the Singleton pattern to ensure that only one
 * instance manages the game lifecycle globally.
 */
public class SimulationLifecycleManagement {

    /** Singleton instance */
    private static SimulationLifecycleManagement instance;
    /** Manages the scenes within the game */
    private SceneManager sceneManager;
    private boolean isPaused = false; // Pause flag

    public boolean isPaused() {
        return this.isPaused;
    }

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    public SimulationLifecycleManagement() {
        sceneManager = SceneManager.getInstance();

    }


    public void togglePause() {
        System.out.println(this.isPaused);
        this.isPaused = !this.isPaused;
    }




    /**
     * Returns the singleton instance of the SimulationLifecycleManagement class,
     * creating it if it does not already exist.
     *
     * @return The singleton instance of the SimulationLifecycleManagement.
     */
    public static SimulationLifecycleManagement getInstance() {
        if (instance == null) {
            instance = new SimulationLifecycleManagement();
        }
        return instance;
    }

    /**
     * Renders the current scene by delegating to the scene manager.
     */
    public void render(){
        this.sceneManager.render();


    }

    /**
     * Updates the current scene by delegating to the scene manager, allowing the scene to
     * process logic such as input handling and game physics.
     *
     * @param dt The time in seconds since the last update.
     */
    public void update(float dt){
        sceneManager.update(dt);
    }

    /**
     * Starts the game by setting the initial scene. This method should be called once
     * when the game is first started.
     *
     */

    public void startGame() {
        sceneManager.setScene("MainMenu");
    }


    /**
     * Closes the game by exiting the application. This method can be called to
     * programmatically end the game.
     */
    public void closeGame() {
        Gdx.app.exit();
    }

    /**
     * Disposes of resources used by the scene manager. This method should be called
     * to clean up resources when the game is closing.
     */
    public void dispose(){
        sceneManager.dispose();
    }
}
