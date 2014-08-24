package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Component.Movement;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BouncesLogic extends BaseLogic implements TickEventHandler {
	public BouncesLogic(Level level) {
		super(new Matching("bounces"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		Position position = entity.getComponent(Position.class);
		me.reckter.game.Entity.Component.Movement movement = entity.getComponent(Movement.class);

        /*
		if(position.coords.x < 0) {
			position.coords.x = 0;
			movement.movement.x = -movement.movement.x;
		}
		if(position.coords.x > entity.level.WIDTH) {
			position.coords.x = entity.level.WIDTH;
			movement.movement.x = -movement.movement.x;
		}
		if(position.coords.y < 0) {
			position.coords.y = 0;
			movement.movement.y = -movement.movement.y;
		}
		if(position.coords.y > entity.level.HEIGHT) {
			position.coords.y = entity.level.HEIGHT;
			movement.movement.y = -movement.movement.y;
		}
		*/

	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.LOWEST;
	}
}
