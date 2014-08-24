package me.reckter.game.Entity.Component;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Life extends BaseComponent {
	public float maxLife;
	public float lifePercentage;
	public int immuneToDamge;


	public Life(float maxLife, float lifePercentage) {
		this.maxLife = maxLife;
		this.lifePercentage = lifePercentage;
		this.immuneToDamge = 0;
	}

	public Life(float maxLife) {
		this(100,1);
	}

	/**
	 * returns the current maxLife
	 * @return the maxLife
	 */
	public float getLife() {
		return maxLife * lifePercentage;
	}

	/**
	 * this should be used to heal ot deal damage, so the percentage can be changed accordingly
	 */
	public void takeDamage(float dmg) {
		float newLife = getLife() - dmg;
		lifePercentage = newLife / maxLife;
	}
}
