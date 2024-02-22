package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Input.InputOutputManager;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.audio.AudioAssetKey;

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

        UIElements.createTextButton(stage, "Go Back", 1212, 650, 50, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SceneManager.getInstance().setScene(new MainScene());
                inputOutputManager.getAudioManager().stop(AudioAssetKey.DEFAULT_TWO);
            }
        });

        UIElements.createTextButton(stage, "End Game", 1212, 580, 50, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SimulationLifecycleManagement.getInstance().closeGame();
                inputOutputManager.getAudioManager().stop(AudioAssetKey.DEFAULT_TWO);
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
