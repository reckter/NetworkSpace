package me.reckter.client.entities;

import me.reckter.network.ClientEngine;
import org.newdawn.slick.Graphics;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Created by hannes on 11/08/14.
 */
public class EntityHandler {

    public HashMap<Integer, Entity> entities;

    public EntityHandler() {
        entities = new HashMap<>();
    }

    public void update(final int delta) {
        entities.values().forEach(a -> a.update(delta));
    }

    public void render(Graphics g) {
        for(Entity entity: entities.values()) {
            entity.render(g);
        }
        //entities.values().forEach(a -> a.render(g));
    }

    public Entity getPlayer() {
        try {
            return entities.values().parallelStream().filter(entity -> entity.player == ClientEngine.id).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }

      /*
        for(Entity entity: entities.values()) {
            if(entity.player == ClientEngine.id) {
                return entity;
            }
        }

        return null;
        */
    }

}
