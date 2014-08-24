package me.reckter.game.Entity;

import me.reckter.Log;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.*;
import me.reckter.game.Entity.Events.Handlers.*;
import me.reckter.game.Entity.Exception.StoppEventExecutionException;
import me.reckter.game.Entity.Logic.BaseLogic;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class LogicHandler {

    HashMap<BaseLogic, ArrayList<BaseEntity>> logicsEntities;
    HashMap<Class<? extends BaseEvent>, ArrayList<BaseLogic>> logics;

    protected int delta;

    protected Queue<BaseEvent> eventQueue;

    public boolean logicsNeedUpdate;

    EntityHandler entities;

    public LogicHandler(EntityHandler entityHandler) {
        entities = entityHandler;
        eventQueue = new ConcurrentLinkedQueue<>();
        logicsEntities = new HashMap<>();
        logics = new HashMap<>();
        registerEvents();
        logicsNeedUpdate = false;
    }

    /**
     * registers all Events, new Events have to get implemented here TODO better idea?
     */
    private void registerEvents() {
        logics.put(AcceleratingEvent.class, new ArrayList<>());
        logics.put(CollisionEvent.class, new ArrayList<>());
        logics.put(CollisionEvent.class, new ArrayList<>());
        logics.put(DamageEvent.class, new ArrayList<>());
        logics.put(DeathEvent.class, new ArrayList<>());
        logics.put(EntityAddEvent.class, new ArrayList<>());
        logics.put(EntityDelEvent.class, new ArrayList<>());
        logics.put(ExplosionEvent.class, new ArrayList<>());
        logics.put(TickEvent.class, new ArrayList<>());
        logics.put(TickOnceEvent.class, new ArrayList<>());
        logics.put(InputEvent.class, new ArrayList<>());
    }


    public void logic(int delta) {
        this.delta = delta;
        entities.entities.parallelStream().forEach(entity -> fireEvent(new TickEvent<>(entity)));

        logics.get(TickOnceEvent.class).parallelStream().forEach(logic -> fireEvent(new TickOnceEvent(logicsEntities.get(logic))));

        while(!eventQueue.isEmpty()) {
            handleEvent(eventQueue.poll());
        }

        /*
        for (BaseLogic logic : logics.get(TickEvent.class)) {
			for (BaseEntity entity : logicsEntities.get(logic)) {
				((TickEventHandler) logic).onTick(delta, entity);
			}
		}
		*/
        if (logicsNeedUpdate) {
            updateLogics();
        }
    }

    public void fireEvent(BaseEvent event) {
        event.delta = this.delta;
        eventQueue.add(event);
    }

    public void handleEvent(BaseEvent event) {
        try {
            for (BaseLogic logic : logics.get(event.getClass())) {
                if (event instanceof TickOnceEvent) {
                    ((TickOnceEventHandler) logic).onTick(((TickOnceEvent) event).entities, event.delta);
                } else if (event.victim.matches(logic.matching)) {
                    if (event instanceof TickEvent) {
                        ((TickEventHandler) logic).onTick(event.delta, event.victim);
                    } else if (event instanceof DamageEvent) {
                        ((DamageEventHandler) logic).onDamage((DamageEvent) event);
                    } else if (event instanceof DeathEvent) {
                        ((DeathEventHandler) logic).onDeath((DeathEvent) event);
                    } else if (event instanceof CollisionEvent) {
                        ((CollisionEventHandler) logic).onCollsion((CollisionEvent) event);
                    } else if (event instanceof AcceleratingEvent) {
                        ((AcceleratingEventHandler) logic).onAcceleration((AcceleratingEvent) event);
                    } else if (event instanceof ExplosionEvent) {
                        ((ExplosionEventHandler) logic).onExplosion((ExplosionEvent) event);
                    } else if (event instanceof EntityAddEvent) {
                        ((EntityAddHandler) logic).onEntityAdd((EntityAddEvent) event);
                    } else if (event instanceof EntityDelEvent) {
                        ((EntityDelHandler) logic).onEntityDel((EntityDelEvent) event);
                    } else if(event instanceof InputEvent) {
                        ((InputEventHandler) logic).onInput((InputEvent) event);
                    }
                }
            }
        } catch (StoppEventExecutionException e) {
            if (e.needsToBeDisplayed) {
                Log.note("Stopped Event Execution of the event " + event + ": " + e.getMessage());
            }
        }
    }

    public void updateLogics() {
        logicsEntities.forEach((logic, entitiesTMP) -> {
            entitiesTMP.clear();
            entities.entities.stream()
                    .filter(entity -> entity.matches(logic.matching))
                    .forEach(entity -> logicsEntities.get(logic).add(entity));
        });
    }

    public void add(BaseLogic logic, Class<? extends BaseEvent>... events) {
        logicsEntities.put(logic, new ArrayList<>());

        for (Class<? extends BaseEvent> event : events) {
            logics.get(event).add(logic);
            Collections.sort(logics.get(event), new LogicComparator());
        }

        updateLogics();
    }


    public void del(BaseLogic logic) {
        logicsEntities.remove(logic);
        logics.values().parallelStream().forEach(list -> list.remove(logic));
    }

    private class LogicComparator implements Comparator<BaseEventHandler> {
        @Override
        public int compare(BaseEventHandler o1, BaseEventHandler o2) {
            return o1.getPriority().compareTo(o2.getPriority());
        }
    }
}
