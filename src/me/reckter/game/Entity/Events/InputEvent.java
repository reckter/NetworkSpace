package me.reckter.game.Entity.Events;

import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.network.packages.Input;

/**
 * Created by hannes on 12/08/14.
 */
public class InputEvent<T extends BaseEntity> extends BaseEvent<T> {

    public Input input;
    public InputEvent(T victim, Input input) {
        super(victim, victim);
        this.input = input;
    }
}
