package me.reckter.game.Entity.Events.Handlers;

import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Events.InputEvent;

/**
 * Created by hannes on 12/08/14.
 */
public interface InputEventHandler<T extends BaseEntity> extends BaseEventHandler {

    /**
     * gets called when an Input is received
     *
     * @param event
     */
    public void onInput(InputEvent<T> event);
}
