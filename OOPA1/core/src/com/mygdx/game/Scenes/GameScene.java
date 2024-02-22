package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Canvas.MainMenuCanvas;
import com.mygdx.game.Controller.AIControlManagement;
import com.mygdx.game.Canvas.CanvasManager;
import com.mygdx.game.Canvas.GameCanvas;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Controller.PlayerControllerManagement;
import com.mygdx.game.Entity.nonPlayer;
import com.mygdx.game.Input.InputOutputManager;
import com.mygdx.game.audio.AudioAssetKey;
import com.mygdx.game.audio.AudioManager;


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
    private InputOutputManager inputOutputManager;
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
        inputOutputManager = InputOutputManager.getInstance();

        inputOutputManager.getAudioManager().loadMusicTrack(AudioAssetKey.DEAFULT_ONE, "SoundEffect/default.mp3" , true);
        inputOutputManager.getAudioManager().play(AudioAssetKey.DEAFULT_ONE);
        inputOutputManager.getAudioManager().setVolume(AudioAssetKey.DEAFULT_ONE, 0.1f);

        pacman = new Player("pacman.png", 250, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL, Entity.RenderType.SPRITE);
        enemy = new nonPlayer("blueGhost.png", 300, 100, 10, Entity.EntityState.NULL, true, false, 50, 50, Entity.EntityType.NULL, Entity.RenderType.SPRITE);

        boxPlayer = new nonPlayer(Color.GRAY, 200, 200, 10, Entity.EntityState.NULL, false, false,  50, 50, Entity.EntityType.NULL, Entity.RenderType.SHAPE);
        boxPlayer2 = new Player(Color.BROWN, 400, 100, 10, Entity.EntityState.NULL, false, 50, 50, Entity.EntityType.NULL, Entity.RenderType.SHAPE);


        // For Presentation Purpose <Delete>
        createWall("wall.jpg", 0, 0, 8, 100, 0, true);
        createWall("wall.jpg", 100, 0, 12, 100, 0, false);
        createWall("wall.jpg", 100, 650, 12, 100, 0, false);
        createWall("wall.jpg", 1200, 0, 8, 100, 0, true);

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
        
        
        // Limitation: Singleplayer, unless different controls configured as current keys controls both player entities
        
        // PlayerControllerManagement playerControllerMgmt2 = new PlayerControllerManagement(boxPlayer2, entityManager, collisionManager);
        //entityManager.setPlayerController(boxPlayer2, playerControllerMgmt2);

        canvasManager = new CanvasManager();
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
