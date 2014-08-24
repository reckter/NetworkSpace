package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Enemy;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.Enemy.Fighter;
import me.reckter.game.Entity.Events.*;
import me.reckter.game.Entity.Events.Handlers.CollisionEventHandler;
import me.reckter.game.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class FighterLogic extends BaseLogic implements TickEventHandler<Fighter>, DeathEventHandler, CollisionEventHandler {

	public FighterLogic(Level level) {
		super(new Matching("position & movement & fighter"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, Fighter entity) {
		Position position = entity.getComponent(Position.class);
		me.reckter.game.Entity.Component.Movement movement = entity.getComponent(me.reckter.game.Entity.Component.Movement.class);
		Position positionEnemy = entity.getComponent(Enemy.class).victim.getComponent(Position.class);

		Vector2f target = positionEnemy.coords.copy().add(position.coords.copy().negate()).normalise().scale(1.1f);
		position.angle = target.add(movement.movement.copy().normalise().negate()).getTheta();

		if ((Math.abs(movement.movement.getTheta() - position.angle) > 20 || movement.movement.length() < 300)) {
			fire(new AcceleratingEvent(entity, movement.acceleration));
		}
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
	/*	BaseEnemy tmp = new Fighter(level);
		tmp.init();
		tmp.getComponent(Position.class).coords = level.getPlayer().getComponent(Position.class).coords.copy().add(new Vector2f(Util.random.nextFloat() * 360).scale((float) Util.random.nextFloat() * 1000 + 1000));
		tmp.getComponent(Enemy.class).victim = level.getPlayer();
		level.add(tmp);
		*/
	}

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param event
	 */
	@Override
	public void onCollsion(CollisionEvent event) {
		level.fire(new DamageEvent(event.offender, event.victim, 10));
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.LOW;
	}

}
