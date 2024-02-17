package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIManager {
    private Stage stage;
    private TextButton startButton;

    public UIManager() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create a font for the button text
        BitmapFont font = new BitmapFont();

        // Create a TextButton with the font
        startButton = new TextButton("Start Game", new TextButton.TextButtonStyle(null, null, null, font));

        // Set button position
        startButton.setPosition(100, 100);

        // Add a click listener to the button
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click event
                System.out.println("Start button clicked!");
            }
        });

        // Add the button to the stage
        stage.addActor(startButton);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    public void draw() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
