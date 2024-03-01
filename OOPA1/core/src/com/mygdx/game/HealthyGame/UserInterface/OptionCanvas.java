package com.mygdx.game.HealthyGame.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Engine.Canvas.Canvas;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Canvas.UIElements;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.audio.AudioAssetKey;

/**
 * Defines the canvas for the main menu, including UI elements like buttons
 * for starting the game and exiting the application.
 */
public class OptionCanvas implements Canvas {

    private Stage stage = new Stage(new ScreenViewport());

    public OptionCanvas() {
        Gdx.input.setInputProcessor(stage);


        UIElements.createLabel(stage, "OPTIONS", 600, 600, Color.RED);
        UIElements.createTextButton(stage, "Back", 600, 450, 100, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CanvasManager.getInstance().setCanvas(new MainMenuCanvas());
            }
        });


        UIElements.createLabel(stage, "Music", 200, 600, Color.RED);
        UIElements.SimpleSlider(stage, 0, 1, 300, 600, InputOutputManager.getInstance().getAudioManager().getVolume(AudioAssetKey.BG_1), new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                System.out.println("Slider Value: " + slider.getValue());
                InputOutputManager.getInstance().getAudioManager().setVolume(AudioAssetKey.BG_1, slider.getValue());
            }
        });



        UIElements.createLabel(stage, "Move Up", 200, 450, Color.RED);



        UIElements.createLabel(stage, "Move Down", 200, 400, Color.RED);



        UIElements.createLabel(stage, "Move Right", 200, 350, Color.RED);



        UIElements.createLabel(stage, "Move Left", 200, 300, Color.RED);




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
