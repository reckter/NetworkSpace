package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.ExplosionEvent;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface ExplosionEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when an entity explodes(and the every tick)
	 *
	 * @param event
	 */
	public void onExplosion(ExplosionEvent<T> event);
}
