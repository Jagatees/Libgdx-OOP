package com.mygdx.game.Engine.Canvas;

/**
 * Manages the active canvas within the game, facilitating switching between different canvases
 * (e.g., UI screens, game views) and ensuring proper resource management.
 */
public class CanvasManager {
    private static CanvasManager instance;
    private Canvas currentCanvas;
    private Canvas previousCanvas;

    /**
     * Constructs a CanvasManager with a reference to the game's scene manager.
     */
    private CanvasManager() {
        // Add any initialization code if needed
    }

    /**
     * Retrieves the singleton instance of CanvasManager.
     * Ensures thread safety during instantiation.
     *
     * @return The singleton instance of CanvasManager.
     */
    public static synchronized CanvasManager getInstance() {
        if (instance == null) {
            instance = new CanvasManager();
        }
        return instance;
    }

    /**
     * Sets the active canvas to the specified canvas, disposing of the previous canvas if one exists.
     * This ensures that resources are managed properly and that only one canvas is active at a time.
     * Saves the previous canvas before switching.
     *
     * @param canvas The new canvas to set as active.
     */
    public void setCanvas(Canvas canvas) {
        try {
            if (currentCanvas != null) {
                previousCanvas = currentCanvas; // Save the current canvas as the previous canvas.
                currentCanvas.dispose(); // Dispose of the current canvas to free resources.
            }
            currentCanvas = canvas; // Set the new canvas as the current canvas.
        } catch (Exception e) {
            // Handle any exceptions that occur during canvas switching
            e.printStackTrace();
        }
    }

    /**
     * Switches back to the previous canvas if available.
     */
    public void switchToPreviousCanvas() {
        if (previousCanvas != null) {
            setCanvas(previousCanvas); // Set the previous canvas as the current canvas.
            previousCanvas = null; // Reset the previous canvas since it's no longer needed.
        }
    }

    /**
     * Renders the current canvas if it is not null, passing along the delta time for frame-rate-independent rendering.
     *
     * @param delta The time in seconds since the last render call.
     */
    public void render(float delta) {
        try {
            if (currentCanvas != null) {
                currentCanvas.render(delta); // Delegate rendering to the current canvas.
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during rendering
            e.printStackTrace();
        }
    }

    /**
     * Updates the state of the current canvas if it is not null, using the delta time for frame-rate-independent updates.
     *
     * @param delta The time in seconds since the last update.
     */
    public void update(float delta) {
        try {
            if (currentCanvas != null) {
                currentCanvas.update(delta); // Delegate state updates to the current canvas.
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during canvas updating
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the current active canvas.
     *
     * @return The current active canvas.
     */
    public Canvas getCurrentCanvas() {
        return currentCanvas;
    }

    /**
     * Disposes of the current canvas if it is not null, ensuring that all resources are properly released.
     * This method should be called when the canvas manager is being disposed of or when the game is closing.
     */
    public void dispose() {
        try {
            if (currentCanvas != null) {
                currentCanvas.dispose(); // Ensure resources of the current canvas are disposed.
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during canvas disposal
            e.printStackTrace();
        }
    }
}
