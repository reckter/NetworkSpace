package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MaximalMovement extends BaseLogic implements TickEventHandler {

	float MAX_MOVEMENT = 5000;

	public MaximalMovement(Level level) {
		super(new Matching("maximalMovement"), level);
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
		me.reckter.game.Entity.Component.MaximalMovement maximalMovement = entity.getComponent(me.reckter.game.Entity.Component.MaximalMovement.class);
		if (movement.movement.lengthSquared() > maximalMovement.maximalSpeed * maximalMovement.maximalSpeed) {
			movement.movement.normalise().scale(maximalMovement.maximalSpeed);
		}
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
