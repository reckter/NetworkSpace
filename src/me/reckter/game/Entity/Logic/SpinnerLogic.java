package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.Enemy.Spinner;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by mediacenter on 05.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class SpinnerLogic extends BaseLogic implements TickEventHandler<Spinner> {
	public SpinnerLogic(Level level) {
		super(new Matching("spinner"), level);
	}
	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, Spinner entity) {
		Position position = entity.getComponent(Position.class);
		position.angle += ((float) delta / 1000 ) * entity.getComponent(me.reckter.game.Entity.Component.Spinner.class).turnSpeed;
		position.angle %= 360;
		entity.getComponent(Abilities.class).castWeapon();
		//TODO weapon
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
