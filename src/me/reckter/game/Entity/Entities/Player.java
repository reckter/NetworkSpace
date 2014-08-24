package me.reckter.game.Entity.Entities;


import me.reckter.game.Abilities.*;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.level.Level;
import org.newdawn.slick.Input;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Player extends BaseEntity {

	public float speedGain;

	public Player(Level level) {
		super(level);
        type = 1;
	}


    @Override
    protected void fillComponent() {
        super.fillComponent();
        AbilityHandler abilities = new AbilityHandler();
        abilities.add("laser", new Laser(this, 200));
        abilities.add("shoot", new Shoot(this));
        abilities.add("grenade", new Grenade(this));
        abilities.add("bomb", new Bomb(this));
        abilities.add("missile", new HomingMissile(this));
        abilities.add("miniGun", new MiniGun(this));


        addComponent(new me.reckter.game.Entity.Component.Player());

        abilities.get("shoot").setMAX_COOLDOWN(500);
        abilities.get("shoot").setDmg(10);

        abilities.get("grenade").setDmg(40);
        abilities.get("grenade").setMAX_COOLDOWN(2 * 1000);

        addComponent(new Abilities(abilities, "miniGun"));
    }

	/**
	 * gets called bevore evercoords.y logic tick so the input can be handled
	 *
	 * @param input
	 */
	public void processInput(Input input) {

		Position position = getComponent(Position.class);
		//angle
		//Vector2f mouse = new Vector2f(level.getRealX(input.getMouseX() - (int) position.coords.x), level.getRealY(input.getMouseY()) - (int) position.coords.y);


		//position.angle = (float) mouse.getTheta();


		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			speedGain = 1;
		} else {
			speedGain = 0;
		}

		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			if (getComponent(Abilities.class).castWeapon()) {
				//  testSound.play();
			}

		}
	}
}
