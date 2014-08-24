package me.reckter.game.level;

import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.EntityHandler;
import me.reckter.game.Entity.Events.*;
import me.reckter.game.Entity.Logic.*;
import me.reckter.network.ServerEngine;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by hannes on 11/08/14.
 */
public class Level {

    public EntityHandler entityHandler;
    public ServerEngine engine;

    public CollisionChecking collisionChecking;
    public Level(ServerEngine engine) {
        this.engine = engine;
        entityHandler = new EntityHandler(this);
    }

    public void populate() {
        BaseEntity t = new BaseEntity(this);
        t.getComponent(Position.class).coords = new Vector2f(50,50);
    }

    @SuppressWarnings("unchecked")
    public void addLogics() {


        entityHandler.add(new AbilityLogic(this), TickEvent.class);
        entityHandler.add(new BasicLogic(this), TickEvent.class, DeathEvent.class, DamageEvent.class);
        entityHandler.add(new BouncesLogic(this), TickEvent.class);
        entityHandler.add(new DestroyedOnContact(this), CollisionEvent.class);
        entityHandler.add(new EnemyDeath(this), DeathEvent.class);
        entityHandler.add(new FighterLogic(this), TickEvent.class, DeathEvent.class, CollisionEvent.class);
        entityHandler.add(new GrenadeLogic(this), TickEvent.class, ExplosionEvent.class, CollisionEvent.class);
        entityHandler.add(new MaximalMovement(this), TickEvent.class);
        entityHandler.add(new Movement(this), TickEvent.class, AcceleratingEvent.class);
        entityHandler.add(new PlayerLogic(this), TickEvent.class, InputEvent.class);
        entityHandler.add(new ProjectileLogic(this), DeathEvent.class, CollisionEvent.class);
        entityHandler.add(new Shooter(this), TickEvent.class);
        entityHandler.add(new SpinnerLogic(this), TickEvent.class);
        entityHandler.add(new TimeToLife(this), TickEvent.class);
        entityHandler.add(new NetworkLogic(this), DeathEvent.class);

        collisionChecking = new CollisionChecking(this);
        collisionChecking.addCollisionGroup("A");
        collisionChecking.addCollisionGroup("B");
        collisionChecking.addCollisionGroup("H");

        collisionChecking.addCollisionRule("H", "A");
        collisionChecking.addCollisionRule("H", "B");
        collisionChecking.addCollisionRule("A", "B");
        collisionChecking.addCollisionRule("B", "A");

        collisionChecking.dellCollisionRule("B", "B");
        collisionChecking.dellCollisionRule("A", "A");

        entityHandler.add(collisionChecking, TickOnceEvent.class, EntityAddEvent.class, EntityDelEvent.class);
    }

    public void init() {
        populate();
        addLogics();
    }

    public void update(int delta) {
        entityHandler.logic(delta);
    }

    public void render(Graphics g) {
        g.setBackground(Color.black);

        entityHandler.render(g);
    }

    public void add(BaseEntity entity) {
        entityHandler.add(entity);
    }

    public void fire(BaseEvent event) {
        entityHandler.logicHandler.fireEvent(event);
    }
}
