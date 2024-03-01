package com.mygdx.game.Engine.Scenes;



/**
 * Defines the lifecycle and core functionality of a game scene.
 * Scenes represent different stages or states of the game, such as menus, levels, or pause screens.
 */
public interface Scene {

    /**
     * Initializes the scene. This method is called when the scene is first created.
     * Use this to set up initial state, load resources, and prepare any necessary elements for the scene.
     */
    void create();

    /**
     * Renders the scene. This method is called every frame to draw the scene's contents.
     * Implementations should contain all drawing logic here.
     */
    void render();

    /**
     * Updates the scene's state. This method is called every frame and allows the scene to update its logic.
     *
     * @param delta The time in seconds since the last frame. This is used for frame-rate independent movement and logic.
     */
    void update(float delta);

    /**
     * Disposes of the scene's resources. This method is called when the scene is no longer needed.
     * Implementations should dispose of all resources (e.g., textures, sounds) that were allocated in create() or during the scene's lifetime.
     */
    void dispose();
}
