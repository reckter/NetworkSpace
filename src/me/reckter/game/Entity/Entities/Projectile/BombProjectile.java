package me.reckter.game.Entity.Entities.Projectile;


import me.reckter.game.Entity.Component.Grenade;
import me.reckter.game.Entity.Component.Projectile;
import me.reckter.game.Entity.Component.TimeToLife;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 3/18/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BombProjectile extends GrenadeProjectile {
	public BombProjectile(Level level) {
		super(level);
	}

	public BombProjectile(Level level, BaseEntity origin) {
		super(level, origin);
	}

	public BombProjectile(Level level, BaseEntity origin, Vector2f coord) {
		super(level, origin, coord, new Vector2f(0, 0), 0, coord);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();
        getComponent(Projectile.class).dmg = 70;
        getComponent(Projectile.class).originCanTakeDamage = true;
        Grenade grenade = getComponent(Grenade.class);

        grenade.fuseTime = 1000;//1300;
        grenade.explosionTime = 150;
        grenade.maxExplosionSize = 140;

        getComponent(TimeToLife.class).timeToLife = grenade.explosionTime + grenade.fuseTime;
    }

}
