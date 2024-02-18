package com.mygdx.game.Canvas;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainScreen implements Screen{
    private Stage stage;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create UI elements for the game screen
        Label label = new Label("Main Screen", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        label.setPosition( (Gdx.graphics.getWidth() / 2f - 100),  (Gdx.graphics.getHeight() / 2f + 200));

        stage.addActor(label);
    }

    @Override
    public void render() {
        // Clear the screen with a specific color (e.g., black)
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void update(float delta) {
        // Add game logic and update code here
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
