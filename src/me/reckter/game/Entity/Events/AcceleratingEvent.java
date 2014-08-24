package me.reckter.game.Entity.Events;


import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class AcceleratingEvent<T extends BaseEntity> extends BaseEvent<T> {


	public Vector2f direction;
	public float acceleration;


	public AcceleratingEvent(T victim, float acceleration) {
		this(victim, new Vector2f(victim.getComponent(Position.class).angle), acceleration);
		this.acceleration = acceleration;
	}


	public AcceleratingEvent(T victim,Vector2f direction, float acceleration) {
		super(victim, victim);
		this.direction = direction;
		this.acceleration = acceleration;
	}

}
