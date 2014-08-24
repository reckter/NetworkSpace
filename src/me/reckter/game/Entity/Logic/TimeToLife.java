package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.DeathEvent;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class TimeToLife extends BaseLogic implements TickEventHandler {
	public TimeToLife(Level level) {
		super(new Matching("timeToLife"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		me.reckter.game.Entity.Component.TimeToLife timeToLife = entity.getComponent(me.reckter.game.Entity.Component.TimeToLife.class);
		timeToLife.timeToLife -= delta;
		if(timeToLife.timeToLife <= 0) {
			fire(new DeathEvent(entity, entity));
		}
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGH;
	}
}
