package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Shooter extends BaseLogic implements TickEventHandler {
	public Shooter(Level level) {
		super(new Matching("shooter"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		Enemy enemy = entity.getComponent(Enemy.class);
		Position position = entity.getComponent(Position.class);
		Position targetPosition = enemy.victim.getComponent(Position.class);
		Abilities abilities = entity.getComponent(Abilities.class);

		if(targetPosition.coords.distanceSquared(position.coords) < 200 * 200) {
			//position.angle = targetPosition.coords.copy().add(position.coords.copy().negate()).getTheta();
			if(abilities.castWeapon()) {
				entity.getComponent(me.reckter.game.Entity.Component.Movement.class).acceleration = 3.5f * me.reckter.game.Entity.Component.Movement.STANDART_ACCELERATION;

			}
		}
		if(abilities.getActiveWeapon().getCooldown() < abilities.getActiveWeapon().getMAX_COOLDOWN() * 0.8) {
			entity.getComponent(me.reckter.game.Entity.Component.Movement.class).acceleration = me.reckter.game.Entity.Component.Movement.STANDART_ACCELERATION;
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
