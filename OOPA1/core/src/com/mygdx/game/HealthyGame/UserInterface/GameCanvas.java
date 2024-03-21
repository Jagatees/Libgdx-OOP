package com.mygdx.game.HealthyGame.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Engine.Canvas.Canvas;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Canvas.UIElements;
import com.mygdx.game.Engine.audio.AudioAssetKey;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.HealthyGame.GameLogic.HealthyGameLogic;

/**
 * Implements the Canvas interface to create a UI canvas for the game menu using scene2d UI elements.
 */
public class GameCanvas implements Canvas {

    private Stage stage;
    private InputOutputManager inputOutputManager;

    /**
     * Constructs a GameCanvas with a reference to the SceneManager for scene transitions.
     * Initializes UI components like buttons and sets their behavior.
     *
     */
    public GameCanvas() {

        inputOutputManager = InputOutputManager.getInstance();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        UIElements.createLabel(stage, HealthyGameLogic.getInstance().getCurrentWord().toString(), 100, 100);

        UIElements.createTextButton(stage,  "Options", 1100, 650, 50, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SimulationLifecycleManagement.getInstance().togglePause();
                CanvasManager.getInstance().setCanvas(new OptionCanvas());
            }
        });




    }

    /**
     * Render the UI elements by drawing the stage and its actors.
     * @param delta The time in seconds since the last render call.
     */
    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Update the state of the canvas. Currently, this method does not contain logic,
     * as the stage handles updates in the render method.
     * @param delta The time in seconds since the last update.
     */
    @Override
    public void update(float delta) {
    }

    /**
     * Dispose of the resources used by the stage to free up memory.
     */
    @Override
    public void dispose() {
    }
}
