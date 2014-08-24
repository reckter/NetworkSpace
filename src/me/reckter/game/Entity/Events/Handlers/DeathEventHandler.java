package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.DeathEvent;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface DeathEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	public void onDeath(DeathEvent<T> event);
}
