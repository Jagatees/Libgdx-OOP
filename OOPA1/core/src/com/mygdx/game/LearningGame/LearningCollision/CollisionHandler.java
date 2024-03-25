package com.mygdx.game.LearningGame.LearningCollision;

import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.Engine.Entity.EntityManager;
import com.mygdx.game.LearningGame.GameLogic.LearningGameLogic;
import com.mygdx.game.Engine.Timer.Timer;

import java.util.Objects;

public class CollisionHandler {

    private LearningGameLogic learningGameLogic = LearningGameLogic.getInstance();

    public void handleCollision(Entity entity1, Entity entity2) {
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
                if (entityType1 != Entity.EntityType.SABO_BOX && entityType2 != Entity.EntityType.SABO_BOX) {
                    // Wrong letter hit
                    System.out.println("Add time");
                    Timer.getInstance().addToTimer();
                }
            }
        }
    }
}
