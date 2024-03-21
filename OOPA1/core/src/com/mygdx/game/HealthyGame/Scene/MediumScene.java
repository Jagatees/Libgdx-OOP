package com.mygdx.game.HealthyGame.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Collision.CollisionManager;
import com.mygdx.game.Engine.Controller.AIControlManagement;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.Entity.*;
import com.mygdx.game.Engine.Scenes.SceneManager;
import com.mygdx.game.Engine.Scenes.TemplateScene;
import com.mygdx.game.HealthyGame.GameLogic.HealthyGameLogic;
import com.mygdx.game.HealthyGame.UserInterface.GameCanvas;
import com.mygdx.game.HealthyGame.UserInterface.GameOverCanvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The GameScene file a like clone of the TemplateScene as that is the Base version
 */
public class MediumScene extends TemplateScene {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private CanvasManager canvasManager;

    private Entity pacman;
    private Entity boxPlayer;
    private List<nonPlayer> listNonPlayerEnemy = new ArrayList<>();
    private Entity tempEnemy;

    private Entity.EntityType entityType;

    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     */
    public MediumScene() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = EntityManager.getInstance();
        EntityFactory entityFactory = new EntityFactory();

        pacman = entityFactory.getEntityByInput("Player", "entity/pacman.png", 100, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.PLAYER, Entity.RenderType.SPRITE);

        boxPlayer = entityFactory.getEntityByInput("nonPlayer", Color.GRAY, 200, 200, 10, Entity.EntityState.NULL, false, false,  50, 50, Entity.EntityType.OBJECT, Entity.RenderType.SHAPE);

        createWall("entity/wall.jpg", -20, 0, 15, 50, 0, true);
        createWall("entity/wall.jpg", 0, -20, 30, 50, 0, false);
        createWall("entity/wall.jpg", 0, 700, 30, 50, 0, false);
        createWall("entity/wall.jpg", 1260, 0, 15, 50, 0, true);

        entityManager.addEntity(pacman);
        entityManager.addEntity(boxPlayer);

        PlayerControllerManagement playerControllerManagement = new PlayerControllerManagement((Player)pacman, entityManager, collisionManager);
        entityManager.setPlayerController((Player)pacman, playerControllerManagement);

        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(new com.mygdx.game.HealthyGame.UserInterface.GameCanvas());

        String word = HealthyGameLogic.getInstance().getCurrentWord();

        Random rand = new Random();
        int minX = 300;
        int maxX = 800;
        int minY = 300;
        int maxY = 600;

        System.out.println(word);

        for (int i = 0; i < word.length(); i++) {
            int x = rand.nextInt( maxX - minX + 1) + minX;
            int y = rand.nextInt(maxY - minY + 1) + minY;

            switch(String.valueOf(word.charAt(i))) {
                case "A":
                    entityType = Entity.EntityType.A;
                    break;
                case "B":
                    entityType = Entity.EntityType.B;
                    break;
                case "C":
                    entityType = Entity.EntityType.C;
                    break;
                case "D":
                    entityType = Entity.EntityType.D;
                    break;
                case "E":
                    entityType = Entity.EntityType.E;
                    break;
                case "F":
                    entityType = Entity.EntityType.F;
                    break;
                case "G":
                    entityType = Entity.EntityType.G;
                    break;
                case "H":
                    entityType = Entity.EntityType.H;
                    break;
                case "I":
                    entityType = Entity.EntityType.I;
                    break;
                case "J":
                    entityType = Entity.EntityType.J;
                    break;
                case "K":
                    entityType = Entity.EntityType.K;
                    break;
                case "L":
                    entityType = Entity.EntityType.L;
                    break;
                case "M":
                    entityType = Entity.EntityType.M;
                    break;
                case "N":
                    entityType = Entity.EntityType.N;
                    break;
                case "O":
                    entityType = Entity.EntityType.O;
                    break;
                case "P":
                    entityType = Entity.EntityType.P;
                    break;
                case "Q":
                    entityType = Entity.EntityType.Q;
                    break;
                case "R":
                    entityType = Entity.EntityType.R;
                    break;
                case "S":
                    entityType = Entity.EntityType.S;
                    break;
                case "T":
                    entityType = Entity.EntityType.T;
                    break;
                case "U":
                    entityType = Entity.EntityType.U;
                    break;
                case "V":
                    entityType = Entity.EntityType.V;
                    break;
                case "W":
                    entityType = Entity.EntityType.W;
                    break;
                case "X":
                    entityType = Entity.EntityType.X;
                    break;
                case "Y":
                    entityType = Entity.EntityType.Y;
                    break;
                case "Z":
                    entityType = Entity.EntityType.Z;
                    break;
                // Optionally, you can have a default case if the input doesn't match any letter
                default:
                    // Handle an unexpected input
                    break;
            }


            System.out.println(entityType.toString());
            tempEnemy = entityFactory.getEntityByInput("nonPlayer", "Words/" + String.valueOf(word.charAt(i)) + ".png", x, y, 10, Entity.EntityState.NULL, true, false,
                    50, 50, entityType, Entity.RenderType.SPRITE);

            entityManager.addEntity(tempEnemy);
            listNonPlayerEnemy.add((nonPlayer)tempEnemy);
        }

        for (nonPlayer enemy : listNonPlayerEnemy) {
            AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
            entityManager.setAIController(enemy, aiControlManagement);
        }

        // Debug Sout
        System.out.println("Goal : " + HealthyGameLogic.getInstance().getScoreGoal());
        System.out.println("Current Word : " +HealthyGameLogic.getInstance().getCurrentWord());
        System.out.println("Current Word length: " +HealthyGameLogic.getInstance().getCurrentWord().length());
        System.out.println("Current Score : " + HealthyGameLogic.getInstance().getScore());
        System.out.println("First letter of word : " + HealthyGameLogic.getInstance().getFirstLetterOfCurrentWordSafely(0));


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

    }

    /**
     * Update method to process game logic updates based on the time since the last frame.
     * @param delta Time passed since the last frame, in seconds.
     */

    boolean mediumPassed = false;
    @Override
    public void update(float delta) {

        // Implementation adjusted to remove references to the removed 'enemy'
        entityManager.movement(pacman);

        for (nonPlayer enemy : listNonPlayerEnemy) {
            entityManager.movement(enemy);
        }

        if (HealthyGameLogic.getInstance().getScore() >= HealthyGameLogic.getInstance().getScoreGoal()) {
            mediumPassed = true;
            HealthyGameLogic.Difficulty currentDifficulty = HealthyGameLogic.getInstance().getCurrentDifficulty();

            if (currentDifficulty == HealthyGameLogic.Difficulty.MEDIUM && mediumPassed) {
                HealthyGameLogic.getInstance().restartScore();


                // Reset
                EntityManager.getInstance().setAllEntitiesRemoved(true);
                EntityManager.getInstance().setxCords(pacman, 100);
                EntityManager.getInstance().setyCords(pacman, 100);

                // Switch scene
                HealthyGameLogic.getInstance().setCurrentDifficulty(HealthyGameLogic.Difficulty.HARD);
                HealthyGameLogic.getInstance().selectNewWord();

                // !! Required to change to setCanvas
                SceneManager.getInstance().setScene("HardStage");
//                CanvasManager.getInstance().setCanvas(new GameCanvas());
            }

            if (!mediumPassed) {
                CanvasManager.getInstance().setCanvas(new GameOverCanvas());
                // Reset difficulty
                HealthyGameLogic.getInstance().setCurrentDifficulty(HealthyGameLogic.Difficulty.EASY);
            }
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
