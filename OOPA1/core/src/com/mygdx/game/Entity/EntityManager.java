package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void render(SpriteBatch spriteBatch) {
        for (Entity entity : entities) {
            entity.render(spriteBatch);
        }
    }

    // Method to return a copy of the list of all entities
    public List<Entity> getAllEntities() {
        return new ArrayList<>(entities);
    }

    public <T extends Entity> List<T> getEntitiesOfType(Class<T> type) {
        List<T> matchingEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                matchingEntities.add(type.cast(entity));
            }
        }
        return matchingEntities;
    }

    public <T extends Entity> T getEntity(Class<T> type) {
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                return type.cast(entity);
            }
        }
        return null;
    }

    public List<Entity> getAllEntitiesExcept(Entity exception) {
        List<Entity> filteredEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (!entity.equals(exception)) {
                filteredEntities.add(entity);
            }
        }
        return filteredEntities;
    }

    public float getxCords(Entity entity) {
        return entity.getxCords();
    }

    public float getyCords(Entity entity) {
        return entity.getyCords();
    }

    public float getWidth(Entity entity) {
        return entity.getWidth();
    }

    public float getHeight(Entity entity) {
        return entity.getHeight();
    }

    public float getSpeed(Entity entity) {
        return entity.getSpeed();
    }

    public Entity.EntityType getType(Entity entity) {
        return entity.getEntityType();
    }
}
