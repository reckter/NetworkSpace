package me.reckter.game.Entity.Component;

import java.util.ArrayList;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Bounces extends BaseComponent {

	/**
	 * gets called with a already initialized ArrayList
	 * should get overriden, if something requires another component
	 *
	 * @param ret the list, which should get filled
	 * @return the list, which contains every Component, that is required
	 */
	@Override
	protected ArrayList<Class<? extends BaseComponent>> requires(ArrayList<Class<? extends BaseComponent>> ret) {
		ret.add(Movement.class);
		return ret;
	}
}
