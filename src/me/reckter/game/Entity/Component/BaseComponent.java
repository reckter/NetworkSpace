package me.reckter.game.Entity.Component;

import java.util.ArrayList;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseComponent {

	/**
	 * gets called when the Component gets add, so that required components can be added, of the not already
	 * @return a List of components, that need to be added
	 */
	public ArrayList<Class<? extends BaseComponent>> requires(){
		return requires(new ArrayList<>());
	}



	/**
	 * gets called with a already initialized ArrayList
	 * should get overriden, if something requires another component
	 * @param ret the list, which should get filled
	 * @return the list, which contains every Component, that is required
	 */
	protected ArrayList<Class<? extends BaseComponent>> requires(ArrayList<Class<? extends BaseComponent>> ret) {
		return ret;
	}

	@Override
	public String toString() {
        return getClass().getCanonicalName();
	}
}
