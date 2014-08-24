package me.reckter.client.hud;

import me.reckter.client.Level;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;

/**
 * Created by hannes on 22/08/14.
 */
public abstract class BaseInterface {

    public Level level;
    public int height;
    public int width;
    public Vector2f position;

    public BaseInterface(Level level) {
        this.level = level;
    }


    public abstract void update(int delta);

    public abstract void render(Graphics g);
}
