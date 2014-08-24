package me.reckter.game.Entity.Entities;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Exception.ComponentNotFoundException;
import me.reckter.game.level.Level;
import me.reckter.network.packages.EntityUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseEntity {
	public boolean isAlive;
	public Level level;

    public int id;
    public byte player;
    public byte type;

	public BaseEntity(Level level) {
		this.level = level;
        components = new HashMap<>();
        isAlive = true;
        level.entityHandler.add(this);
        init();
        type = 0;
	}

	public HashMap<Class<? extends BaseComponent>, BaseComponent> components;


    protected void fillComponent() {
        addComponent(new Position());
        addComponent(new Life(100));
        addComponent(new Movement());
        addComponent(new MaximalMovement());
        addComponent(new Collision("A"));

    }

	public void init() {
        this.fillComponent();

	}



    public EntityUpdate toNetwork() {
        Position position = getComponent(Position.class);
        EntityUpdate ret = new EntityUpdate();
        ret.positionX = position.coords.x;
        ret.positionY = position.coords.y;
        ret.angle = (byte) (position.angle / 360 * 255);
        ret.id = id;
        ret.player = player;
        ret.type = type;
        try {
            Life life = getComponent(Life.class);
            ret.lifePercent = ((Float) (life.lifePercentage * 255 - 128)).byteValue();
            ret.maxLife = life.maxLife;
        } catch (ComponentNotFoundException e) {
            ret.maxLife = -1;
            ret.lifePercent = (byte) 255;
        }
        return ret;
    }

	public boolean matches(Matching matching) {
		return matching.resolve(components.values());
	}


	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * adds a component and checks if the requirements fot the component are met, and if one is missing ads it
	 * @param component the component that will be added
	 */
	public void addComponent(BaseComponent component) {
		for(Class<? extends BaseComponent> compClass: component.requires()) {
			try {
				getComponent(compClass);
			} catch (ComponentNotFoundException e) {
				try {
					addComponent(compClass.newInstance());
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}
		components.put(component.getClass(), component);
	}

	/**
	 * removes a component. respects every component that requires this one and returns all components removed
	 * @param component the component that will be removed
	 * @return the list of components that got remove, because they depend on the given one
	 */
	public ArrayList<BaseComponent> removeComponent(BaseComponent component) {
		LinkedList<BaseComponent> componentsToCheck = new LinkedList<BaseComponent>();
		ArrayList<BaseComponent> ret = new ArrayList<BaseComponent>();

		componentsToCheck.add(component);

		while(!componentsToCheck.isEmpty()) {
			BaseComponent componentCheck = componentsToCheck.poll();
			ret.add(componentCheck);

			ArrayList<BaseComponent> tmp = isRequiredBy(componentCheck.getClass());
			tmp.removeAll(componentsToCheck);

			componentsToCheck.addAll(tmp);

			componentsToCheck.removeAll(ret);
		}
		for(BaseComponent component1: ret) {
			components.remove(component1.getClass());
		}
		return ret;
	}

	    /**
     * returns all components that require the argument
     * @param component the class of the component
     * @return a list with all the components that require the given one
     */
    public ArrayList<BaseComponent> isRequiredBy(Class<?> component) {
        ArrayList<BaseComponent> ret = new ArrayList<BaseComponent>();
        for(BaseComponent componentTmp: components.values()) {
            if(componentTmp.requires().contains(component)) {
                ret.add(componentTmp);
            }
        }
        return ret;
    }

    /**
     * returns the first component that is (instanceof T.class)
     * @param <T> the class
     * @return the fist class that  is found
     */
    public <T extends BaseComponent> T getComponent(Class<T> cls) throws ComponentNotFoundException {
        if(components.containsKey(cls)) {
            return (T) components.get(cls);
        }
        throw new ComponentNotFoundException(cls + " couldn't be found.");
    }

    /**
     * returns all components that are instance of the given parameter
     * @param <T> the class
     * @return an TreeSet of all Components that match the class
     */
    public <T extends BaseComponent>ArrayList<T> getComponents(Class<T> cls) {
        ArrayList<T> ret = new ArrayList<T>();
        for(Class<? extends BaseComponent> componentClass: components.keySet()) {
            if(cls.isAssignableFrom(componentClass)) {
                ret.add((T) components.get(componentClass));
            }
        }
        return ret;
    }
}
