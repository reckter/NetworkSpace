package me.reckter.game.Entity;


import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.BaseEvent;
import me.reckter.game.Entity.Events.EntityAddEvent;
import me.reckter.game.Entity.Events.EntityDelEvent;
import me.reckter.game.Entity.Logic.BaseLogic;
import me.reckter.game.level.Level;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class EntityHandler {

    public HashMap<Integer, BaseEntity> player;
	public ArrayList<BaseEntity> entities;
	public LogicHandler logicHandler;
    int maxId = 0;

	ArrayList<BaseEntity> entitiesToRemove;
	ArrayList<BaseEntity> entitiesToAdd;

	Level level;

	public EntityHandler(Level level) {
        player = new HashMap<>();
		entities = new ArrayList<>();
		entitiesToRemove = new ArrayList<>();
		entitiesToAdd = new ArrayList<>();
		logicHandler = new LogicHandler(this);
		this.level = level;
	}

	public ArrayList<BaseEntity> getEntitiesWichMatch(Matching matching) {
		return entities.parallelStream()
                .filter(entity -> matching.resolve(entity.components.values()))
                .collect(ArrayList::new, (c1, o) -> c1.add(o), (c1, c2) -> c1.addAll(c2));
	}

	public void updateEntityList() {
		entities.addAll(entitiesToAdd);
		for (BaseEntity entity : entitiesToAdd) {
			logicHandler.fireEvent(new EntityAddEvent(entity));
		}

		for (BaseEntity entity : entitiesToRemove) {
			logicHandler.fireEvent(new EntityDelEvent(entity));
		}

		entities.removeAll(entitiesToRemove);
		if (!entitiesToRemove.isEmpty() || !entitiesToAdd.isEmpty()) {
			logicHandler.updateLogics();
		}
		entitiesToAdd = new ArrayList<>();
		entitiesToRemove = new ArrayList<>();
	}

	public void logic(int delta) {
		updateEntityList();

		logicHandler.logic(delta);


		//removing all dead entities
		for (BaseEntity entity : entities) {
			if (!entity.isAlive) {
				entitiesToRemove.add(entity);
			}
		}

	}

	public void render(Graphics g) {
		/*
		Rectangle screen = new Rectangle(level.getRealX(0), level.getRealY(0), ClientEngine.SCREEN_WIDTH, ClientEngine.SCREEN_HEIGHT);

		for (BaseEntity entity : entities) {
			if (entity.getComponent(Position.class).getAAHitBox().intersects(screen)) {
				entity.render(g);
			}
		}
        */
	}

	public void add(BaseLogic logic, Class<? extends BaseEvent>... events) {
		logicHandler.add(logic, events);
	}

	public void del(BaseLogic logic) {
		logicHandler.del(logic);
	}

	public void add(BaseEntity entity) {
		entitiesToAdd.add(entity);
        entity.id = ++maxId;
	}

    public void setPlayer(BaseEntity entity) {
        player.put((int) entity.player, entity);
    }

	public void del(BaseEntity entity) {
		entity.isAlive = false;
		entitiesToRemove.add(entity);
	}
}
