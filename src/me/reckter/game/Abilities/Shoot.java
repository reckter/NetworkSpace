package me.reckter.game.Abilities;


import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Projectile.BaseProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 28.12.13.
 */
public class Shoot extends BaseAbility {
	public Shoot(BaseEntity caster) {
		super(caster);
	}

	@Override
	public void init() {
		super.init();

		MAX_COOLDOWN = 3 * 1000;
		MAX_TTL = 0;
	}

	@Override
	public boolean cast() {
		if (super.cast()) {
			resolve();
			//soundEngine.playSoundAt("pew", caster.getX(), caster.getY(), 0.1f, 0.1f);
			return true;
		}
		return false;
	}

	@Override
	public void resolve() {
		Position position = caster.getComponent(Position.class);

		Vector2f tmp = position.coords.copy().add(new Vector2f(position.angle).scale((float) position.size.y + 2));
		BaseProjectile projectile = new BaseProjectile(caster.level, caster, tmp, new Vector2f(position.angle), 300);

		projectile.getComponent(Projectile.class).dmg = dmg;


		caster.level.add(projectile);

        /*
		BaseParticle particle;
		for(int i = 0; i < 20; i++) {
			particle = new ShootParticle(caster.level, projectile);
			particle.init();
			caster.level.add(particle);
		}
		*/

	}

	@Override
	public void logic(int delta) {
		cooldown -= delta;
	}
}
