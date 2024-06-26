package com.mygdx.game.Engine.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Engine.audio.AudioAssetKey;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.Scenes.SceneManager;

/**
 * Defines the canvas for the main menu, including UI elements like buttons
 * for starting the game and exiting the application.
 */
public class OptionsCanvas implements Canvas{

    private Stage stage;
    private InputOutputManager inputOutputManager;
    private SimulationLifecycleManagement simulationLifecycleManagement;

    public OptionsCanvas() {
    	inputOutputManager = InputOutputManager.getInstance();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        SceneManager.getInstance();
        this.simulationLifecycleManagement = SimulationLifecycleManagement.getInstance();


        UIElements.createTextButton(stage, "Resume", 500, 500, 50, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	inputOutputManager.getAudioManager().play(AudioAssetKey.DEFAULT_TWO);
            	simulationLifecycleManagement.togglePause();
            }
        });
    }


    /**
     * Renders the current canvas if it is not null, passing along the delta time for frame-rate-independent rendering.
     *
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
