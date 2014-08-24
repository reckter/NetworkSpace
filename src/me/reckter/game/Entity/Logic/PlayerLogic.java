package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.Player;
import me.reckter.game.Entity.Events.AcceleratingEvent;
import me.reckter.game.Entity.Events.Handlers.InputEventHandler;
import me.reckter.game.Entity.Events.Handlers.TickEventHandler;
import me.reckter.game.Entity.Events.InputEvent;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class PlayerLogic extends BaseLogic implements TickEventHandler<Player>, InputEventHandler<Player> {


	public PlayerLogic(Level level) {
		super(new Matching("player & movement"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
    public void onTick(int delta, Player entity) {
        if (((Player) entity).speedGain == 1) {
            fire(new AcceleratingEvent(entity, entity.getComponent(me.reckter.game.Entity.Component.Player.class).MOMENTUM_GAIN));
        }
	}


    @Override
    public void onInput(InputEvent<Player> event) {


        Position position = event.victim.getComponent(Position.class);

        //angle
        Vector2f mouse = new Vector2f(event.input.mouseX - position.coords.x, event.input.mouseY - position.coords.y);

        position.angle = (float) mouse.getTheta();


        if (event.input.accelerating) {
            event.victim.speedGain = 1;
        } else {
            event.victim.speedGain = 0;
        }

        if (event.input.shooting) {
            if (event.victim.getComponent(Abilities.class).castWeapon()) {
                //  testSound.play();
            }
        }
    }

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.LOWEST;
	}

}
