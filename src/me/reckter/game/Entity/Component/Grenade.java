package me.reckter.game.Entity.Component;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Grenade extends BaseComponent {

	public int explosionTime;
	public int fuseTime;

	public float maxExplosionSize;


	/**
	 *
	 * @param explosionTime
	 * @param fuseTime
	 * @param maxExplosionSize
	 */
	public Grenade(int explosionTime, int fuseTime, float maxExplosionSize) {
		this.explosionTime = explosionTime;
		this.fuseTime = fuseTime;
		this.maxExplosionSize = maxExplosionSize;
	}

	/**
	 *
	 * @param explosionTime
	 * @param fuseTime
	 */
	public Grenade(int explosionTime, int fuseTime) {
		this(explosionTime, fuseTime, 300);
	}
}
