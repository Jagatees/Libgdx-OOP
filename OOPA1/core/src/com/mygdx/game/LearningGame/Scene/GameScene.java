package com.mygdx.game.LearningGame.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Engine.Controller.AIControlManagement;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Collision.CollisionManager;
import com.mygdx.game.Engine.Entity.*;
import com.mygdx.game.Engine.Controller.PlayerControllerManagement;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Scenes.SceneManager;
import com.mygdx.game.Engine.Scenes.TemplateScene;
import com.mygdx.game.LearningGame.GameLogic.LearningGameLogic;
import com.mygdx.game.Engine.Timer.Timer;
import com.mygdx.game.LearningGame.UserInterface.GameCanvas;
import com.mygdx.game.LearningGame.UserInterface.GameOverCanvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The GameScene file a like clone of the TemplateScene as that is the Base version
 */
public class GameScene extends TemplateScene {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private CanvasManager canvasManager;
    private Entity pacman;
    private Entity boxPlayer;

    private Entity sabotageCube1;
    private Entity sabotageCube2;
    private List<nonPlayer> listNonPlayerEnemy = new ArrayList<>();
    private Entity.EntityType entityType;
    EntityFactory entityFactory = new EntityFactory();
    int minX = 40;
    int maxX = 1210;
    int minY = 40;
    int maxY = 650;
    private Texture background;
    private int previousScore = LearningGameLogic.getInstance().getScore();

    private float lightIntensity = 1f;
    private boolean increasingIntensity = false;
    private float darknessDelayTimer = 0f;
    private final float darknessDelayDuration = 0.5f;

    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     */
    public GameScene() {
        recreateSceneEasy();
    }

    /**
     * Render method called every frame to draw the scene's entities and background.
     */
    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        entityManager.render(batch, shapeRenderer);

        float delta = Gdx.graphics.getDeltaTime();
        canvasManager.render(delta);
        canvasManager.update(delta);

        System.out.println(Timer.getInstance().getTime());


        int currentScore = LearningGameLogic.getInstance().getScore();
        if (currentScore != previousScore) {
            // Do something when the score changes
            CanvasManager.getInstance().setCanvas(new GameCanvas());
            previousScore = currentScore; // Update the previous score
        }

        LearningGameLogic.Difficulty currentDifficulty2 = LearningGameLogic.getInstance().getCurrentDifficulty();

