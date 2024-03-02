package com.mygdx.game.Engine.Collision;

import com.mygdx.game.Engine.Entity.Entity;

/**
 * Manages collision detection for entities within the game.
 * Provides methods to check for collisions and handle collision responses between entities.
 */
public class CollisionManager {
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
    public void checkResponse(Entity.EntityType type, Entity.EntityType type1) {
        System.out.println("Entity " + type + " has Collided with Entity " + type1);
    }
}

