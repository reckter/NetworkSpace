package me.reckter.game.Entity.Logic;

import me.reckter.Util;
import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Collision;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.CollisionEvent;
import me.reckter.game.Entity.Events.EntityAddEvent;
import me.reckter.game.Entity.Events.EntityDelEvent;
import me.reckter.game.Entity.Events.Handlers.EntityAddDelHandler;
import me.reckter.game.Entity.Events.Handlers.TickOnceEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.Entity.Exception.CollisionGroupNotFoundException;
import me.reckter.game.level.Level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class CollisionChecking extends BaseLogic implements TickOnceEventHandler, EntityAddDelHandler {


    public TreeMap<String, ArrayList<BaseEntity>> groups;
    public TreeMap<String, HashSet<String>> collision;

    public CollisionChecking(Level level) {
        super(new Matching("collision"), level);

        groups = new TreeMap<>();
        collision = new TreeMap<>();
    }

    /**
     * gets called every tick, after each entity allready got called, but only gets called once for all entities,
     * with all entities that matches the gven Matching (requires to be a sublcass of BaseLogic)
     *
     * @param entities an ArrayList of matching entities
     * @param delta    the delta between the last call and the current one
     */
    @Override
    public void onTick(ArrayList<BaseEntity> entities, int delta) {

        groups.forEach((groupA, entitiesA) -> entitiesA.parallelStream()
                .forEach((entityA) ->
                        collision.get(groupA).parallelStream()
                                .map(groups::get)
                                .collect(() -> new HashSet<BaseEntity>(),
                                        (c1, o) -> c1.addAll(o),
                                        (c1, c2) -> c1.addAll(c2)).parallelStream()
                                .filter(entityB -> entityA != entityB && Util.checkCollision(entityA, entityB))
                                .forEach((entityB -> level.fire(new CollisionEvent<>(entityA, entityB))))));


       /* for (String groupA : groups.keySet()) {
            for (String groupB : collision.get(groupA)) {

                for (BaseEntity entityA : groups.get(groupA)) {
                    checkedEntities.clear();
                    for (BaseEntity entityB : groups.get(groupB)) {

                        if (checkedEntities.contains(entityB)) {
                            continue;
                        }

                        checkedEntities.add(entityB);
                        *//*if (entityA != entityB) {
							if (Util.checkCollision(entityA, entityB)) {
								level.fire(new CollisionEvent(entityA, entityB));
							}
						}*//*
                    }
                }
            }
        }*/

    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.MIDDLE;
    }

    /**
     * gets called when an entity gets added
     *
     * @param event the entity Addition event
     */
    @Override
    public void onEntityAdd(EntityAddEvent event) {
        for (String group : event.victim.getComponent(Collision.class).collisionGroups) {
            groups.get(group).add(event.victim);
        }
    }

    /**
     * gets called when an entity gets deleted
     *
     * @param event the entity deltion event
     */
    @Override
    public void onEntityDel(EntityDelEvent event) {
        for (String group : event.victim.getComponent(Collision.class).collisionGroups) {
            groups.get(group).remove(event.victim);
        }
    }

    /**
     * adds the rule, that the collider collides with 'with', thus checking if collider is coliding with 'with'
     *
     * @param collider the group that will be checked
     * @param with     the group that will be checked against
     */
    public void addCollisionRule(String collider, String with) {
        if (!groups.containsKey(collider)) {
            throw new CollisionGroupNotFoundException("The collision group '" + collider + "' does not exist!");
        }
        if (!groups.containsKey(with)) {
            throw new CollisionGroupNotFoundException("The collision group '" + with + "' does not exist!");
        }

        collision.get(collider).add(with);
    }

    /**
     * removes a collision rule, so that collider no longer collides with 'with'
     *
     * @param collider the group that has been checked
     * @param with     the goup that has been checked against
     */
    public void dellCollisionRule(String collider, String with) {
        if (!groups.containsKey(collider)) {
            throw new CollisionGroupNotFoundException("The collision group '" + collider + "' does not exist!");
        }
        if (!groups.containsKey(with)) {
            throw new CollisionGroupNotFoundException("The collision group '" + with + "' does not exist!");
        }

        collision.get(collider).remove(with);
    }

    public void addCollisionGroup(String group) {

        groups.put(group, new ArrayList<>());
        collision.put(group, new HashSet<>());

        addCollisionRule(group, group);
    }

    public void removeCollisionGroup(String group) {
        groups.remove(group);
        collision.remove(group);

        collision.values().forEach(s -> s.remove(group));
    }


}
