package me.reckter.game.Entity.Logic;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.DeathEvent;
import me.reckter.game.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.game.Entity.Events.Priority;
import me.reckter.game.level.Level;
import me.reckter.network.packages.EntityDied;

/**
 * Created by hannes on 15/08/14.
 */
public class NetworkLogic extends BaseLogic implements DeathEventHandler<BaseEntity>{

    public NetworkLogic(Level level) {
        super(new Matching("ALL"), level);
    }

    @Override
    public void onDeath(DeathEvent<BaseEntity> event) {
        level.engine.sendToAllTCP(new EntityDied(event.victim.id));
    }

    @Override
    public Priority getPriority() {
        return Priority.MIDDLE;
    }
}
