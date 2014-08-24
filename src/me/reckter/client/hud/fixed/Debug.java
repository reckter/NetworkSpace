package me.reckter.client.hud.fixed;

import me.reckter.client.Level;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;

/**
 * Created by hannes on 22/08/14.
 */
public class Debug extends Fixed {


    int lines;
    public Debug(Level level) {
        super(level);
        width = 0;
        height = 0;
        position = new Vector2f(0,0);
    }

    @Override
    public void update(int delta) {

    }


    private void printLine(Graphics g, String line) {
        g.drawString(line, x + 10, y + 10 + 20 * lines++);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        lines = 0;
        printLine(g, "(" + x + "|" + y + ")");
        printLine(g, "(" + level.camX + "|" + level.camY + ")");
    }
}
