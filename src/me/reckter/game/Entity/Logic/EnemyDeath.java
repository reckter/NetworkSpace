package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Entities.Player;
import me.reckter.game.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.game.Entity.Events.DeathEvent;
import me.reckter.game.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class EnemyDeath extends BaseLogic implements DeathEventHandler {
	public EnemyDeath(Level level) {
		super(new Matching("enemy"), level);
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
		if (event.offender instanceof BaseProjectile) {
			if (event.offender.getComponent(Projectile.class).origin instanceof Player) {
				//level.getScore().addScore(10);
			}
		}
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
