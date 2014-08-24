package me.reckter.game.Abilities;

import me.reckter.game.Entity.Component.Enemy;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.game.Entity.Entities.Player;

/**
 * Created by mediacenter on 30.12.13.
 */
public class HomingMissile extends Shoot {
	protected float detectorRange = 15;

	public HomingMissile(BaseEntity caster) {
		super(caster);
	}

	@Override
	public void init() {
		super.init();
		MAX_COOLDOWN = 3 * 1000;
		dmg = 20;
	}

	@Override
	public void resolve() {
		BaseEntity target = caster;
		if (caster instanceof Player) {
            /*
			ArrayList<BaseEntity> entities = caster.level.getEntities();
			Input input = caster.level.getInput();

			Vector2f mouse = new Vector2f(caster.level.getRealX(input.getMouseX()), caster.level.getRealY(input.getMouseY()));
			Vector2f entityVector;
			Vector2f nearestVector;

			for (BaseEntity entity : entities) {
				entityVector = entity.getComponent(Position.class).coords.copy();
				nearestVector = target.getComponent(Position.class).coords.copy();

				if (mouse.distanceSquared(entityVector) <= detectorRange * detectorRange && mouse.distanceSquared(entityVector) < mouse.distanceSquared(nearestVector)) {
					target = entity;
				}
			}
			*/
		}

        if(caster instanceof BaseEnemy){
            target = caster.getComponent(Enemy.class).victim;
        }
/*
		Position position = caster.getComponent(Position.class);
        Vector2f tmp = new Vector2f(position.coords.x,position.coords.y).add(new Vector2f(position.angle).scale( position.size.y + 2));
        BaseProjectile projectile = new HomingMissileProjectile(caster.level, caster, tmp, new Vector2f(position.angle), target);
        projectile.init();
        projectile.setDmg(dmg);

        caster.level.add(projectile);
        */
	}
}
