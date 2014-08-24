package me.reckter.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import me.reckter.network.packages.Cooldown;
import me.reckter.network.packages.EntityDied;
import me.reckter.network.packages.EntityUpdate;
import me.reckter.network.packages.Input;

/**
 * Created by hannes on 11/08/14.
 */
public class Network {

    static public final int portTCP = 54555;
    static public final int portUDP = 54556;


    public static void registerClasses(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(EntityUpdate.class);
        kryo.register(EntityDied.class);
        kryo.register(EntityUpdate[].class);
        kryo.register(SyncFrameCount.class);
        kryo.register(FrameCount.class);
        kryo.register(EntitiesAlive.class);
        kryo.register(Integer[].class);
        kryo.register(BoardUpdate.class);
        kryo.register(Cooldown.class);
        kryo.register(Input.class);
    }


    static public class BoardUpdate {
        public EntityUpdate[] entities;
    }



    static public class EntitiesAlive {
        public Integer[] entities;
    }

    static public class SyncFrameCount {

        public SyncFrameCount() {
        }

        public SyncFrameCount(int frameIndex) {
            this.frameIndex = frameIndex;
        }

        int frameIndex;
    }

    static public class FrameCount {

        public FrameCount() {
        }

        public FrameCount(int frameIndex) {
            this.frameIndex = frameIndex;
        }

        int frameIndex;
    }
}
