package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.SceneManager;

/**
 * Defines the canvas for the main menu, including UI elements like buttons
 * for starting the game and exiting the application.
 */
public class MainMenuCanvas implements Canvas{

    private SceneManager sceneManager;
    private Stage stage;
    private TextButton startGameButton;
    private TextButton exitGameButton;
    private SimulationLifecycleManagement simulationLifecycleManagement;


    public MainMenuCanvas(SceneManager sceneManager) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;

        startGameButton = createButton("Start Game", 600, 450);
        stage.addActor(startGameButton);

        exitGameButton = createButton("Exit Game", 600, 375);
        stage.addActor(exitGameButton);

        this.sceneManager = sceneManager;
        this.simulationLifecycleManagement = new SimulationLifecycleManagement();

    }


    /**
     * Creates a button with specified text and position.
     * @param buttonText Text displayed on the button.
     * @param x Horizontal position of the button.
     * @param y Vertical position of the button.
     * @return Configured TextButton instance.
     */
    private TextButton createButton(String buttonText, float x, float y) {
        NinePatch patch = new NinePatch(new Texture("color/black.jpg"), 3, 3, 3, 3); // Adjust the 3s to match your NinePatch borders
        NinePatchDrawable background = new NinePatchDrawable(patch);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.up = background; // Use the NinePatchDrawable as the button background

        TextButton button = new TextButton(buttonText, buttonStyle);
        button.setPosition(x, y);
        button.setSize(200, 50);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                if (actor == startGameButton) {
                    sceneManager.setScene(new GameScene(sceneManager));
                }
                else if (actor == exitGameButton) {
                    simulationLifecycleManagement.closeGame();
                }
            }
        });

        return button;
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
        stage.dispose();
    }
}
