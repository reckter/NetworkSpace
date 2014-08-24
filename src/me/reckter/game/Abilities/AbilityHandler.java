package me.reckter.game.Abilities;

import java.util.HashMap;

/**
 * Created by mediacenter on 29.12.13.
 */
public class AbilityHandler {

	protected HashMap<String, BaseAbility> abilities;

	public AbilityHandler() {
		this.abilities = new HashMap<String, BaseAbility>();
	}

	/**
	 * casts an ability
	 *
	 * @param abilityName
	 * @return
	 */
	public boolean cast(String abilityName) {
		BaseAbility ability = abilities.get(abilityName);
		if (ability != null) {
			return abilities.get(abilityName).cast();
		}
		return false;
	}

	/**
	 * cancels all active abilities
	 */
	public void cancelAll() {
		for (BaseAbility ability : abilities.values()) {
			if (ability.isActive()) {
				ability.resolve();
				ability.setTimeToLife(0);
			}
		}
	}

	/**
	 * adds an ability
	 *
	 * @param abilityName
	 * @param ability
	 */
	public void add(String abilityName, BaseAbility ability) {
		abilities.put(abilityName, ability);
		ability.init();
	}

	/**
	 * removes the given ability
	 *
	 * @param abilityName
	 */
	public void del(String abilityName) {
		abilities.remove(abilityName);
	}

	/**
	 * removes the given ABility
	 *
	 * @param ability
	 */
	public void del(BaseAbility ability) {
		abilities.remove(ability);
	}

	/**
	 * returns an ability
	 *
	 * @param abilityName
	 * @return
	 */
	public BaseAbility get(String abilityName) {
		return abilities.get(abilityName);
	}

	/**
	 * gets called evry logic tick
	 *
	 * @param delta
	 */
	public void logic(int delta) {
		for (BaseAbility ability : abilities.values()) {
			ability.logic(delta);
		}
	}
}
