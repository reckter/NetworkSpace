package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.AcceleratingEvent;
import me.reckter.game.Entity.Events.Handlers.AcceleratingEventHandler;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Movement extends BaseLogic implements TickEventHandler, AcceleratingEventHandler {
	public Movement(Level level) {
		super(new Matching("movement"), level);
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
		Position position = entity.getComponent(Position.class);
		position.coords.add(movement.movement.copy().scale((float) delta / 1000));
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGH;
	}

	/**
	 * gets called when the given entity accelerates
	 *
	 * @param event
	 */
	@Override
	public void onAcceleration(AcceleratingEvent event) {
		me.reckter.game.Entity.Component.Movement movement = event.victim.getComponent(me.reckter.game.Entity.Component.Movement.class);
		Position position = event.victim.getComponent(Position.class);

		movement.movement.add(new Vector2f(event.direction.normalise()).scale(event.acceleration).scale((float) event.delta / 1000));
		if(event.direction.equals(new Vector2f(event.victim.getComponent(Position.class).angle).normalise())) {
			for(int i = 0; i < event.delta / 2 + 5 / event.delta ; i++) {
				//level.add(new RocketParticle(level, event.victim, event.direction));
			}
		}
	}
}
