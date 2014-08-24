package me.reckter.game.Entity.Events;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public abstract class BaseEvent<T extends BaseEntity> {

	public T victim;
	public BaseEntity offender;
	public int delta;

	public BaseEvent(T victim, BaseEntity offender) {
		this.victim = victim;
		this.offender = offender;
	}

	@Override
	public String toString() {
		String[] tmp = getClass().getCanonicalName().split("\\.");
		return tmp[tmp.length - 1];
	}
}
