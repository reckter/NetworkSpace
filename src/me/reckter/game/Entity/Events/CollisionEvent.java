package me.reckter.game.Entity.Events;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class CollisionEvent<T extends BaseEntity> extends BaseEvent<T> {


	public CollisionEvent(T victim, BaseEntity offender) {
		super(victim, offender);
	}

}
