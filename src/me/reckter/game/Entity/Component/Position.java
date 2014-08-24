package me.reckter.game.Entity.Component;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Position extends BaseComponent {
	public Vector2f coords;
	public Vector2f size;
	public double angle;

	public Position(Vector2f coords, Vector2f size, double angle) {
		this.coords = coords;
		this.size = size;
		this.angle = angle;
	}

	public Position(Vector2f coords) {
		this(coords, new Vector2f(10,10), 0);
	}

	public Position() {
		this(new Vector2f(0,0));
	}

	public Position(float size) {
		this(new Vector2f(0,0), new Vector2f(size, size), 0);
	}



	public Rectangle getAAHitBox() {
		return new Rectangle(coords.x - size.x, coords.y - size.y, size.x * 2, size.y * 2);
	}

	public Shape getHitBox() {
		return new Rectangle(coords.x - size.x, coords.y - size.y, size.x * 2, size.y * 2);
	}

	public void setHeight(float height) {
		this.size.y = height;
	}
}
