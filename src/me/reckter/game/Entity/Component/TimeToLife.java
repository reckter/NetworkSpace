package me.reckter.game.Entity.Component;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class TimeToLife extends BaseComponent {
	public int timeToLife;

	public TimeToLife(int timeToLife) {
		this.timeToLife = timeToLife;
	}

	public TimeToLife() {
		this(1000);
	}
}
