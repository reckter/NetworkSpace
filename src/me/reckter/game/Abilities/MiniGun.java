package me.reckter.game.Abilities;


import me.reckter.Util;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Projectile.BaseProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 11.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class MiniGun extends Shoot {

	public float disturbance;
	public float heat;
	public int REAL_MAX_COOLDOWN = 50;

	public boolean isOverheated;

	public MiniGun(BaseEntity caster) {
		super(caster);
	}

	@Override
	public void init() {
		super.init();
		MAX_COOLDOWN = 50;
		dmg = 4;
		heat = 0;
	}

	@Override
	public void resolve() {
		Position position = caster.getComponent(Position.class);

		Vector2f tmp = position.coords.copy().add(new Vector2f(position.angle).scale((float) position.size.y + 2));

		double param = 200; // the param of the equation used to calculate the spreading
		double pointingAngle = position.angle;
		double maxDisturbanceAngle =(1 - (param / (disturbance + param))) * 5 + 1;
		if(maxDisturbanceAngle < 0) {
			maxDisturbanceAngle = 0;
		}
		pointingAngle = pointingAngle + (Util.random.nextGaussian() * maxDisturbanceAngle);
		BaseProjectile projectile = new BaseProjectile(caster.level, caster, tmp, new Vector2f(pointingAngle) , 300);

		Projectile projectileComponent = projectile.getComponent(Projectile.class);
		projectileComponent.dmg = dmg;
		projectileComponent.immuneToDamage = 10;
		caster.level.add(projectile);

        /*
		BaseParticle particle;
		for(int i = 0; i < 20; i++) {
			particle = new ShootParticle(caster.level, projectile);
			particle.init();
			caster.level.add(particle);
		}
		*/

		heat += 14;
		disturbance+= 15;
		MAX_COOLDOWN = (int) ((heat / 1000) * REAL_MAX_COOLDOWN + REAL_MAX_COOLDOWN);
		cooldown = MAX_COOLDOWN;
	}

	@Override
	public void logic(int delta) {
		super.logic(delta);
		if(cooldown <= -MAX_COOLDOWN) {
			MAX_COOLDOWN = REAL_MAX_COOLDOWN;
			isOverheated = false;
			disturbance -= (float) delta / 1000 * 2000;
		}


		if(heat > 2000) {
			isOverheated = true;
			heat = 2000;
			MAX_COOLDOWN = 100 * REAL_MAX_COOLDOWN;
			cooldown = MAX_COOLDOWN;
		}


		if(isOverheated) {
			disturbance -= (float) delta / 1000 * 200;
			if(cooldown <= 0) {
				isOverheated = false;
				MAX_COOLDOWN = REAL_MAX_COOLDOWN;
			}
		}


		if(isOverheated || cooldown < -MAX_COOLDOWN) {
			heat /= 1 + (.15 * (float) delta / 1000);
		}

		if(disturbance <= 0){
			disturbance = 0;
		}
	}
}
