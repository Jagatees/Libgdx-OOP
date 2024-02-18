package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AI.AIController;
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
    private ShapeRenderer shapeRenderer;
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private Player pacman;
    private nonPlayer enemy;
    private nonPlayer wall;
    private nonPlayer wall2;
    private nonPlayer wall3;
    private nonPlayer wall4;
    private SceneManager sceneManager;
    private CanvasManager canvasManager;




    public GameScene(SceneManager sceneManager) {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();

        pacman = new Player("pacman.png", 100, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL);
        enemy = new nonPlayer("blueGhost.png", 300, 100, 10, Entity.EntityState.NULL, true, false, 50, 50, Entity.EntityType.NULL);

        wall = new nonPlayer("wall.jpg", -50, 0,0 , Entity.EntityState.NULL, false, true, 80, 1000, Entity.EntityType.NULL);
        wall2 = new nonPlayer("wall.jpg", 500, 0,0 , Entity.EntityState.NULL, false, true, 80, 1000, Entity.EntityType.NULL);
        wall3 = new nonPlayer("wall.jpg", 0, -50,0 , Entity.EntityState.NULL, false, true, 1000, 80, Entity.EntityType.NULL);
        wall4 = new nonPlayer("wall.jpg", 0, 500,0 , Entity.EntityState.NULL, false, true, 1000, 80, Entity.EntityType.NULL);

        
        entityManager.addEntity(pacman);
        entityManager.addEntity(enemy);
        entityManager.addEntity(wall);
        entityManager.addEntity(wall2);
        entityManager.addEntity(wall3);
        entityManager.addEntity(wall4);

        

        PlayerController playerController = new PlayerController(pacman, entityManager, collisionManager);
        pacman.setPlayerController(playerController);
        
        AIController aiController = new AIController(enemy, entityManager, collisionManager);
        enemy.setAIController(aiController);
        
        this.sceneManager = sceneManager;

        canvasManager = new CanvasManager(sceneManager);
        canvasManager.setCanvas(new GameCanvas(sceneManager));

    }

    @Override
    public void create() {

    }

    @Override
    public void render() {

    	ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);

        batch.begin();
        entityManager.render(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Start drawing lines
        shapeRenderer.setColor(Color.RED); // Set the color of the bounding box to red

        shapeRenderer.rect(entityManager.getxCords(pacman), entityManager.getyCords(pacman), entityManager.getWidth(pacman), entityManager.getHeight(pacman));
        shapeRenderer.rect(entityManager.getxCords(wall), entityManager.getyCords(wall), entityManager.getWidth(wall), entityManager.getHeight(wall));
        shapeRenderer.rect(entityManager.getxCords(enemy), entityManager.getyCords(enemy), entityManager.getWidth(enemy), entityManager.getHeight(enemy));

        shapeRenderer.end();

        float delta = Gdx.graphics.getDeltaTime();
        canvasManager.render(delta);
        canvasManager.update(delta);

    }

    @Override
    public void update(float delta) {
        entityManager.movement(pacman);
        entityManager.movement(enemy);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        canvasManager.dispose();
    }


}
