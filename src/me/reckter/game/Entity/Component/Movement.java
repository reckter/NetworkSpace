package me.reckter.game.Entity.Component;


import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Movement extends BaseComponent {
	public Vector2f movement;
	public float acceleration;
	public static final float STANDART_ACCELERATION = 100;

	public Movement() {
		movement = new Vector2f(0,0);
		acceleration = STANDART_ACCELERATION;
	}

	/**
	 * gets called with a already initialized ArrayList
	 * should get overriden, if something requires another component
	 *
	 * @param ret the list, which should get filled
	 * @return the list, which contains every Component, that is required
	 */
	@Override
	protected ArrayList<Class<? extends BaseComponent>> requires(ArrayList<Class<? extends BaseComponent>> ret) {
		ret.add(Position.class);
		return ret;
	}
}
