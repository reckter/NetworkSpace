package me.reckter.game.Entity.Component;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Projectile extends BaseComponent {

	public float dmg;
	public int immuneToDamage;
	public BaseEntity origin;
	public boolean originCanTakeDamage;

	public Projectile(float dmg, BaseEntity origin) {
		this.dmg = dmg;
		this.origin = origin;
		this.immuneToDamage = 40;
		this.originCanTakeDamage = false;
	}

	public Projectile(float dmg, int immuneToDamage, BaseEntity origin) {
		this.dmg = dmg;
		this.immuneToDamage = immuneToDamage;
		this.origin = origin;
		this.originCanTakeDamage = false;
	}

	public Projectile(float dmg) {
		this.dmg = dmg;
		this.immuneToDamage = 40;
		this.originCanTakeDamage = false;
	}
}
