package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Life;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.DamageEvent;
import me.reckter.game.Entity.Events.DeathEvent;
import me.reckter.game.Entity.Events.Handlers.DamageEventHandler;
import me.reckter.game.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.Entity.Exception.ComponentNotFoundException;
import me.reckter.game.level.Level;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BasicLogic extends BaseLogic implements DeathEventHandler, DamageEventHandler, TickEventHandler {
	public BasicLogic(Level level) {
		super(new Matching("ALL"), level);
	}

	/**
	 * gets called when the victim gets Damage from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDamage(DamageEvent event) {
		Life life = event.victim.<Life>getComponent(Life.class);
		if(life.immuneToDamge <= 0){
			try {
				life.immuneToDamge = event.offender.getComponent(Projectile.class).immuneToDamage * 10;
			} catch(ComponentNotFoundException e) {
				life.immuneToDamge = 400;
			}
			life.takeDamage(event.dmg);
			//level.add(new DamageText(level, (int) event.dmg, event.victim));
			if(life.getLife() <= 0) {
				fire(new DeathEvent(event.victim, event.offender));
			}
		}
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
		event.victim.isAlive = false;
		for(int i = 0; i < event.victim.getComponent(Position.class).size.y * 50; i++) {
			//level.add(new ExplosionParticle(level, event.victim, 100));
		}
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
        try {
            Life life = entity.getComponent(Life.class);
            life.immuneToDamge -= delta;
        } catch (ComponentNotFoundException ignored) {}
    }

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGHEST;
	}

}
