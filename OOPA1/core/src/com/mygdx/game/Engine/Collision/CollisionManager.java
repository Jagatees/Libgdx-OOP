package com.mygdx.game.Engine.Collision;

import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.LearningGame.LearningCollision.CollisionHandler;

/**
 * Manages collision detection for entities within the game.
 * Provides methods to check for collisions and handle collision responses between entities.
 */
public class CollisionManager {

    private CollisionHandler collisionHandler = new CollisionHandler();

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
        collisionHandler.handleCollision(entity1, entity2);
    }

}

