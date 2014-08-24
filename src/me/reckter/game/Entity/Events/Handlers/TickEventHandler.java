package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface TickEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	public void onTick(int delta, T entity);

}
