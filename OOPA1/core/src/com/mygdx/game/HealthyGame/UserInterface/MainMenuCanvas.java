package com.mygdx.game.HealthyGame.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Engine.Canvas.Canvas;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Canvas.ImageRenderer;
import com.mygdx.game.Engine.Canvas.UIElements;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Scenes.SceneManager;
import com.mygdx.game.Engine.Entity.EntityManager;
import com.mygdx.game.Engine.audio.AudioAssetKey;
import com.mygdx.game.Engine.audio.AudioManager;
import com.mygdx.game.HealthyGame.GameLogic.HealthyGameLogic;



// Beta build

/**
 * Defines the canvas for the main menu, including UI elements like buttons
 * for starting the game and exiting the application.
 */
public class MainMenuCanvas implements Canvas {

    private Stage stage = new Stage(new ScreenViewport());
    private SpriteBatch batch;
    private Texture background;


    public MainMenuCanvas() {
        Gdx.input.setInputProcessor(stage);

        UIElements.createLabel(stage, "Alphabet Chase", 600, 600, Color.RED);
        UIElements.createTextButton(stage, "Start Game", 600, 450, 100, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.getInstance().play(AudioAssetKey.MOUSE_CLICK);
                SceneManager.getInstance().setScene("EasyStage");
                CanvasManager.getInstance().setCanvas(new GameCanvas());
                EntityManager.getInstance().setAllEntitiesRemoved(false);

            }
        });

       UIElements.createTextButton(stage, "Options", 600, 350, 100 , 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.getInstance().play(AudioAssetKey.MOUSE_CLICK);
                CanvasManager.getInstance().setCanvas(new OptionCanvas());

            }
        });

        UIElements.createTextButton(stage, "Close Game", 600, 250, 100 , 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.getInstance().play(AudioAssetKey.MOUSE_CLICK);
                SimulationLifecycleManagement.getInstance().closeGame();
            }
        });


        batch = new SpriteBatch();
        background = new Texture("background/bg-1.jpg");



    }


    /**
     * Renders the current canvas if it is not null, passing along the delta time for frame-rate-independent rendering.
     *
     * @param delta The time in seconds since the last render call.
     */
    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();


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
        background.dispose();

    }
}
