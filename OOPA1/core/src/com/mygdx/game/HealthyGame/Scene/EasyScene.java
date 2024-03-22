package com.mygdx.game.HealthyGame.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Engine.Canvas.Canvas;
import com.mygdx.game.Engine.Controller.AIControlManagement;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Collision.CollisionManager;
import com.mygdx.game.Engine.Entity.*;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.Scenes.SceneManager;
import com.mygdx.game.Engine.Scenes.TemplateScene;
import com.mygdx.game.HealthyGame.GameLogic.HealthyGameLogic;
import com.mygdx.game.HealthyGame.GameLogic.Timer;
import com.mygdx.game.HealthyGame.UserInterface.GameCanvas;
import com.mygdx.game.HealthyGame.UserInterface.GameOverCanvas;
import com.mygdx.game.HealthyGame.UserInterface.MainMenuCanvas;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * The GameScene file a like clone of the TemplateScene as that is the Base version
 */
public class EasyScene extends TemplateScene {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private CanvasManager canvasManager;
    private Entity pacman;
    private List<nonPlayer> listNonPlayerEnemy = new ArrayList<>();
    private Entity.EntityType entityType;
    EntityFactory entityFactory = new EntityFactory();
    private List<Rectangle> spawnLocations = new ArrayList<>();
    // Spawn Enemy
    Random rand = new Random();
    int minX = 40;
    int maxX = 1210;
    int minY = 40;
    int maxY = 650;

    List<Rectangle> occupiedAreas = new ArrayList<>();
    private int previousScore = HealthyGameLogic.getInstance().getScore();


    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     */
    public EasyScene() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = EntityManager.getInstance();

        /** Creation of player entity via entity factory **/
        pacman = entityFactory.getEntityByInput("Player", "entity/bee.png", null, 100, 100, 10, Entity.EntityState.NULL, false, false,50, 50, Entity.EntityType.PLAYER, Entity.RenderType.SPRITE);

        createWall("entity/wall.jpg", -20, 0, 15, 50, 0, true);
        createWall("entity/wall.jpg", 0, -20, 30, 50, 0, false);
        createWall("entity/wall.jpg", 0, 700, 30, 50, 0, false);
        createWall("entity/wall.jpg", 1260, 0, 15, 50, 0, true);

        entityManager.addEntity(pacman);

        /** Assignment of player controller for created player **/
        PlayerControllerManagement playerControllerManagement = new PlayerControllerManagement((Player)pacman, entityManager, collisionManager);
        entityManager.setPlayerController((Player)pacman, playerControllerManagement);

        canvasManager = CanvasManager.getInstance();

        /** Performs various checks to ensure that non-player entities are not spawned on top of objects that have already spawned **/
        String word = HealthyGameLogic.getInstance().getCurrentWord();

