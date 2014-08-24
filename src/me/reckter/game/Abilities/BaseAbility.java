package me.reckter.game.Abilities;


import me.reckter.game.Entity.Entities.BaseEntity;
import org.newdawn.slick.Graphics;

/**
 * Created by mediacenter on 28.12.13.
 */
public class BaseAbility {

	protected BaseEntity caster;

	protected int timeToLife;
	protected int MAX_TTL;

	protected float dmg;

	protected int cooldown;
	protected int MAX_COOLDOWN;


	public BaseAbility(BaseEntity caster) {
		this.caster = caster;
	}

	/**
	 * gets called after the ability has been created
	 */
	public void init() {

		timeToLife = 0;
		cooldown = 0;

		MAX_COOLDOWN = 3000;
		MAX_TTL = 1000;
	}

	/**
	 * gets called when the Ability is initialized
	 */
	public boolean cast() {
		if (cooldown <= 0) {
			cooldown = MAX_COOLDOWN;
			timeToLife = MAX_TTL;
			return true;
		}
		return false;
	}

	/**
	 * gets called when the ability resolves
	 */
	public void resolve() {

	}

	/**
	 * cancels the ability
	 */
	public void cancel() {
		timeToLife = 0;
	}

	/**
	 * gets called every logic tick
	 *
	 * @param delta
	 */
	public void logic(int delta) {

		if (cooldown > 0) {
			cooldown -= delta;
		}

		if (isActive()) {
			if (timeToLife > 0) {
				timeToLife -= delta;
			}

			if (timeToLife <= 0) {
				resolve();
			}
		}

	}

	/**
	 * checks if the ability is still alive
	 *
	 * @return
	 */
	public boolean isActive() {
		return timeToLife > 0;
	}


	/**
	 * gets called every render
	 *
	 * @param g
	 */
	public void render(Graphics g) {

	}

	public int getMAX_COOLDOWN() {
		return MAX_COOLDOWN;
	}

	public void setMAX_COOLDOWN(int MAX_COOLDOWN) {
		this.MAX_COOLDOWN = MAX_COOLDOWN;
	}

	public int getMAX_TTL() {
		return MAX_TTL;
	}

	public void setMAX_TTL(int MAX_TTL) {
		this.MAX_TTL = MAX_TTL;
	}

	public float getDmg() {
		return dmg;
	}

	public void setDmg(float dmg) {
		this.dmg = dmg;
	}

	public int getTimeToLife() {
		return timeToLife;
	}

	public void setTimeToLife(int timeToLife) {
		this.timeToLife = timeToLife;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
}
