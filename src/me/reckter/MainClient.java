package me.reckter;

import me.reckter.network.ClientEngine;
import me.reckter.network.ServerEngine;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;

/**
 * Created by hannes on 11/08/14.
 */
public class MainClient {

    static public final int portTCP = 54655;
    static public final int portUDP = 54656;

    public static void main(String[] args) throws SlickException {

        Log.setConsoleLevel(Log.LogLevel.DEBUG);
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "lib/slick/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        Log.debug(System.getProperty("org.lwjgl.librarypath"));

        ClientEngine dsm = new ClientEngine();

        AppGameContainer app = new AppGameContainer(dsm);

        app.setUpdateOnlyWhenVisible(false);
        app.setAlwaysRender(true);
        app.setVSync(false);
        // app.setSmoothDeltas(true);

        //app.setMaximumLogicUpdateInterval(10);

        app.setShowFPS(true);
        app.setTargetFrameRate(ServerEngine.FPS);

        app.setDisplayMode(ClientEngine.SCREEN_WIDTH, ClientEngine.SCREEN_HEIGHT, false);
        app.start();

    }
}
