package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.DamageEvent;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface DamageEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when the victim gets Damage from the offender
	 *
	 * @param event
	 */
	public void onDamage(DamageEvent<T> event);
}
