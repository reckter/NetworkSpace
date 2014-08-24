package me.reckter.game.Entity.Component;

/**
 * Created by mediacenter on 11.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class DestroyedOnContact extends BaseComponent {
	public int collisionsTillDeath;

	public DestroyedOnContact(int collisionsTillDeath) {
		this.collisionsTillDeath = collisionsTillDeath;
	}

	public DestroyedOnContact() {
		this(1);
	}

	/**
	 * returns whether the entity should be destroyed or not
	 * @return if the countdown is 0
	 */
	public boolean isDestroyed() {
		return collisionsTillDeath <= 0;
	}

	/**
	 * counts down by one and returns isDestroyed()
	 * @return if the entity should be destroyed or not
	 */
	public boolean onCollision() {
		collisionsTillDeath--;
		return isDestroyed();
	}
}
