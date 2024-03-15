package com.mygdx.game.Engine.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Engine.Canvas.OptionsCanvas;
import com.mygdx.game.Engine.Controller.AIControlManagement;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Canvas.GameCanvas;
import com.mygdx.game.Engine.Collision.CollisionManager;
import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.Engine.Entity.EntityManager;
import com.mygdx.game.Engine.Entity.Player;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.Entity.nonPlayer;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.audio.AudioAssetKey;


/**
 * The GameScene file a like clone of the TemplateScene as that is the Base version
 */
public class GameScene extends TemplateScene {

    /** Rendering */
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    /** Manager */
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private CanvasManager canvasManager;


    /** Game Entity */
    private Player pacman;
    private nonPlayer enemy;
    private nonPlayer boxPlayer;
    private Player boxPlayer2;


    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     */
    public GameScene() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();




        pacman = new Player("entity/pacman.png", 150, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.PLAYER, Entity.RenderType.SPRITE);
        enemy = new nonPlayer("entity/blueGhost.png", 300, 100, 10, Entity.EntityState.NULL, true, false, 50, 50, Entity.EntityType.ENEMY, Entity.RenderType.SPRITE);

        boxPlayer = new nonPlayer(Color.GRAY, 200, 200, 10, Entity.EntityState.NULL, false, false,  50, 50, Entity.EntityType.OBJECT, Entity.RenderType.SHAPE);
        boxPlayer2 = new Player(Color.BROWN, 400, 100, 10, Entity.EntityState.NULL, false, 50, 50, Entity.EntityType.OBJECT, Entity.RenderType.SHAPE);
        
        // For Presentation Purpose <Delete>
        createWall("entity/wall.jpg", 0, 0, 8, 100, 0, true);
        createWall("entity/wall.jpg", 100, 0, 12, 100, 0, false);
        createWall("entity/wall.jpg", 100, 650, 12, 100, 0, false);
        createWall("entity/wall.jpg", 1200, 0, 8, 100, 0, true);

        entityManager.addEntity(pacman);
        entityManager.addEntity(enemy);
        entityManager.addEntity(boxPlayer);
        entityManager.addEntity(boxPlayer2);
        
        
        // Testing of Update for Player
        // entityManager.update(pacman, 5);
        
        // Testing of Update for nonPlayer
        // entityManager.update(enemy, 5);
        
        PlayerControllerManagement playerControllerManagement = new PlayerControllerManagement(pacman, entityManager, collisionManager);
        entityManager.setPlayerController(pacman, playerControllerManagement);
        
        AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
        entityManager.setAIController(enemy, aiControlManagement);
        
        
        entityManager.removeEntity(boxPlayer2);
        
        // Limitation: Singleplayer, unless different controls configured as current keys controls both player entities
        
        // PlayerControllerManagement playerControllerMgmt2 = new PlayerControllerManagement(boxPlayer2, entityManager, collisionManager);
        //entityManager.setPlayerController(boxPlayer2, playerControllerMgmt2);

        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(new GameCanvas());



    }

    // For Presentation Purpose <Delete>
    public void createWall(String spritePath, int startX, int startY, int segments, int segmentSize, int spacing, boolean vertical) {
        int x = startX;
        int y = startY;
        for (int i = 0; i < segments; i++) {
            nonPlayer wallSegment = new nonPlayer(spritePath, x, y, 0, Entity.EntityState.NULL, false, true, segmentSize, segmentSize, Entity.EntityType.NULL, Entity.RenderType.SPRITE);
            entityManager.addEntity(wallSegment);
            if (vertical) {
                y += segmentSize + spacing; // Move down for the next segment if vertical
            } else {
                x += segmentSize + spacing; // Move right for the next segment if horizontal
            }
        }
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
        entityManager.movement(boxPlayer2);

        // Check if the game is paused and switch canvas accordingly
        if (SimulationLifecycleManagement.getInstance().isPaused() && !(canvasManager.getCurrentCanvas() instanceof OptionsCanvas)) {
            canvasManager.setCanvas(new OptionsCanvas());
        } else if (!SimulationLifecycleManagement.getInstance().isPaused() && !(canvasManager.getCurrentCanvas() instanceof GameCanvas)) {
            canvasManager.setCanvas(new GameCanvas());
        }
    }
    
    /**
     * Dispose method to clean up resources when the scene is no longer in use.
     */
    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        canvasManager.dispose();
    }

    @Override
    public void create() {

    }

}
