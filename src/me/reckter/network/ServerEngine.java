package me.reckter.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import me.reckter.Log;
import me.reckter.Util;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Life;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Entities.Player;
import me.reckter.game.Entity.Events.InputEvent;
import me.reckter.game.level.Level;
import me.reckter.network.packages.Cooldown;
import me.reckter.network.packages.EntityUpdate;
import me.reckter.network.packages.Input;
import org.newdawn.slick.geom.Vector2f;

import java.io.IOException;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by hannes on 11/08/14.
 */
public class ServerEngine {

    Level level;

    Server server;
    public static final int FPS = 60;
    public static final int FPS_TIME = 1000 / FPS;
    int frameCount = 0;

    Queue<Input> inputQueue;


    public void serverStart() {
        server = new Server() {
            @Override
            protected Connection newConnection() {
                return new PlayerConnection();
            }
        };
        Network.registerClasses(server);
        inputQueue = new ConcurrentLinkedDeque<>();

        Util.random = new Random();

        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                Log.info("client connected: " + connection.getID() + "(" + connection.getRemoteAddressUDP() + ")");
                connection.sendTCP(new Network.SyncFrameCount(frameCount));
                Player player = new Player(level);
                player.player = (byte) connection.getID();
                player.getComponent(Position.class).coords = new Vector2f(Util.random.nextDouble() * 360).scale(50);
                level.entityHandler.setPlayer(player);
                ((PlayerConnection) connection).player = player;
            }

            @Override
            public void received(Connection connection, Object object) {
                if(object instanceof Input) {
                    ((Input) object).id = connection.getID();
                    inputQueue.add((Input) object);
                }
            }

            @Override
            public void disconnected(Connection connection) {
                Log.info("lost connection: " + connection.getID() + "(" + connection.getRemoteAddressUDP() + ")");
            }
        });
        level = new Level(this);
        level.init();
        server.start();
        try {
            server.bind(Network.portTCP, Network.portUDP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainLoop() {
        long time = System.currentTimeMillis();
        long time2, timeToWait;
        int delta = 1;
        while (true) {
            frameCount++;
            time2 = System.currentTimeMillis();
            delta = (int) (time2 - time);
            time = time2;

            update(delta);
            sendUpdates();

            timeToWait = FPS_TIME - (System.currentTimeMillis() - time2);
            if(timeToWait > 0) {
                try {
                    Thread.sleep(timeToWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendToAllTCP(Object obj) {
        server.sendToAllTCP(obj);
    }

    public void sendToAllUDP(Object obj) {
        server.sendToAllUDP(obj);
    }

    public void update(int delta) {
        while(inputQueue.size() > 0) {
            Input input = inputQueue.remove();
            if(level.entityHandler.player.containsKey(input.id)) {
                level.fire(new InputEvent<>(level.entityHandler.player.get((input.id)), input));
            }
        }
        level.update(delta);
    }

    public void sendUpdates() {
        if(frameCount % 60 == 0) {
           // Log.info("entities: " + level.entityHandler.entities.size());
        }
        Network.EntitiesAlive entitiesAlive = new Network.EntitiesAlive();
        entitiesAlive.entities = new Integer[level.entityHandler.entities.size()];
        int i = 0;
        for(BaseEntity entity: level.entityHandler.entities) {
            entitiesAlive.entities[i] = entity.id;
            i++;
        }
        //server.sendToAllUDP(entitiesAlive);


        EntityUpdate[] array = level.entityHandler.entities.parallelStream().filter(BaseEntity::isAlive).map(BaseEntity::toNetwork).toArray(EntityUpdate[]::new);

        EntityUpdate[] arraySend;

        int packSize = 40;
        arraySend = new EntityUpdate[array.length > packSize? packSize : array.length];

        int size = 0;
        for(i = 0; i < array.length; i++) {

            if(size == packSize) {
                Network.BoardUpdate update = new Network.BoardUpdate();
                update.entities = arraySend;
                server.sendToAllTCP(update);
                size = 0;
                arraySend = new EntityUpdate[array.length - i > packSize? packSize : array.length - i];
            }
            arraySend[size] = array[i];
            size++;
        }

        Network.BoardUpdate update = new Network.BoardUpdate();
        update.entities = arraySend;
        server.sendToAllUDP(update);

        for(Connection con: server.getConnections()) {
            Cooldown pack = new Cooldown();
            Player player = ((PlayerConnection) con).player;
            if(player != null) {
                pack.healthPercentage = ((PlayerConnection) con).player.getComponent(Life.class).lifePercentage;
                pack.cooldown = ((PlayerConnection) con).player.getComponent(Abilities.class).getActiveWeapon().getCooldown();
                con.sendUDP(pack);
            }
        }

    }
}
