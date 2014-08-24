package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.EntityAddEvent;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public interface EntityAddHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when an entity gets added
	 *
	 * @param event the entity Addition event
	 */
	public void onEntityAdd(EntityAddEvent<T> event);
}