        if (currentDifficulty2 == LearningGameLogic.Difficulty.HARD) {

            // Use lightIntensity to simulate light conditions
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0, 0, 0, 1 - lightIntensity)); // Adjust alpha based on lightIntensity
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }



    }

    boolean easyPassed = false;
    boolean mediumPassed = false;
    boolean hardPassed = false;

    @Override
    public void update(float delta) {

        // Implementation adjusted to remove references to the removed 'enemy'
        entityManager.movement(pacman);

        for (nonPlayer enemy : listNonPlayerEnemy) {
            entityManager.movement(enemy);
        }




        /** Constantly checks if current score has met the goal **/
        boolean restartGame = false;
        LearningGameLogic.Difficulty currentDifficulty = LearningGameLogic.getInstance().getCurrentDifficulty();

        if (currentDifficulty == LearningGameLogic.Difficulty.HARD) {
            entityManager.movement(sabotageCube1);
            entityManager.movement(sabotageCube2);

            if (!SimulationLifecycleManagement.getInstance().isPaused()){
                if (increasingIntensity) {
                    if (darknessDelayTimer > 0) {
                        // If we are in the delay phase, decrease the timer
                        darknessDelayTimer -= delta;
                    } else {
                        // Once the delay is over, start increasing the light intensity
                        lightIntensity += 0.1f * delta;
                        if (lightIntensity >= 1) {
                            lightIntensity = 1;
                            increasingIntensity = false; // Prepare to decrease intensity next cycle
                        }
                    }
                } else {
                    lightIntensity -= 0.1f * delta; // Decrease light intensity
                    if (lightIntensity <= 0) {
                        lightIntensity = 0;
                        increasingIntensity = true; // Start increasing the intensity
                        darknessDelayTimer = darknessDelayDuration; // Reset the delay timer
                    }
                }
            } else {
                lightIntensity = 1f;

            }
        }

        if (LearningGameLogic.getInstance().getScore() >= LearningGameLogic.getInstance().getScoreGoal()) {

            if (currentDifficulty == LearningGameLogic.Difficulty.EASY) {
                easyPassed = true;

                /** Restarts the score to prepare for next stage **/
                LearningGameLogic.getInstance().restartScore();

                /** Changes the difficulty of the stage, selects a new word from the new difficulty word list **/
                LearningGameLogic.getInstance().setCurrentDifficulty(LearningGameLogic.Difficulty.MEDIUM);
                LearningGameLogic.getInstance().selectNewWord();
                LearningGameLogic.getInstance().setScoreGoal(LearningGameLogic.getInstance().getCurrentWordLength());

                /** Stage with more medium difficulty now **/
                SceneManager.getInstance().setScene("GameStage");
                restartGame = true;

                /** Reset Entities **/
                EntityManager.getInstance().removeAllEntitiesCompletely();


            } else if (currentDifficulty == LearningGameLogic.Difficulty.MEDIUM) {
                mediumPassed = true;

                /** Restarts the score to prepare for next stage **/
                LearningGameLogic.getInstance().restartScore();

                /** Changes the difficulty of the stage, selects a new word from the new difficulty word list **/
                LearningGameLogic.getInstance().setCurrentDifficulty(LearningGameLogic.Difficulty.HARD);
                LearningGameLogic.getInstance().selectNewWord();
                LearningGameLogic.getInstance().setScoreGoal(LearningGameLogic.getInstance().getCurrentWordLength());

                /** Stage with more hard difficulty now **/
                SceneManager.getInstance().setScene("GameStage");
                restartGame = true;

                /** Reset Entities **/
                EntityManager.getInstance().removeAllEntitiesCompletely();


            } else if (currentDifficulty == LearningGameLogic.Difficulty.HARD) {
                hardPassed = true;

                /** Restarts the score to prepare for next stage **/
                LearningGameLogic.getInstance().restartScore();

                /** Reset Entities **/
                EntityManager.getInstance().removeAllEntitiesCompletely();

                /** Transitions to end game scene **/
                restartGame = true;
            }
        }

        if (restartGame) {
            if (currentDifficulty == LearningGameLogic.Difficulty.EASY && easyPassed) {
                recreateSceneMedium();
            }

            else if (currentDifficulty == LearningGameLogic.Difficulty.MEDIUM && mediumPassed) {
                recreateSceneHard();
            }

            else if (currentDifficulty == LearningGameLogic.Difficulty.HARD && hardPassed) {
                CanvasManager.getInstance().setCanvas(new GameOverCanvas());
                SceneManager.getInstance().setScene("GameOver");
            }
        }
    }

    private void recreateSceneEasy() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = EntityManager.getInstance();

        /** Creation of player entity via entity factory **/
        pacman = entityFactory.getEntityByInput("Player", "entity/bee.png", null, 100, 100, 10, Entity.EntityState.NULL, false, false, 50, 50, Entity.EntityType.PLAYER, Entity.RenderType.SPRITE);

        createWall("entity/wall.jpg", -20, 0, 15, 50, 0, true);
        createWall("entity/wall.jpg", 0, -20, 30, 50, 0, false);
        createWall("entity/wall.jpg", 0, 700, 30, 50, 0, false);
        createWall("entity/wall.jpg", 1260, 0, 15, 50, 0, true);

        entityManager.addEntity(pacman);

        /** Assignment of player controller for created player **/
        PlayerControllerManagement playerControllerManagement = new PlayerControllerManagement((Player) pacman, entityManager, collisionManager);
        entityManager.setPlayerController((Player) pacman, playerControllerManagement);

        canvasManager = CanvasManager.getInstance();

        /** Performs various checks to ensure that non-player entities are not spawned on top of objects that have already spawned **/
        String word = LearningGameLogic.getInstance().getCurrentWord();

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
                    } else {
                        charOverlap = false;
                    }
                }

                if (!charOverlap) {
                    /** Checks for the character of the current index of word, and sets entityType to the EntityType of character found **/
                    switch (String.valueOf(word.charAt(i))) {
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
                    listNonPlayerEnemy.add((nonPlayer) tempEnemy);
                }
            }
        }

        /** Assignment of AI Controller for some non-player entities **/
        for (nonPlayer enemy : listNonPlayerEnemy) {
            AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
            entityManager.setAIController(enemy, aiControlManagement);
        }

        background = new Texture("background/bg-5.jpg");

    }

    private void recreateSceneMedium() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = EntityManager.getInstance();
        EntityFactory entityFactory = new EntityFactory();

        /** Creation of player entity via entity factory **/
        pacman = entityFactory.getEntityByInput("Player", "entity/bee.png", null, 100, 100, 10, Entity.EntityState.NULL, false, false,50, 50, Entity.EntityType.PLAYER, Entity.RenderType.SPRITE);
        entityManager.addEntity(pacman);

        createWall("entity/wall.jpg", -20, 0, 15, 50, 0, true);
        createWall("entity/wall.jpg", 0, -20, 30, 50, 0, false);
        createWall("entity/wall.jpg", 0, 700, 30, 50, 0, false);
        createWall("entity/wall.jpg", 1260, 0, 15, 50, 0, true);

        /** Creation of non-player entity which is not an AI via entity factory **/

        int minX = 40;
        int maxX = 1210;
        int minY = 40;
        int maxY = 650;

        /** Spawns 10 obstructions (grey boxes) **/
        for (int i = 0; i < 10; i++) {
            boolean overlap = true;

            while (overlap) {
                Random rand = new Random();
                int randomX = rand.nextInt(maxX - minX + 1) + minX;
                int randomY = rand.nextInt(maxY - minY + 1) + minY;

                for (Entity entity : entityManager.getAllEntities()) {
                    float entityX = EntityManager.getInstance().getxCords(entity);
                    float entityY = EntityManager.getInstance().getyCords(entity);

                    /** Gives about +- 50 padding/margin to check **/
                    if (Math.abs(randomX - entityX) <= 50 && Math.abs(randomY - entityY) <= 50) {
                        overlap = true; // Break and try another set of x and y
                        break;
                    }

                    else {
                        overlap = false;
                    }
                }

                if (!overlap) {
                    boxPlayer = entityFactory.getEntityByInput("nonPlayer", null, Color.GRAY, randomX, randomY, 10, Entity.EntityState.NULL, false, false, 50, 50, Entity.EntityType.OBJECT, Entity.RenderType.SHAPE);
                    entityManager.addEntity(boxPlayer);
                }
            }
        }

        /** Assignment of player controller for created player **/
        PlayerControllerManagement playerControllerManagement = new PlayerControllerManagement((Player)pacman, entityManager, collisionManager);
        entityManager.setPlayerController((Player)pacman, playerControllerManagement);

        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(new com.mygdx.game.LearningGame.UserInterface.GameCanvas());


        /** Performs various checks to ensure that non-player entities are not spawned on top of objects that have already spawned **/
        String word = LearningGameLogic.getInstance().getCurrentWord();

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

        /** Assignment of AI Controller for some non-player entities **/
        for (nonPlayer enemy : listNonPlayerEnemy) {
            AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
            entityManager.setAIController(enemy, aiControlManagement);
        }

        background = new Texture("background/bg-3.jpg");

    }


    private void recreateSceneHard() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager();
        entityManager = EntityManager.getInstance();
        EntityFactory entityFactory = new EntityFactory();

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
        canvasManager.setCanvas(new com.mygdx.game.LearningGame.UserInterface.GameCanvas());

        /** Performs various checks to ensure that non-player entities are not spawned on top of objects that have already spawned **/
        String word = LearningGameLogic.getInstance().getCurrentWord();

        int minX = 40;
        int maxX = 1210;
        int minY = 40;
        int maxY = 650;

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

        /** Assignment of AI Controller for some non-player entities **/
        for (nonPlayer enemy : listNonPlayerEnemy) {
            AIControlManagement aiControlManagement = new AIControlManagement(enemy, entityManager, collisionManager);
            entityManager.setAIController(enemy, aiControlManagement);
        }

        /** Creation of 2x sabotage cube (specific to hard stage) **/

        sabotageCube1 = entityFactory.getEntityByInput("nonPlayer", null, Color.PURPLE, 600, 400, 70, Entity.EntityState.NULL, true, false, 150, 150, Entity.EntityType.SABO_BOX, Entity.RenderType.SHAPE);
        entityManager.addEntity(sabotageCube1);
        AIControlManagement sabotageCube1AIController = new AIControlManagement((nonPlayer) sabotageCube1, entityManager, collisionManager);
        entityManager.setAIController((nonPlayer) sabotageCube1, sabotageCube1AIController);

        sabotageCube2 = entityFactory.getEntityByInput("nonPlayer", null, Color.PURPLE, 300, 300, 70, Entity.EntityState.NULL, true, false, 150, 150, Entity.EntityType.SABO_BOX, Entity.RenderType.SHAPE);
        entityManager.addEntity(sabotageCube2);
        AIControlManagement sabotageCube2AIController = new AIControlManagement((nonPlayer) sabotageCube2, entityManager, collisionManager);
        entityManager.setAIController((nonPlayer) sabotageCube2, sabotageCube2AIController);


        background = new Texture("background/bg-2.jpg");
    }

    /**
     * Dispose method to clean up resources when the scene is no longer in use.
     */
    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        shapeRenderer.dispose();
        canvasManager.dispose();
    }

    @Override
    public void create() {

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
