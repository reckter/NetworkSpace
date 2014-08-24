package me.reckter.game.Entity.Events;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */


public class TickEvent<T extends BaseEntity> extends BaseEvent<T> {

	public TickEvent(T victim) {
		super(victim, victim);
	}
}
