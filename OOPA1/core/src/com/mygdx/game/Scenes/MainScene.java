package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Canvas.UIManager;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.PlayerController;
import com.mygdx.game.Entity.nonPlayer;

public class MainScene implements Scene{

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private EntityManager entityManager;

    private nonPlayer wall;
//    private UIManager uiManager;


    public MainScene() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        entityManager = new EntityManager();

        wall = new nonPlayer("wall.jpg", 400, 100,0 , Entity.EntityState.NULL, false, true, 100, 100, Entity.EntityType.NULL);


        entityManager.addEntity(wall);


//        uiManager = new UIManager();
//        uiManager.setScreen("Main");
    }

    @Override
    public void create() {
        // Additional setup if needed
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);

        batch.begin();
        entityManager.render(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Start drawing lines
        shapeRenderer.setColor(Color.RED); // Set the color of the bounding box to red

        shapeRenderer.rect(entityManager.getxCords(wall), entityManager.getyCords(wall), entityManager.getWidth(wall), entityManager.getHeight(wall));

        shapeRenderer.end();

//        uiManager.render();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
//        uiManager.dispose();
    }

}
