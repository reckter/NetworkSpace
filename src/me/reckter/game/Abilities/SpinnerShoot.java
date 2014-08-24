package me.reckter.game.Abilities;


import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Enemy.MiniSpinner;
import me.reckter.game.Entity.Entities.Projectile.BaseProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 05.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class SpinnerShoot extends Shoot {
	public boolean isShootingMiniSpinners;

	public SpinnerShoot(BaseEntity caster, boolean isShootingMiniSpinners) {
		super(caster);
		this.isShootingMiniSpinners = isShootingMiniSpinners;
	}

	public SpinnerShoot(BaseEntity caster) {
		super(caster);
		isShootingMiniSpinners = false;

	}

	@Override
	public void init() {
		super.init();
		dmg = 2;
		MAX_COOLDOWN = 350;
	}

	@Override
	public void resolve() {
		Position position = caster.getComponent(Position.class);
		Movement movement = caster.getComponent(Movement.class);
		for(int i = 0; i < 4; i++) {
			Vector2f tmp = position.coords.copy().add(new Vector2f(position.angle + 90 * i).scale((float) position.size.y * 0.75f));
			BaseEntity projectile;
			if(isShootingMiniSpinners) {
				projectile = new MiniSpinner(caster.level);
				projectile.init();
				projectile.addComponent(new TimeToLife(3000));
				projectile.getComponent(Abilities.class).getActiveWeapon().setCooldown(300);

				projectile.getComponent(Movement.class).movement = movement.movement.copy().add(new Vector2f(position.angle + 90 * i).scale(200));
				projectile.getComponent(Position.class).coords = tmp;

                /*
				BaseParticle particle;
				for(int j = 0; j < 150; j++) {
					particle = new ShootParticle(caster.level, projectile);
					particle.init();
					caster.level.add(particle);
				}
				*/

			} else {
				projectile = new BaseProjectile(caster.level, caster, tmp, new Vector2f(position.angle + 90 * i), 300);

				//projectile.addComponent(new MaximalMovement(200));
				projectile.getComponent(Projectile.class).dmg = dmg;
				projectile.getComponent(Position.class).size = new Vector2f(2,2);

                /*
				BaseParticle particle;
				for(int j = 0; j < 25; j++) {
					particle = new ShootParticle(caster.level, projectile);
					particle.init();
					caster.level.add(particle);
				}
                */
			}

			//caster.level.add(projectile);
		}
	}
}
