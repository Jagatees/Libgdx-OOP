package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TextRendererScreen  {

    private Stage stage;

    public TextRendererScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create a minimal skin
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.BLACK;

        // Optionally set other style properties
        // textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("button-up.png")));
        // textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture("button-down.png")));

        // Create and configure a text button
        TextButton textButton = new TextButton("Game Menu", textButtonStyle);
        textButton.setPosition(100, 100); // Position the button
        textButton.setSize(200, 50); // Set the size of the button
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                Gdx.app.log("UIManager", "Button clicked!");
                // Add what you want to do when the button is clicked
            }
        });

        // Add the button to the stage
        stage.addActor(textButton);
    }

    /**
     * Call this method in your render loop.
     */
    public void draw() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Disposes of the resources used by this UIManager.
     */
    public void dispose() {
        stage.dispose();
    }

}
