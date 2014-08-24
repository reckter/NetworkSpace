package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by reckter on 26.07.2014.
 */
public class GravityLogic extends BaseLogic implements TickEventHandler {

    public Vector2f gravity;
    public GravityLogic(Level level) {
        super(new Matching("gravity"), level);
        gravity = new Vector2f(0,1);
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, BaseEntity entity) {
        me.reckter.game.Entity.Component.Movement movement = entity.getComponent(me.reckter.game.Entity.Component.Movement.class);
        movement.movement.add(gravity.copy().scale((float)delta / 1000));
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.MIDDLE;
    }
}
