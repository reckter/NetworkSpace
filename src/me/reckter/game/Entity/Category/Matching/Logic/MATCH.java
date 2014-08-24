package me.reckter.game.Entity.Category.Matching.Logic;


import me.reckter.game.Entity.Component.BaseComponent;

import java.util.Collection;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MATCH extends BaseLogic {

	public MATCH(Class<? extends BaseComponent> component) {
		super(component, component);
	}

	public MATCH(BaseLogic logic) {
		super(logic, logic);
	}

	/**
	 * gets a Set of Components and checks if it matches them
	 *
	 *
	 * @param components
	 * @return true if it matches
	 */
	@Override
	public boolean resolve(Collection<BaseComponent> components) {

		if (hasChildren) {
			return logicA.resolve(components);
		}
		return contains(components, componentA);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String toString() {
		if (hasChildren) {
			return " (" + logicA.toString() + ") ";
		}
		return " (" + getBeautifulComponentName(componentA) + ") ";
	}
}
