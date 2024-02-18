package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Canvas.CanvasManager;
import com.mygdx.game.Canvas.MainMeunCanvas;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.nonPlayer;

public class MainScene implements Scene{

    private SpriteBatch batch = new SpriteBatch();
    private CanvasManager canvasManager;

    public MainScene(SceneManager sceneManager) {
        canvasManager = new CanvasManager(sceneManager);
        canvasManager.setCanvas(new MainMeunCanvas(sceneManager));
    }

    @Override
    public void update(float delta) {
        canvasManager.render(delta);
        canvasManager.update(delta);
    }


    @Override
    public void render() {
        ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);
    }

    @Override
    public void dispose() {
        batch.dispose();
        canvasManager.dispose();
    }


    @Override
    public void create() {

    }


}
