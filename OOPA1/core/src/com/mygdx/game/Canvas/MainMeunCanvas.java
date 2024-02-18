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
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.SceneManager;

public class MainMeunCanvas implements Canvas{

    private SceneManager sceneManager;
    private Stage stage;
    private TextButton startGameButton;
    private TextButton optionsButton;
    private TextButton exitGameButton;


    public MainMeunCanvas(SceneManager sceneManager) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;

        startGameButton = createButton("Start Game", 600, 450);
        stage.addActor(startGameButton);

        optionsButton = createButton("Options", 600, 375);
        stage.addActor(optionsButton);

        exitGameButton = createButton("Exit Game", 600, 300);
        stage.addActor(exitGameButton);

        this.sceneManager = sceneManager;
    }

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
                    Gdx.app.log("UIManager", "Start button clicked!");
                    sceneManager.setScene(new GameScene(sceneManager));
                } else if (actor == optionsButton) {
                    // Options action
                    Gdx.app.log("UIManager", "Options button clicked!");
                } else if (actor == exitGameButton) {
                    // Exit game action
                    Gdx.app.log("UIManager", "Exit Game button clicked!");
                }
            }
        });

        return button;
    }


    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
