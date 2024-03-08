package com.mygdx.game.Engine.Entity;

import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.Engine.Entity.Player;
import com.badlogic.gdx.graphics.Color;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Color;
public class EntityFactory {

    /** Overloading to support for different types of entity creation **/

    public Entity getEntityByInput(String gameEntityType, String tex, float xCords, float yCords, float speed, Entity.EntityState state, boolean isAI, float width, float height, Entity.EntityType entityType,
                                   Entity.RenderType renderType) {


        if (gameEntityType == null) {
            return null;
        } else if (gameEntityType.equalsIgnoreCase("Player")) {
            return new Player(tex, xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
        } else {
            return null;
        }
    }

    public Entity getEntityByInput(String gameEntityType, Color color, float xCords, float yCords, float speed, Entity.EntityState state, boolean isAI, float width, float height, Entity.EntityType entityType,
                                   Entity.RenderType renderType) {

        if (gameEntityType == null) {
            return null;
        } else if (gameEntityType.equalsIgnoreCase("Player")) {
            return new Player(color, xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
        } else {
            return null;
        }
    }

    public Entity getEntityByInput(String gameEntityType, String tex, float xCords, float yCords, float speed,
                                   Entity.EntityState state, boolean isAI, boolean isWall, float width, float height, Entity.EntityType entityType, Entity.RenderType renderType) {

        if (gameEntityType == null) {
            return null;
        } else if (gameEntityType.equalsIgnoreCase("nonPlayer")) {
            return new nonPlayer(tex, xCords, yCords, speed, state, isAI, isWall, width, height, entityType, renderType);
        } else {
            return null;
        }
    }

    public Entity getEntityByInput(String gameEntityType, Color color, float xCords, float yCords, float speed,
                                   Entity.EntityState state, boolean isAI, boolean isWall, float width, float height, Entity.EntityType entityType, Entity.RenderType renderType) {

        if (gameEntityType == null) {
            return null;
        } else if (gameEntityType.equalsIgnoreCase("nonPlayer")) {
            return new nonPlayer(color, xCords, yCords, speed, state, isAI, isWall, width, height, entityType, renderType);
        } else {
            return null;
        }
    }
}