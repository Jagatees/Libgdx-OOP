package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;

/**
 * Implements the Canvas interface to create a UI canvas for the game menu using scene2d UI elements.
 */
public class GameCanvas implements Canvas {

    private Stage stage;

    /**
     * Constructs a GameCanvas with a reference to the SceneManager for scene transitions.
     * Initializes UI components like buttons and sets their behavior.
     *
     * @param sceneManager The SceneManager used to switch scenes upon UI interactions.
     */
    public GameCanvas(SceneManager sceneManager) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.YELLOW;

        TextButton textButton = new TextButton("Game Menu", textButtonStyle);
        textButton.setPosition(100, 100);
        textButton.setSize(200, 50);

        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                if (actor == textButton) {
                    sceneManager.setScene(new MainScene(sceneManager));
                }
            }
        });

        stage.addActor(textButton);
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
        stage.dispose();
    }
}
