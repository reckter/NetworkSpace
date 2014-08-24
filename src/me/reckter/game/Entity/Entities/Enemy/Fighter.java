package me.reckter.game.Entity.Entities.Enemy;


import me.reckter.game.Entity.Component.Life;
import me.reckter.game.level.Level;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Fighter extends BaseEnemy {
	public Fighter(Level level) {
		super(level);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();
        addComponent(new me.reckter.game.Entity.Component.Fighter());
        getComponent(Life.class).maxLife = 50;
    }
}
