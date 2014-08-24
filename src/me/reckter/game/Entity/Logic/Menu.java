package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Events.DeathEvent;
import me.reckter.game.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Menu extends BaseLogic implements DeathEventHandler {
	public Menu(Level level) {
		super(new Matching("menu"), level);
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
		//((BaseMen) level).invoke(event.victim.getComponent(Menu.class).name);
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGHEST;
	}
}
