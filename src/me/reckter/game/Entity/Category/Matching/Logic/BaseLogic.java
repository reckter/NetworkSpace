package me.reckter.game.Entity.Category.Matching.Logic;

import me.reckter.game.Entity.Component.BaseComponent;

import java.util.Collection;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public abstract class BaseLogic {

	protected boolean hasChildren;

	protected BaseLogic(Class<? extends BaseComponent> componentA, Class<? extends BaseComponent> componentB) {
		this.componentA = componentA;
		this.componentB = componentB;
		hasChildren = false;
	}

	protected BaseLogic() {
		hasChildren = true;
	}

	protected BaseLogic(BaseLogic logicA, BaseLogic logicB) {
		this.logicA = logicA;
		this.logicB = logicB;
		hasChildren = true;
	}

	protected Class<? extends BaseComponent> componentA;
	protected Class<? extends BaseComponent> componentB;

	protected BaseLogic logicA;
	protected BaseLogic logicB;


	/**
	 * gets a Set of Components and checks if it matches them
	 *
	 * @param components
	 * @return true if it matches
	 */
	abstract public boolean resolve(Collection<BaseComponent> components);

	/**
	 * returns the name of the operation
	 *
	 * @return the name of this logic statement
	 */
	abstract public String getName();


	/**
	 * checks if the given Collections contains an instance of the given class
	 * @param components
	 * @param componentClass
	 * @return
	 */
	protected boolean contains(Collection<BaseComponent> components, Class<? extends BaseComponent> componentClass) {
		for(BaseComponent component: components) {
			if(component.getClass().equals(componentClass)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns a beatified name
	 * @param component
	 * @return
	 */
	protected String getBeautifulComponentName(Class<? extends BaseComponent> component) {
		String str = component.getCanonicalName();
		String[] tmp = str.split("\\.");
		return tmp[tmp.length - 1];
	}

	public String toString() {
		if (hasChildren) {
			return " (" + logicA.toString() + getName() + logicB.toString() + ") ";
		}
		return " (" + getBeautifulComponentName(componentA) + getName() + getBeautifulComponentName(componentB) + ") ";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof BaseLogic && o.getClass().equals(this.getClass())) {
			if (hasChildren && ((BaseLogic) o).hasChildren) {
				return ((BaseLogic) o).logicA.equals(logicA) && ((BaseLogic) o).logicB.equals(logicB);
			} else if (!hasChildren && !((BaseLogic) o).hasChildren) {
				return ((BaseLogic) o).componentA == componentA && ((BaseLogic) o).componentB == componentB;
			} else {
				return false;
			}
		}
		return false;
	}
}
