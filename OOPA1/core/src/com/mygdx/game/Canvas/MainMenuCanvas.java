package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private SimulationLifecycleManagement simulationLifecycleManagement;

    public MainMenuCanvas() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.sceneManager = SceneManager.getInstance();
        this.simulationLifecycleManagement = SimulationLifecycleManagement.getInstance();


        UIElements.createLabel(stage, "Main Menu", 600, 600, Color.BLACK); // Assuming 'stage' is your Scene2D stage
        UIElements.createTextButton(stage, "Start Simulation", 600, 450, 50, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sceneManager.setScene("Game");
            }
        });


       UIElements.createTextButton(stage, "Exit Simulation", 600, 350, 50 , 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                simulationLifecycleManagement.closeGame();
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
