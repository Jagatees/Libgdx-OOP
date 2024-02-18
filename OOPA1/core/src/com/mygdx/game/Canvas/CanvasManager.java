package com.mygdx.game.Canvas;

import com.mygdx.game.Scenes.SceneManager;

public class CanvasManager {
    private Canvas currentCanvas;
    private SceneManager sceneManager;

    public CanvasManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setCanvas(Canvas canvas) {
        if (currentCanvas != null) {
            currentCanvas.dispose();
        }
        currentCanvas = canvas;
    }

    public void render(float delta) {
        if (currentCanvas != null) {
            currentCanvas.render(delta);
        }
    }

    public void update(float delta) {
        if (currentCanvas != null) {
            currentCanvas.update(delta);
        }
    }

    public void dispose() {
        if (currentCanvas != null) {
            currentCanvas.dispose();
        }
    }
}
