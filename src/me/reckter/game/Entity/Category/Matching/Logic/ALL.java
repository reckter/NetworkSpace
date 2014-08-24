package me.reckter.game.Entity.Category.Matching.Logic;

import me.reckter.game.Entity.Component.BaseComponent;

import java.util.Collection;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class ALL extends BaseLogic {


	/**
	 * gets a Set of Components and checks if it matches them
	 *
	 *
	 * @param components
	 * @return true if it matches
	 */
	@Override
	public boolean resolve(Collection<BaseComponent> components) {
		return true;
	}

	/**
	 * returns the name of the operation
	 *
	 * @return the name of this logic statement
	 */
	@Override
	public String getName() {
		return "<ALL>";
	}

	@Override
	public String toString() {
		return getName();
	}
}
