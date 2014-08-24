package me.reckter;

import me.reckter.network.ServerEngine;

/**
 * Created by hannes on 11/08/14.
 */
public class MainServer {



    public static void main(String[] args) {
        Log.setConsoleLevel(Log.LogLevel.DEBUG);
        ServerEngine engine = new ServerEngine();
        engine.serverStart();
        engine.mainLoop();
    }

}
