package me.reckter.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import me.reckter.Log;
import me.reckter.client.Level;
import me.reckter.client.entities.Entity;
import me.reckter.network.packages.Cooldown;
import me.reckter.network.packages.EntityDied;
import me.reckter.network.packages.EntityUpdate;
import org.newdawn.slick.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hannes on 11/08/14.
 */
public class ClientEngine extends BasicGame {

    public static int SCREEN_WIDTH = 1200;
    public static int SCREEN_HEIGHT = 800;

    public static int id;

    Client client;
    Level level;
    Queue<EntityUpdate> queue;
    int frameIndex;

    public ClientEngine() {
        super("SpaceNetwork");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        level = new Level();

        queue = new ConcurrentLinkedQueue<>();
        client = new Client();
        Network.registerClasses(client);

        client.start();

        InetAddress address = client.discoverHost(Network.portUDP, 5000);
        System.out.println(address);

        client.addListener(new Listener.ThreadedListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                Log.info("new Connection: " + connection.getID() + "," + connection.getRemoteAddressTCP());
                id = connection.getID();
            }

            @Override
            public void disconnected(Connection connection) {
                Log.info("lost Connection: " + connection.getID() + "," + connection.getRemoteAddressTCP());
            }

            @Override
            public void received(Connection connection, Object o) {
                if( o instanceof EntityUpdate) {
                    if(queue.size() < 400) {
                        queue.add((EntityUpdate) o);
                    }
                } else if(o instanceof Network.SyncFrameCount) {
                    frameIndex = ((Network.SyncFrameCount) o).frameIndex;
                } else if(o instanceof Network.FrameCount) {
                    if(frameIndex < ((Network.FrameCount) o).frameIndex - 1) {
                        queue.clear();
                        Log.info("can't keep up, flushing the queue (" + frameIndex + "," + ((Network.FrameCount) o).frameIndex + ")");
                    }
                } else if (o instanceof Network.BoardUpdate) {
                    queue.addAll(Arrays.asList(((Network.BoardUpdate) o).entities));
                } else  if(o instanceof Cooldown) {
                    Entity player = level.entityHandler.getPlayer();
                    if (player != null) {
                        player.lifePercent = ((Cooldown) o).healthPercentage;
                    }
                }
            }
        }));
        try {
            client.connect(5000, address, Network.portTCP, Network.portUDP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        Input input = gameContainer.getInput();
        me.reckter.network.packages.Input pack = new me.reckter.network.packages.Input();
        pack.mouseX = input.getMouseX() - level.camX;
        pack.mouseY = input.getMouseY() - level.camY;

        pack.accelerating = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
        pack.shooting = input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);

        client.sendUDP(pack);

        long time = System.currentTimeMillis();
        int itemsBefore = queue.size();
        while(queue.size() > 0) {
            if(System.currentTimeMillis() - time >= ServerEngine.FPS_TIME) {
                return;
            }
            EntityUpdate update = queue.remove();
            if(update instanceof EntityDied) {
                level.entityHandler.entities.remove(update.id);
            } else if(level.entityHandler.entities.containsKey(update.id)) {
                level.entityHandler.entities.get(update.id).updateFromNetwork(update);
            } else {
                level.entityHandler.entities.put(update.id, Entity.fromNetwork(update));
            }
        }
        //Log.info("updated " + (itemsBefore - queue.size()));
        frameIndex++;
        level.update(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        level.render(graphics);
    }
}
