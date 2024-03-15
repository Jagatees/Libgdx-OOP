package com.mygdx.game.Engine.Collision;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.Engine.Entity.EntityManager;
import com.mygdx.game.HealthyGame.GameLogic.HealthyGameLogic;

import java.util.Objects;

/**
 * Manages collision detection for entities within the game.
 * Provides methods to check for collisions and handle collision responses between entities.
 */
public class CollisionManager {

    HealthyGameLogic healthyGameLogic = HealthyGameLogic.getInstance();


    /**
     * Checks if two rectangular entities intersect.
     * This method is a basic AABB (Axis-Aligned Bounding Box) collision check.
     *
     * @param x1 The x-coordinate of the first entity's bottom-left corner.
     * @param y1 The y-coordinate of the first entity's bottom-left corner.
     * @param width1 The width of the first entity.
     * @param height1 The height of the first entity.
     * @param x2 The x-coordinate of the second entity's bottom-left corner.
     * @param y2 The y-coordinate of the second entity's bottom-left corner.
     * @param width2 The width of the second entity.
     * @param height2 The height of the second entity.
     * @return true if the entities intersect, false otherwise.
     */
    public boolean checkCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;
    }

    /**
     * Handles the response to a collision between two entities based on their types.
     * This method can be expanded to include specific collision handling logic
     * depending on the types of entities involved.
     *
     * @param type The type of the first entity involved in the collision.
     * @param type1 The type of the second entity involved in the collision.
     */
    public void checkResponse(Entity type, Entity type1) {



        // get current word

        String word = healthyGameLogic.getCurrentWord();

        Entity.EntityType typeEntityType = EntityManager.getInstance().getType(type);
        Entity.EntityType type1EntityType = EntityManager.getInstance().getType(type1);

        System.out.println("Type: " + typeEntityType + " Type2: " + type1EntityType);


        if (HealthyGameLogic.getInstance().getScore() != word.length()) {
            // Make sure get the right letter first
            if (Objects.equals(type1EntityType.getLetter(), String.valueOf(HealthyGameLogic.getInstance().getFirstLetterOfCurrentWordSafely(HealthyGameLogic.getInstance().getScore()))) ||
                    Objects.equals(typeEntityType.getLetter(), String.valueOf(HealthyGameLogic.getInstance().getFirstLetterOfCurrentWordSafely(HealthyGameLogic.getInstance().getScore()))

                    ))
            {
                System.out.println("it match");

                // Check if one entity is PLAYER and the other is any letter from A to Z.
                if ((typeEntityType == Entity.EntityType.PLAYER && type1EntityType.isLetter()) ||
                    (type1EntityType == Entity.EntityType.PLAYER && typeEntityType.isLetter())) {

                    // Increment score regardless of which entity is the PLAYER
                    healthyGameLogic.addScore(1);

                    // Remove the non-PLAYER entity
                    if (typeEntityType == Entity.EntityType.PLAYER) {
                        EntityManager.getInstance().removeEntity(type1);
                    } else {
                        EntityManager.getInstance().removeEntity(type);
                    }

                    // Output current score
                    System.out.println("Current Score: " + HealthyGameLogic.getInstance().getScore());

                    // Check score against current word length
                    if (HealthyGameLogic.getInstance().getScore() != healthyGameLogic.getCurrentWord().length()) {
                        System.out.println("Next Letter: " + HealthyGameLogic.getInstance().getFirstLetterOfCurrentWordSafely(HealthyGameLogic.getInstance().getScore()));
                    }
                }

        }


        }


    }
}