        for (int i = 0; i < word.length(); i++) {
            boolean charOverlap = true;

            /** while charOverlap is true, generate a random pair of x and y coordinates */
            while (charOverlap) {
                Random rand = new Random();
                int charX = rand.nextInt(maxX - minX + 1) + minX;
                int charY = rand.nextInt(maxY - minY + 1) + minY;

                /** Checks against all entities that have been added for their x and y pos **/
                for (Entity entity : entityManager.getAllEntities()) {
                    float entityX = EntityManager.getInstance().getxCords(entity);
                    float entityY = EntityManager.getInstance().getyCords(entity);

                    /** Gives about +- 50 padding/margin to check **/
                    if (Math.abs(charX - entityX) <= 50 && Math.abs(charY - entityY) <= 50) {
                        charOverlap = true; // Break and try another set of x and y
                        break;
                    }

                    else {
                        charOverlap = false;
                    }
                }

                if (!charOverlap) {
                    /** Checks for the character of the current index of word, and sets entityType to the EntityType of character found **/
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
                        /** Defaults to breaking the loop, should a case not be found **/
                        default:
                            break;
                    }

                    /** Create letters nonPlayer AI objects via entity factory **/
                    Entity tempEnemy = entityFactory.getEntityByInput("nonPlayer", "Words/" + String.valueOf(word.charAt(i)) + ".png", null, charX, charY, 10, Entity.EntityState.NULL, true, false,
                            50, 50, entityType, Entity.RenderType.SPRITE);
                    entityManager.addEntity(tempEnemy);
                    listNonPlayerEnemy.add((nonPlayer)tempEnemy);
                }
            }
        }

        /** Assignemnt of AI Controller for some non-player entities **/
        for (nonPlayer enemy : listNonPlayerEnemy) {
            AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
            entityManager.setAIController(enemy, aiControlManagement);
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


        System.out.println(Timer.getInstance().getTime());


        int currentScore = HealthyGameLogic.getInstance().getScore();
        if (currentScore != previousScore) {
            // Do something when the score changes
            CanvasManager.getInstance().setCanvas(new GameCanvas());
            previousScore = currentScore; // Update the previous score
        }

    }

    boolean easyPassed = false;
    @Override
    public void update(float delta) {

        // Implementation adjusted to remove references to the removed 'enemy'
        entityManager.movement(pacman);

        for (nonPlayer enemy : listNonPlayerEnemy) {
            entityManager.movement(enemy);
        }


        /** Constantly checks if current score has met the goal **/
        if (HealthyGameLogic.getInstance().getScore() >= HealthyGameLogic.getInstance().getScoreGoal()) {
            easyPassed = true;
            HealthyGameLogic.Difficulty currentDifficulty = HealthyGameLogic.getInstance().getCurrentDifficulty();

            if (currentDifficulty == HealthyGameLogic.Difficulty.EASY && easyPassed) {
                /** Restarts the score to prepare for next stage **/
                HealthyGameLogic.getInstance().restartScore();

                /** Resets entities **/
//                EntityManager.getInstance().setAllEntitiesRemoved(true);
                EntityManager.getInstance().setxCords(pacman, 100);
                EntityManager.getInstance().setyCords(pacman, 100);

                /** Changes the difficulty of the stage, selects a new word from the new difficulty word list **/
                HealthyGameLogic.getInstance().setCurrentDifficulty(HealthyGameLogic.Difficulty.MEDIUM);
                HealthyGameLogic.getInstance().selectNewWord();
                HealthyGameLogic.getInstance().setScoreGoal(HealthyGameLogic.getInstance().getCurrentWordLength());

                EntityManager.getInstance().removeAllEntitiesCompletely();

                /** Transitions into another scene to introduce more "difficulty-specific" elements (i.e. blocks/spikes/etc.) **/
                SceneManager.getInstance().setScene("MediumStage");


            }

            /** Transition to game over scene since player did not pass this stage **/
            if (!easyPassed) {
                CanvasManager.getInstance().setCanvas(new GameOverCanvas());
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


    // Method to check if the given point is too close to any existing entity
    private boolean isTooCloseToEntity(int x, int y, List<Rectangle> occupiedAreas, int minDistance) {
        for (Rectangle area : occupiedAreas) {
            double distance = Math.sqrt(Math.pow(x - area.getX(), 2) + Math.pow(y - area.getY(), 2));
            if (distance < minDistance) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a point is occupied by any entity
    private boolean isPointOccupied(int x, int y, List<Rectangle> occupiedAreas) {
        for (Rectangle area : occupiedAreas) {
            if (area.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    /** Method to return boolean value based on checks if x and y are too close to player object **/
    private boolean tooCloseToPlayer(int x, int y, Entity player) {
        if (player != null) {
            float playerX = EntityManager.getInstance().getxCords(player);
            float playerY = EntityManager.getInstance().getxCords(player);
            double distance = Math.sqrt(Math.pow(x - playerX, 2) + Math.pow(y - playerY, 2));
            float minDistance = EntityManager.getInstance().getWidth(player) + 20; // Adjust the buffer distance as needed
            if (distance < minDistance) {
                return true;
            }
        }
        return false;
    }


    private void createWall(String spritePath, int startX, int startY, int segments, int segmentSize, int spacing, boolean vertical) {
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

}
