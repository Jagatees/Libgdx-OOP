package com.mygdx.game.Collision;

import com.badlogic.gdx.Input;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;

public class CollisionManager {

    public boolean checkCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;
    }


    public void checkResponse(Entity.EntityType type, Entity.EntityType type1) {


    }
}


