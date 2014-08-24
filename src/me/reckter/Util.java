package me.reckter;


import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;

import java.util.Random;

/**
 * Created by mediacenter on 10.01.14.
 */
public class Util {

	public static Random random;

	static public String printBooleanArray(boolean[] array) {
		String ret = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				ret += "    " + array[i];
			} else {
				ret += "   " + array[i];
			}
		}
		return ret;
	}


	static public String printByteArray(byte[] array) {
		String ret = "";
		for (int i = 0; i < array.length; i++) {
			ret += makeReadable(array[i]);
		}
		return ret;
	}


	static public String makeReadable(byte data) {
		if (data >= 16 || data < 0) {
			return "  " + Integer.toHexString(((int) (data) << 24) >>> 24);
		}
		return "  0" + Integer.toHexString(((int) (data) << 24) >>> 24);
	}


	static public boolean checkCollision(BaseEntity entityA, BaseEntity entityB) {
		if (entityA.<Position>getComponent(Position.class).getAAHitBox().intersects(entityB.<Position>getComponent(Position.class).getAAHitBox())) {
			return entityA.<Position>getComponent(Position.class).getHitBox().intersects(entityB.<Position>getComponent(Position.class).getHitBox());
		}
		return false;
	}
}
