package com.mygdx.game.HealthyGame.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Engine.Controller.AIControlManagement;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Collision.CollisionManager;
import com.mygdx.game.Engine.Entity.*;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.Scenes.TemplateScene;
import com.mygdx.game.HealthyGame.GameLogic.HealthyGameLogic;
import com.mygdx.game.HealthyGame.UserInterface.GameOverCanvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    private Entity pacman;
    private Entity enemy;
    private Entity boxPlayer;
    private List<nonPlayer> listNonPlayerEnemy = new ArrayList<>();
    private Entity tempEnemy;



    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     */
    public GameScene() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = EntityManager.getInstance();
        EntityFactory entityFactory = new EntityFactory();

        enemy = entityFactory.getEntityByInput("nonPlayer", "Words/H.png", 300, 100, 10, Entity.EntityState.NULL, true, false, 50, 50, Entity.EntityType.H, Entity.RenderType.SPRITE);

        pacman = entityFactory.getEntityByInput("Player", "entity/pacman.png", 150, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.PLAYER, Entity.RenderType.SPRITE);

        boxPlayer = entityFactory.getEntityByInput("nonPlayer", Color.GRAY, 200, 200, 10, Entity.EntityState.NULL, false, false,  50, 50, Entity.EntityType.OBJECT, Entity.RenderType.SHAPE);
        createWall("entity/wall.jpg", -20, 0, 15, 50, 0, true);
        createWall("entity/wall.jpg", 0, -20, 30, 50, 0, false);
        createWall("entity/wall.jpg", 0, 700, 30, 50, 0, false);
        createWall("entity/wall.jpg", 1260, 0, 15, 50, 0, true);

        entityManager.addEntity(pacman);
        entityManager.addEntity(enemy);
        entityManager.addEntity(boxPlayer);

        PlayerControllerManagement playerControllerManagement = new PlayerControllerManagement((Player)pacman, entityManager, collisionManager);
        entityManager.setPlayerController((Player)pacman, playerControllerManagement);

        AIControlManagement aiControlManagement = new AIControlManagement((nonPlayer)enemy, entityManager, collisionManager);
        entityManager.setAIController((nonPlayer)enemy, aiControlManagement);

        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(new com.mygdx.game.HealthyGame.UserInterface.GameCanvas());

        String word = HealthyGameLogic.getInstance().getCurrentWord();

        Random rand = new Random();

        // Define the boundaries for the spawn locations.
        int minX = 100; // Example minimum X coordinate
        int maxX = 800; // Example maximum X coordinate
        int minY = 100; // Example minimum Y coordinate
        int maxY = 600; // Example maximum Y coordinate


        for (int i = 0; i < word.length(); i++) {
            // Generate random positions within the defined boundaries.
            int x = rand.nextInt( maxX - minX + 1) + minX;
            int y = rand.nextInt(maxY - minY + 1) + minY;

            tempEnemy = entityFactory.getEntityByInput("nonPlayer","Words/" + String.valueOf(word.charAt(i)) + ".png", x, y, 10, Entity.EntityState.NULL, true, false,
                    50, 50, Entity.EntityType.H, Entity.RenderType.SPRITE);

            entityManager.addEntity(tempEnemy);
            listNonPlayerEnemy.add((nonPlayer)tempEnemy);
        }

        for (nonPlayer enemy : listNonPlayerEnemy) {
            aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
            entityManager.setAIController(enemy, aiControlManagement);
        }
    }

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

        if (HealthyGameLogic.getInstance().getScore() > HealthyGameLogic.getInstance().GetScoreGoal()) {
            System.out.println("switch canvas");
            HealthyGameLogic.getInstance().setScore(0);
            EntityManager.getInstance().setAllEntitiesRemoved(true);
            CanvasManager.getInstance().setCanvas(new GameOverCanvas());
        }

    }

    /**
     * Update method to process game logic updates based on the time since the last frame.
     * @param delta Time passed since the last frame, in seconds.
     */
    @Override
    public void update(float delta) {
        entityManager.movement(pacman);
        entityManager.movement(enemy);

        for (nonPlayer enemy : listNonPlayerEnemy) {
            entityManager.movement(enemy);
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
