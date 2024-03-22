package com.mygdx.game.HealthyGame.Scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Scenes.TemplateScene;
import com.mygdx.game.HealthyGame.UserInterface.MainMenuCanvas;


public class MainMenuScene extends TemplateScene {

    /** Rendering */
    private SpriteBatch batch = new SpriteBatch();


    /**
     * Constructor for MainScene. Initializes the canvas manager and sets up the main menu canvas.
     */
    public MainMenuScene() {
        CanvasManager.getInstance().setCanvas(new MainMenuCanvas());
    }

    /**
     * Updates the state of the scene. Typically called each frame.
     * @param delta Time since the last frame ikrin seconds.
     */
    @Override
    public void update(float delta) {
        CanvasManager.getInstance().render(delta);
        CanvasManager.getInstance().update(delta);
    }

    /**
     * Renders the visual elements of the scene. This method is responsible for drawing the main menu.
     */
    @Override
    public void render() {
        ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);
    }

    /**
     * Frees up resources when the scene is no longer active or when the game is closed.
     */
    @Override
    public void dispose() {
        batch.dispose();
        CanvasManager.getInstance().dispose();
    }

    /**
     * Handles scene creation logic. This method would be used to initialize scene-specific resources.
     */
    @Override
    public void create() {

    }


}
