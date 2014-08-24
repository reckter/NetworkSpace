package me.reckter.game.Entity.Entities.Enemy;


import me.reckter.game.Abilities.AbilityHandler;
import me.reckter.game.Abilities.SpinnerShoot;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Life;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 06.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class MiniSpinner extends Spinner {

	public MiniSpinner(Level level) {
		super(level);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();
        AbilityHandler abilities = getComponent(Abilities.class).abilities;
        abilities.add("shoot", new SpinnerShoot(this));
        abilities.get("shoot").setMAX_COOLDOWN(100);
        abilities.get("shoot").setCooldown(50);
        getComponent(Life.class).maxLife = 15;
        getComponent(Position.class).size = new Vector2f(7,7);
    }
}
