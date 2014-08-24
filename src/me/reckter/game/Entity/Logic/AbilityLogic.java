package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;
import me.reckter.game.Abilities.*;

/**
 * Created by mediacenter on 10.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class AbilityLogic extends BaseLogic implements TickEventHandler {
	public AbilityLogic(Level level) {
		super(new Matching("ability"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		AbilityHandler handler = entity.getComponent(Abilities.class).abilities;
		handler.logic(delta);
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
