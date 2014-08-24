package me.reckter.client.hud;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by hannes on 22/08/14.
 */
public class HudHandler {
    ArrayList<BaseInterface> interfaces;

    public HudHandler() {
        interfaces = new ArrayList<>();
    }

    public void update(int delta) {
        interfaces.forEach(a -> a.update(delta));
    }

    public void render(Graphics g) {
        interfaces.forEach(a -> a.render(g));
    }

    public void add(BaseInterface anInterface) {
        interfaces.add(anInterface);
    }
}
