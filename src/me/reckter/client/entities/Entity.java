package me.reckter.client.entities;

import me.reckter.network.ClientEngine;
import me.reckter.network.packages.EntityUpdate;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by hannes on 11/08/14.
 */
public class Entity {
    public Vector2f position;
    public Vector2f movement;
    public float size;
    public float angle;

    public float maxLife;
    public float lifePercent;
    public byte player;

    public byte type;
    public int id;

    protected Entity(int id) {
        this.id = id;
    }

    public void update(int delta) {
        position.add(movement.copy().scale((float) delta / 1000));
        if(position.x < 0) {
            position.x = 0;
            movement.x = - movement.x;
        } else if(position.x > ClientEngine.SCREEN_WIDTH) {
            position.x = ClientEngine.SCREEN_WIDTH;
            movement.x = - movement.x;
        }

        if(position.y < 0) {
            position.y = 0;
            movement.y = - movement.y;
        } else if(position.y > ClientEngine.SCREEN_HEIGHT) {
            position.y = ClientEngine.SCREEN_HEIGHT;
            movement.y = - movement.y;
        }
    }

    public EntityUpdate toNetwork() {
        EntityUpdate ret = new EntityUpdate();
        ret.angle = (byte) (angle / 360 * 255);
        ret.positionX = position.x;
        ret.positionY = position.y;
        ret.id = id;
        ret.player = player;

        return ret;
    }

    public void updateFromNetwork(EntityUpdate update) {
        this.angle = ((float) ((int) update.angle + 128)) / 255f * 360f;
        this.position = new Vector2f(update.positionX, update.positionY);
        this.player = update.player;
        this.type = update.type;
        this.maxLife = update.maxLife;
        this.lifePercent = (((float) update.lifePercent) + 128) / 255;
    }


    public static Entity fromNetwork(EntityUpdate update) {
        Entity ret = new Entity(update.id);
        ret.angle = ((float) ((int) update.angle + 128)) / 255f * 360f;
        ret.position = new Vector2f(update.positionX, update.positionY);
        ret.player = update.player;
        ret.type = update.type;
        ret.maxLife = update.maxLife;
        ret.lifePercent = update.lifePercent;
        return ret;
    }

    public void render(Graphics g) {
        if(ClientEngine.id == player) {
            g.setColor(Color.green);
        } else if(player == 0) {
            g.setColor(Color.white);
        } else  {
            g.setColor(Color.red);
        }
        if(type == 1) {
            g.draw(new Line(position, position.copy().add(new Vector2f(angle).scale(10))));
            if(player != ClientEngine.id) {
                g.setColor(Color.red);
                g.draw(new Line(position.copy().add(new Vector2f(-10, -15)), position.copy().add(new Vector2f(-10 + 20 * lifePercent, -15))));
            }
        } else if(type == 10) {
            g.fill(new Circle(position.x, position.y, 2));
        } else {
            g.draw(new Circle(position.x, position.y, 10));
        }
    }


}
