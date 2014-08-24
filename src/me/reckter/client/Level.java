package me.reckter.client;

import me.reckter.client.entities.Entity;
import me.reckter.client.entities.EntityHandler;
import me.reckter.client.hud.HudHandler;
import me.reckter.client.hud.fixed.Debug;
import me.reckter.client.hud.fixed.HealthBar;
import me.reckter.network.ClientEngine;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by hannes on 11/08/14.
 */
public class Level {

    public int camX;
    public int camY;

    public EntityHandler entityHandler;

    public HudHandler hudHandler;

    public Level() {
        entityHandler = new EntityHandler();
        hudHandler = new HudHandler();
        hudHandler.add(new HealthBar(this));
        hudHandler.add(new Debug(this));
    }

    public void update(int delta) {
        //entityHandler.update(delta);
        hudHandler.update(delta);
    }

    public void render(Graphics g) {
        g.setBackground(Color.black);
        Entity player = entityHandler.getPlayer();

        if (player != null) {

            camX = (int) -(player.position.x - ClientEngine.SCREEN_WIDTH  / 2);
            camY = (int) -(player.position.y - ClientEngine.SCREEN_HEIGHT  / 2);
        }

        g.translate(camX, camY);

        entityHandler.render(g);
        hudHandler.render(g);
    }

}
