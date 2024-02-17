package com.mygdx.game.Collision;

import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.InputOutput.InputOutputManger;

import java.util.List;


public class CollisionManager {

    private EntityManager entityManager;

    public CollisionManager() {
        this.entityManager = new EntityManager();
    }

    EntityManager getEntityManager(){
        return this.entityManager;
    }

    public void generalCollision(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);
                if (checkCollision(entity1, entity2)) {
                    checkResponse(getEntityManager().getType(entity1), getEntityManager().getType(entity2));
                }
            }
        }
    }

    public void checkResponse(Entity.EntityType type, Entity.EntityType type1) {
        System.out.println( type + "-hit-" + type1);

    }

    public boolean checkCollision(Entity e1, Entity e2) {
        return  getEntityManager().getxCords(e1) < getEntityManager().getxCords(e2) + getEntityManager().getWidth(e2) &&
                getEntityManager().getxCords(e1) + getEntityManager().getWidth(e1) > getEntityManager().getxCords(e2) &&
                getEntityManager().getyCords(e1) < getEntityManager().getyCords(e2) + getEntityManager().getHeight(e2) &&
                getEntityManager().getyCords(e1) + getEntityManager().getHeight(e1) > getEntityManager().getyCords(e2);
    }
}


