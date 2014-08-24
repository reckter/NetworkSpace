package me.reckter.client.hud.fixed;

import me.reckter.client.Level;
import me.reckter.client.hud.BaseInterface;
import org.newdawn.slick.Graphics;

/**
 * Created by hannes on 22/08/14.
 */
public abstract class Fixed extends BaseInterface {

    public int x;
    public int y;

    protected Fixed(Level level) {
        super(level);
    }

    public void render(Graphics g) {
        x = (int) (position.x - level.camX);
        y = (int) (position.y - level.camY);
    }


}
