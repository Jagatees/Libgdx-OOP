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
    private ShapeRenderer shapeRenderer = new ShapeRenderer(); // REMOVE THE SHAPE RENDER THIS IS ONLY FOR TESTING
    private EntityManager entityManager = new EntityManager();
    private nonPlayer wall;
    private CanvasManager canvasManager;



    public MainScene(SceneManager sceneManager) {
        wall = new nonPlayer("wall.jpg", 400, 100,0 , Entity.EntityState.NULL, false, true, 100, 100, Entity.EntityType.NULL);
        entityManager.addEntity(wall);
        canvasManager = new CanvasManager(sceneManager);
        canvasManager.setCanvas(new MainMeunCanvas(sceneManager));

    }

    @Override
    public void render() {
        ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);

        batch.begin();
        entityManager.render(batch);
        batch.end();

        // REMOVE THE SHAPE RENDER THIS IS ONLY FOR TESTING
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(entityManager.getxCords(wall), entityManager.getyCords(wall), entityManager.getWidth(wall), entityManager.getHeight(wall));
        shapeRenderer.end();
        // REMOVE THE SHAPE RENDER THIS IS ONLY FOR TESTING
        float delta = Gdx.graphics.getDeltaTime();
        canvasManager.render(delta);
        canvasManager.update(delta);

    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        canvasManager.dispose();
    }


    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {

    }


}
