package me.reckter.game.Entity.Events.Handlers;


import me.reckter.game.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public interface EntityAddDelHandler<T extends BaseEntity> extends EntityAddHandler<T>, EntityDelHandler<T> {

}
