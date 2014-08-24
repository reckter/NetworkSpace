package me.reckter.game.Entity.Entities.Enemy;

import me.reckter.game.Entity.Component.Collision;
import me.reckter.game.Entity.Component.Enemy;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseEnemy extends BaseEntity {


	public BaseEnemy(Level level) {
		super(level);
	}


    @Override
    protected void fillComponent() {
        super.fillComponent();
        addComponent(new Enemy());
        getComponent(Position.class).size = new Vector2f(13,13);
        Collision collision = getComponent(Collision.class);
        collision.collisionGroups.clear();
        collision.collisionGroups.add("B");
        
    }
}
