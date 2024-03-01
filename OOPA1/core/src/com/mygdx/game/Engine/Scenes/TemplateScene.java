package com.mygdx.game.Engine.Scenes;

/**
 * A template scene that provides basic implementations of the Scene interface.
 * This class serves as a foundation for more specific scene types within the game.
 * Implementors can extend this class to create concrete scenes, overriding
 * lifecycle methods as needed.
 */
public class TemplateScene implements Scene {

    /**
     * Initializes the scene. This method is called when the scene is first created.
     * Override this method to set up initial state, load resources, and prepare
     * any necessary elements for the scene.
     */
    @Override
    public void create() {
        // Initial setup and resource loading
    }

    /**
     * Renders the scene. This method is called every frame to draw the scene's contents.
     * Override this method to implement the drawing logic for the scene.
     */
    @Override
    public void render() {
        // Drawing code for the scene
    }

    /**
     * Updates the scene's state. This method is called every frame and allows
     * the scene to update its logic based on the time since the last frame.
     *
     * @param delta The time in seconds since the last update.
     */
    @Override
    public void update(float delta) {
        // Logic to update the scene state
    }

    /**
     * Disposes of the scene's resources. This method is called when the scene
     * is no longer needed. Override this method to clean up resources like textures
     * and sounds that were allocated in create() or during the scene's lifetime.
     */
    @Override
    public void dispose() {
        // Cleanup resources
    }
}
