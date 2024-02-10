package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();


    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities); // Return a copy to prevent external modifications
    }

    public void render(SpriteBatch spriteBatch) {
        for (Entity entity : entities) {
            entity.render(spriteBatch);
        }
    }

    // Method to get the first entity of a specific type
    public <T extends Entity> T getEntity(Class<T> type) {
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                return type.cast(entity);
            }
        }
        return null; // or throw an exception if you prefer
    }

}