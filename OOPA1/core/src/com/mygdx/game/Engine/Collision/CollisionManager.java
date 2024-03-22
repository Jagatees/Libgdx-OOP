package com.mygdx.game.Engine.Collision;

import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.Engine.Entity.EntityManager;
import com.mygdx.game.LearningGame.GameLogic.LearningGameLogic;
import com.mygdx.game.LearningGame.GameLogic.Timer;

import java.util.Objects;

/**
 * Manages collision detection for entities within the game.
 * Provides methods to check for collisions and handle collision responses between entities.
 */
public class CollisionManager {

    private LearningGameLogic learningGameLogic = LearningGameLogic.getInstance();

    /**
     * Checks if two rectangular entities intersect.
     * This method is a basic AABB (Axis-Aligned Bounding Box) collision check.
     *
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
     */
    public void checkResponse(Entity entity1, Entity entity2) {
        Entity.EntityType entityType1 = EntityManager.getInstance().getType(entity1);
        Entity.EntityType entityType2 = EntityManager.getInstance().getType(entity2);

        String word = learningGameLogic.getCurrentWord();
        char expectedLetter = learningGameLogic.getFirstLetterOfCurrentWordSafely(learningGameLogic.getScore());

        if (learningGameLogic.getScore() != word.length()) {
            if (Objects.equals(entityType2.getLetter(), String.valueOf(expectedLetter)) ||
                    Objects.equals(entityType1.getLetter(), String.valueOf(expectedLetter))) {

                if ((entityType1 == Entity.EntityType.PLAYER && entityType2.isLetter()) ||
                        (entityType2 == Entity.EntityType.PLAYER && entityType1.isLetter())) {

                    learningGameLogic.addScore(1);

                    if (entityType1 == Entity.EntityType.PLAYER) {
                        EntityManager.getInstance().removeEntity(entity2);
                    } else {
                        EntityManager.getInstance().removeEntity(entity1);
                    }

                    System.out.println("Current Difficulty: " + learningGameLogic.getCurrentDifficulty());
                    System.out.println("Current Word: " + learningGameLogic.getCurrentWord());
                    System.out.println("Current Score: " + learningGameLogic.getScore());

                    if (learningGameLogic.getScore() != learningGameLogic.getCurrentWord().length()) {
                        System.out.println("Next Letter: " + expectedLetter);
                    }
                }
            } else {
                // Wrong letter hit
                System.out.println("Add time");
                Timer.getInstance().addToTimer();
            }
        }
    }

}

