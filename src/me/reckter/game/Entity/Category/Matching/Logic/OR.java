package me.reckter.game.Entity.Category.Matching.Logic;


import me.reckter.game.Entity.Component.BaseComponent;

import java.util.Collection;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class OR extends BaseLogic {

	public OR(BaseLogic logicA, BaseLogic logicB) {
		super(logicA, logicB);
	}

	public OR(Class<? extends BaseComponent> componentA, Class<? extends BaseComponent> componentB) {
		super(componentA, componentB);
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
			return logicA.resolve(components) || logicB.resolve(components);
		}

		return contains(components, componentA) || contains(components, componentB);
	}


	@Override
	public String getName() {
		return "|";
	}
}
