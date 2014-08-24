package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Entities.Projectile.GrenadeProjectile;
import me.reckter.game.Entity.Events.*;
import me.reckter.game.Entity.Events.Handlers.CollisionEventHandler;
import me.reckter.game.Entity.Events.Handlers.ExplosionEventHandler;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Exception.ComponentNotFoundException;
import me.reckter.game.level.Level;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public class GrenadeLogic extends BaseLogic implements TickEventHandler<GrenadeProjectile>, ExplosionEventHandler<GrenadeProjectile>, CollisionEventHandler<GrenadeProjectile> {
	public GrenadeLogic(Level level) {
		super(new Matching("grenade"), level);
	}


	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.LOW;
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, GrenadeProjectile entity) {
		if(entity.isExploding()) {
			fire(new ExplosionEvent(entity));
		}
	}

	/**
	 * gets called when an entity explodes(and the every tick)
	 *
	 * @param event
	 */
	@Override
	public void onExplosion(ExplosionEvent<GrenadeProjectile> event) {
		Position position = event.victim.getComponent(Position.class);
        Grenade grenade = event.victim.getComponent(Grenade.class);
        me.reckter.game.Entity.Component.TimeToLife timeToLife = event.victim.getComponent(me.reckter.game.Entity.Component.TimeToLife.class);
		float tmp = ((((float) grenade.explosionTime - event.victim.getComponent(me.reckter.game.Entity.Component.TimeToLife.class).timeToLife) / (float) grenade.explosionTime) * (float) grenade.maxExplosionSize);
		position.size.x = tmp;
		position.size.y = tmp;

		try {
			event.victim.removeComponent(event.victim.getComponent(me.reckter.game.Entity.Component.Movement.class));
			level.entityHandler.logicHandler.logicsNeedUpdate = true;
		} catch(ComponentNotFoundException e) {

		}
        /*
		for (int i = 0; i < (timeToLife.timeToLife * timeToLife.timeToLife) / 100; i++) { //TODO particle number
            Vector2f deviation = new Vector2f(Util.random.nextDouble() * 360).normalise().scale(Util.random.nextFloat() * position.size.y * 0.8f);
			BaseParticle particle = new ExplosionParticle(level, event.victim, grenade.maxExplosionSize, deviation);
			int ttl = (int) Math.abs(Util.random.nextGaussian() * timeToLife.timeToLife * 5);
            level.add(particle);
			particle.setTimeToLive(ttl);
		}
		*/
	}

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param event
	 */
	@Override
	public void onCollsion(CollisionEvent<GrenadeProjectile> event) {
		if(event.victim.isExploding() && event.offender == event.victim.getComponent(Projectile.class).origin) {

            Position posOffender = event.offender.getComponent(Position.class);
            Position posBomb = event.victim.getComponent(Position.class);
            if(posOffender.coords.distanceSquared(posBomb.coords) >= posBomb.size.y * posBomb.size.y * 0.9) {
                level.fire(new AcceleratingEvent<>(event.offender, posOffender.coords.copy().sub(posBomb.coords), 10000));
            }
			level.fire(new DamageEvent(event.offender, event.victim, event.victim.getComponent(Projectile.class).dmg));
		}
		if ((!event.victim.isExploding()) && event.offender != event.victim.getComponent(Projectile.class).origin && !(event.offender instanceof GrenadeProjectile)) {
			event.victim.getComponent(me.reckter.game.Entity.Component.TimeToLife.class).timeToLife = event.victim.getComponent(Grenade.class).explosionTime;
		}
	}
}
