package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AI.AIControlManagement;
import com.mygdx.game.Canvas.CanvasManager;
import com.mygdx.game.Canvas.GameCanvas;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.PlayerController;
import com.mygdx.game.Entity.nonPlayer;


/**
 * The GameScene file a like clone of the TemplateScene as that is the Base version
 */
public class GameScene implements Scene {

    /** Rendering */
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    /** Manager */
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private SceneManager sceneManager;
    private CanvasManager canvasManager;

    /** Game Entity */
    private Player pacman;
    private nonPlayer enemy;
    private nonPlayer wall;
    private nonPlayer wall2;
    private nonPlayer wall3;
    private nonPlayer wall4;
    private nonPlayer boxPlayer;
    private nonPlayer boxPlayer2;


    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     * @param sceneManager Manages transitions between scenes.
     */
    public GameScene(SceneManager sceneManager) {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();

        pacman = new Player("pacman.png", 100, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL, Entity.RenderType.SPRITE);
        enemy = new nonPlayer("blueGhost.png", 300, 100, 10, Entity.EntityState.NULL, true, false, 50, 50, Entity.EntityType.NULL, Entity.RenderType.SPRITE);

        wall = new nonPlayer("wall.jpg", -50, 0,0 , Entity.EntityState.NULL, false, true, 80, 1000, Entity.EntityType.NULL, Entity.RenderType.SPRITE);
        wall2 = new nonPlayer("wall.jpg", 500, 0,0 , Entity.EntityState.NULL, false, true, 80, 1000, Entity.EntityType.NULL, Entity.RenderType.SPRITE);
        wall3 = new nonPlayer("wall.jpg", 0, -50,0 , Entity.EntityState.NULL, false, true, 1000, 80, Entity.EntityType.NULL, Entity.RenderType.SPRITE);
        wall4 = new nonPlayer("wall.jpg", 0, 500,0 , Entity.EntityState.NULL, false, true, 1000, 80, Entity.EntityType.NULL, Entity.RenderType.SPRITE);

        boxPlayer = new nonPlayer(Color.RED, 200, 200, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL, Entity.RenderType.SHAPE);
        boxPlayer2 = new nonPlayer(Color.BLACK, 300, 300, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL, Entity.RenderType.SHAPE);


        entityManager.addEntity(pacman);
        entityManager.addEntity(enemy);
        entityManager.addEntity(wall);
        entityManager.addEntity(wall2);
        entityManager.addEntity(wall3);
        entityManager.addEntity(wall4);
        entityManager.addEntity(boxPlayer);
        entityManager.addEntity(boxPlayer2);

        
        PlayerController playerController = new PlayerController(pacman, entityManager, collisionManager);
        entityManager.setPlayerController(pacman, playerController);
        
        AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
        entityManager.setAIController(enemy, aiControlManagement);
            
        this.sceneManager = sceneManager;

        canvasManager = new CanvasManager(sceneManager);
        canvasManager.setCanvas(new GameCanvas(sceneManager));

    }

    /**
     * Render method called every frame to draw the scene's entities and background.
     */
    @Override
    public void render() {

    	ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);
        entityManager.render(batch, shapeRenderer);

        float delta = Gdx.graphics.getDeltaTime();
        canvasManager.render(delta);
        canvasManager.update(delta);

    }


    /**
     * Update method to process game logic updates based on the time since the last frame.
     * @param delta Time passed since the last frame, in seconds.
     */
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

    /**
     * Dispose method to clean up resources when the scene is no longer in use.
     */
    @Override
    public void create() {

    }

}
