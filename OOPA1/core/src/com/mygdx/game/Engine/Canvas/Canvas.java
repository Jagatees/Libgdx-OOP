package com.mygdx.game.Engine.Canvas;

/**
 * Defines the basic structure and lifecycle methods for a canvas in the game.
 * A canvas represents a drawable area where game elements, UI components, or scenes can be rendered and updated.
 */
public interface Canvas {

    /**
     * Renders the content of the canvas.
     * This method should contain all drawing logic that needs to be executed every frame.
     *
     * @param delta The time in seconds since the last render call. Useful for frame-rate-independent rendering.
     */
    void render(float delta);

    /**
     * Updates the state of the canvas.
     * This method is called every frame and should contain logic to update game elements or UI components,
     * such as animations or input handling, based on the time elapsed since the last frame.
     *
     * @param delta The time in seconds since the last update. Useful for frame-rate-independent updates.
     */
    void update(float delta);

    /**
     * Disposes of resources used by the canvas.
     * This method should clean up resources like textures, sounds, or other disposables to prevent memory leaks.
     */
    void dispose();
}
