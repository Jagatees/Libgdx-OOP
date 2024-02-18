package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Canvas.CanvasManager;
import com.mygdx.game.Canvas.GameCanvas;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.PlayerController;
import com.mygdx.game.Entity.nonPlayer;

public class GameScene implements Scene {


    private SpriteBatch batch;
    private Player pacman;
    private nonPlayer enemy, wall;


    // Manager
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private SceneManager sceneManager;
    private CanvasManager canvasManager;




    public GameScene(SceneManager sceneManager) {
        batch = new SpriteBatch();
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();

        pacman = new Player("pacman.png", 100, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL);
        wall = new nonPlayer("wall.jpg", 400, 100,0 , Entity.EntityState.NULL, false, true, 100, 100, Entity.EntityType.NULL);
        enemy = new nonPlayer("blueGhost.png", 300, 100, 10, Entity.EntityState.NULL, true, false, 50, 50, Entity.EntityType.NULL);

        entityManager.addEntity(pacman);
        entityManager.addEntity(enemy);
        entityManager.addEntity(wall);

        PlayerController playerController = new PlayerController(pacman, entityManager, collisionManager);
        pacman.setPlayerController(playerController);
        this.sceneManager = sceneManager;

        canvasManager = new CanvasManager(sceneManager);
        canvasManager.setCanvas(new GameCanvas(sceneManager));

    }

    @Override
    public void render() {
    	ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);

        batch.begin();
        entityManager.render(batch);
        batch.end();

    }

    @Override
    public void update(float delta) {
        entityManager.movement(pacman);
        entityManager.movement(enemy);

        canvasManager.render(delta);
        canvasManager.update(delta);
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
