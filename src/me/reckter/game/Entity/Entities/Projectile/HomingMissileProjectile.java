package me.reckter.game.Entity.Entities.Projectile;


import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 30.4.2014.
 *
 * @author Hannes Güdelhöfer
 */
public class HomingMissileProjectile extends BaseProjectile {


	public HomingMissileProjectile(Level level) {
		super(level);
	}

	public HomingMissileProjectile(Level level, BaseEntity origin) {
		super(level, origin);
	}

	public HomingMissileProjectile(Level level, BaseEntity origin, Vector2f coord, Vector2f movement, float speed) {
		super(level, origin, coord, movement, speed);
	}


    //TODO
}
