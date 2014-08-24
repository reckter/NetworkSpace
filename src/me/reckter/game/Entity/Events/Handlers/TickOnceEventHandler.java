package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;

import java.util.ArrayList;

/**
 * Created by mediacenter on 05.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public interface TickOnceEventHandler extends BaseEventHandler {

	/**
	 * gets called every tick, after each entity allready got called, but only gets called once for all entities,
	 * with all entities that matches the gven Matching (requires to be a sublcass of BaseLogic)
	 * @param entities an ArrayList of matching entities
	 * @param delta the delta between the last call and the current one
	 */
	public void onTick(ArrayList<BaseEntity> entities, int delta);
}
