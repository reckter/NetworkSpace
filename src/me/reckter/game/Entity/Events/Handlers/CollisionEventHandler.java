package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.CollisionEvent;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface CollisionEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param event
	 */
	public void onCollsion(CollisionEvent<T> event);
}
