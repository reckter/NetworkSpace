package me.reckter.game.Entity.Entities.Projectile;


import me.reckter.game.Entity.Component.*;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Exception.ComponentNotFoundException;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseProjectile extends BaseEntity {


	public BaseProjectile(Level level) {
		super(level);
        type = 10;
	}

	public BaseProjectile(Level level, BaseEntity origin) {
		this(level);
	}

	public BaseProjectile(Level level, BaseEntity origin, Vector2f coord, Vector2f movement, float speed) {
		this(level);
		init();

		try {
			Movement movementComp = getComponent(Movement.class);
			movementComp.movement = movement;
			movementComp.movement = origin.getComponent(Movement.class).movement.copy().add(movement.normalise().scale(speed));
		} catch(ComponentNotFoundException e) {}

		getComponent(Position.class).coords = coord;
		getComponent(Projectile.class).origin = origin;
	}


    @Override
    protected void fillComponent() {
        addComponent(new Position(2));
        addComponent(new Movement());
        addComponent(new Collision("H"));
        addComponent(new Projectile(10));
        addComponent(new TimeToLife(3000));
        addComponent(new DestroyedOnContact());
    }
}
