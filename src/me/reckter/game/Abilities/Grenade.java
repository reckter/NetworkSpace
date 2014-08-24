package me.reckter.game.Abilities;


import me.reckter.game.Entity.Component.Enemy;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.game.Entity.Entities.Player;
import me.reckter.game.Entity.Entities.Projectile.GrenadeProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 30.12.13.
 */
public class Grenade extends Shoot {

	protected Vector2f destination;

	public Grenade(BaseEntity caster) {
		super(caster);
	}

	@Override
	public void init() {
		super.init();
		MAX_COOLDOWN = 2 * 1000;
		destination = new Vector2f(0, 0);
	}

	@Override
	public void resolve() {

		Position position = caster.getComponent(Position.class);
		GrenadeProjectile grenade;
		if (caster instanceof Player) {
			//Input input = caster.level.getInput();
			//destination = new Vector2f(caster.level.getRealX(input.getMouseX()), caster.level.getRealY(input.getMouseY()));
		} else if (caster instanceof BaseEnemy) {
			destination = ((BaseEnemy) caster).getComponent(Enemy.class).victim.getComponent(Position.class).coords.copy();
		}

		Vector2f tmp = position.coords.copy().add(new Vector2f(position.angle).scale((float) position.size.y + 2));

		grenade = new GrenadeProjectile(caster.level, caster, tmp, new Vector2f(position.angle), 300, destination);

		grenade.getComponent(Projectile.class).dmg = dmg;

		grenade.getComponent(Position.class).size = new Vector2f(2,2);
		//caster.level.add(grenade);

	}
}
