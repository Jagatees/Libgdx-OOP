package com.mygdx.game.Engine.Entity;

import com.mygdx.game.Engine.Entity.Entity;
import com.mygdx.game.Engine.Entity.Player;
import com.badlogic.gdx.graphics.Color;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Color;
public class EntityFactory {

    /**
     Takes in a series of parameters when calling getEntityByInput
     **/


    public Entity getEntityByInput(String gameEntityType, String tex, Color color, float xCords, float yCords, float speed, Entity.EntityState state, boolean isAI, boolean isWall, float width, float height, Entity.EntityType entityType, Entity.RenderType renderType) {

        if (gameEntityType == null) {
            return null;
        }

        switch (gameEntityType.toLowerCase()) {

            /** Checks if initiated string is "player" **/
            case "player":

                /** Checks if tex/color is null, isWall to be set to false for player types and returns the Player object accordingly **/

                if (tex != null && !isWall) {
                    return new Player(tex, xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
                } else if (color != null && !isWall) {
                    return new Player(color, xCords, yCords, speed, state, isAI, width, height, entityType, renderType);
                }

                break;


            /** Checks if initiated string is "nonplayer" **/
            case "nonplayer":
                /** Checks if tex/color is null, and returns the nonPlayer object accordingly **/

                if (tex != null) {
                    return new nonPlayer(tex, xCords, yCords, speed, state, isAI, isWall, width, height, entityType, renderType);
                } else if (color != null) {
                    return new nonPlayer(color, xCords, yCords, speed, state, isAI, isWall, width, height, entityType, renderType);
                }

                break;

            default:
                return null;
        }

        return null;
    }
}