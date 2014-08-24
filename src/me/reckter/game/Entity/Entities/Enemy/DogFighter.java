package me.reckter.game.Entity.Entities.Enemy;


import me.reckter.game.Abilities.AbilityHandler;
import me.reckter.game.Abilities.MiniGun;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Shooter;
import me.reckter.game.level.Level;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class DogFighter extends Fighter {
	public DogFighter(Level level) {
		super(level);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();

        AbilityHandler abilityHandler = new AbilityHandler();
        abilityHandler.add("shoot", new MiniGun(this));
        addComponent(new Abilities(abilityHandler, "shoot"));
        addComponent(new Shooter());
    }
}
