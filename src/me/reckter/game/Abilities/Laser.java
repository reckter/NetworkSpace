package me.reckter.game.Abilities;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 28.12.13.
 */
public class Laser extends BaseAbility {

	//protected BaseProjectile laser;
	protected float length;

	public Laser(BaseEntity caster) {
		super(caster);
		this.length = 0;
	}

	public Laser(BaseEntity caster, float length) {
		super(caster);
		this.length = length;
	}


	@Override
	public void init() {
		super.init();

		MAX_COOLDOWN = 3000;
		MAX_TTL = 1000;
		dmg = 30;
	}

	@Override
	public boolean cast() {
		if (super.cast()) {
	        /*
            laser = new LaserProjectile(caster.getLevel(), caster, caster.getX(), caster.getY(), new Vector2f(caster.getAngle()), length);
            laser.init();
            caster.getLevel().add(laser);

            laser.setDmg(dmg);

            rotationLock = new RotationStun(1000);

            caster.add(rotationLock);
            return true;
            */
		}
		return false;

	}

	@Override
	public void resolve() {
		// caster.level.del(laser);
		// laser.onDeath(laser);
	}

	@Override
	public void cancel() {
		super.cancel();
		resolve();
	}

	@Override
	public void logic(int delta) {
		if (isActive()) {
           /* laser.setX(caster.getX());
            laser.setY(caster.getY());

            laser.setMovement(new Vector2f(caster.getAngle()).scale(laser.getMovement().length()));

        */
		}
		super.logic(delta);
	}
}
