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


}