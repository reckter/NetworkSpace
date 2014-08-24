package me.reckter.client.hud.fixed;

import me.reckter.client.Level;
import me.reckter.client.entities.Entity;
import me.reckter.network.ClientEngine;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by hannes on 22/08/14.
 */
public class HealthBar extends Fixed {

    float maxLife;
    float lifePercent;

    public HealthBar(Level level) {
        super(level);
        height = 30;
        width = 300;
        position = new Vector2f((ClientEngine.SCREEN_WIDTH - width) / 2, ClientEngine.SCREEN_HEIGHT - height);
    }

    @Override
    public void update(int delta) {
        Entity player = level.entityHandler.getPlayer();
        if(player != null) {
            maxLife = player.maxLife;
            lifePercent = player.lifePercent;
        } else {
            maxLife = 0;
            lifePercent = 1;
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.white);
        g.draw(new Rectangle(x, y, width, height));
        g.setColor(Color.red);
        g.fill(new Rectangle(x, y, width * lifePercent, height));
        g.setColor(Color.white);
        g.drawString("" + (maxLife * lifePercent), x + width / 2 - 15, y + 5);
    }


}
