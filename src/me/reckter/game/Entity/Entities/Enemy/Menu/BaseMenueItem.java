package me.reckter.game.Entity.Entities.Enemy.Menu;


import me.reckter.game.Entity.Component.Menu;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseMenueItem extends BaseEnemy {
	public BaseMenueItem(Level level) {
		super(level);
	}

	public BaseMenueItem(Level level, String name, Vector2f coords) {
		super(level);
		getComponent(Menu.class).name = name;
		Position position = getComponent(Position.class);
		position.coords = coords;
		position.size = new Vector2f(130,20);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();
        addComponent(new Menu());
    }
}
