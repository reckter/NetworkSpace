package me.reckter.game.Abilities;


import me.reckter.game.Entity.Component.Movement;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Projectile.BombProjectile;
import me.reckter.game.Entity.Exception.ComponentNotFoundException;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 30.12.13.
 */
public class Bomb extends Shoot {
	public Bomb(BaseEntity caster) {
		super(caster);
	}

	public void init() {
		super.init();
		MAX_COOLDOWN = 500;//2 * 1000;
		dmg = 5;
	}

	@Override
	public void resolve() {
		BombProjectile bomb;
		Position position = caster.getComponent(Position.class);
		Vector2f tmp = position.coords.copy().add(new Vector2f(position.angle).normalise().scale(position.size.y + 2));

		bomb = new BombProjectile(caster.level, caster, tmp);
		bomb.getComponent(Projectile.class).dmg = (dmg);

		bomb.getComponent(Position.class).size = new Vector2f(5,5);
		try {
			bomb.getComponent(Movement.class).movement = caster.getComponent(Movement.class).movement.copy().add(new Vector2f(position.angle).normalise().scale(100));
		} catch (ComponentNotFoundException e) {

		}
		caster.level.add(bomb);

	}
}
