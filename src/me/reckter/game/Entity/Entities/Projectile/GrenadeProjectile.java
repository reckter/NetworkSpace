package me.reckter.game.Entity.Entities.Projectile;


import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public class GrenadeProjectile extends BaseProjectile {

	public Vector2f destination;

	public GrenadeProjectile(Level level) {
		super(level);
	}

	public GrenadeProjectile(Level level, BaseEntity origin) {
		super(level, origin);
	}

	public GrenadeProjectile(Level level, BaseEntity origin, Vector2f coord, Vector2f movement, float speed, Vector2f destination) {
		super(level, origin, coord, movement, speed);
		this.destination = destination;
	}

	/**
	 * checks if the grenade is exploding
	 *
	 * @return
	 */
	public boolean isExploding() {
		return getComponent(TimeToLife.class).timeToLife <= getComponent(Grenade.class).explosionTime;
	}


	public Rectangle getAAHitBox() {
		Position position = getComponent(Position.class);
		return new Rectangle(position.coords.x - position.size.y, position.coords.y - position.size.y, position.size.y * 2, position.size.y * 2);
	}

	public Shape getHitBox() {
		Position position = getComponent(Position.class);
		return new Circle(position.coords.x, position.coords.y, position.size.y);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();


        int explosionTime;
        int fuseTime;

        removeComponent(getComponent(DestroyedOnContact.class));

        explosionTime = 100;

        getComponent(Projectile.class).dmg = 50;


        fuseTime = 300;//(int) (1000 * (coords.copy().add(destination.copy().negate()).length() / movement.length()));
        //TODO fix this again

        addComponent(new Grenade(explosionTime, fuseTime, 70));
        getComponent(TimeToLife.class).timeToLife = explosionTime + fuseTime;
    }

}
