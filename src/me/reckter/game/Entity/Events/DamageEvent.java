package me.reckter.game.Entity.Events;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class DamageEvent<T extends BaseEntity> extends BaseEvent<T> {


	public float dmg;

	public DamageEvent(T victim, BaseEntity offender, float dmg) {
		super(victim, offender);
		this.dmg = dmg;
	}

}
