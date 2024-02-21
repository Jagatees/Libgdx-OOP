package com.mygdx.game.Canvas;


import java.util.List;

/**
 * Manages the active canvas within the game, facilitating switching between different canvases
 * (e.g., UI screens, game views) and ensuring proper resource management.
 */
public class CanvasManager {
    private static CanvasManager instance;
    private Canvas currentCanvas;

    /**
     * Constructs a CanvasManager with a reference to the game's scene manager.
     *
     */
    public CanvasManager() {

    }

    public static synchronized CanvasManager getInstance() {
        if (instance == null) {
            instance = new CanvasManager();
        }
        return instance;
    }

    /**
     * Sets the active canvas to the specified canvas, disposing of the previous canvas if one exists.
     * This ensures that resources are managed properly and that only one canvas is active at a time.
     *
     * @param canvas The new canvas to set as active.
     */
    public void setCanvas(Canvas canvas) {
        if (currentCanvas != null) {
            currentCanvas.dispose(); // Dispose of the current canvas to free resources.
        }
        currentCanvas = canvas; // Set the new canvas as the current canvas.
    }

    /**
     * Renders the current canvas if it is not null, passing along the delta time for frame-rate-independent rendering.
     *
     * @param delta The time in seconds since the last render call.
     */
    public void render(float delta) {
        if (currentCanvas != null) {
            currentCanvas.render(delta); // Delegate rendering to the current canvas.
        }
    }

    /**
     * Updates the state of the current canvas if it is not null, using the delta time for frame-rate-independent updates.
     *
     * @param delta The time in seconds since the last update.
     */
    public void update(float delta) {
        if (currentCanvas != null) {
            currentCanvas.update(delta); // Delegate state updates to the current canvas.
        }
    }

    /**
     * Disposes of the current canvas if it is not null, ensuring that all resources are properly released.
     * This method should be called when the canvas manager is being disposed of or when the game is closing.
     */
    public void dispose() {
        if (currentCanvas != null) {
            currentCanvas.dispose(); // Ensure resources of the current canvas are disposed.
        }
    }
}
