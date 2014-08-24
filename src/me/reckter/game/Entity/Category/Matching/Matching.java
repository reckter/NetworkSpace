package me.reckter.game.Entity.Category.Matching;

import me.reckter.game.Entity.Category.Matching.Logic.*;
import me.reckter.game.Entity.Component.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Matching {

	static final HashMap<String, Class<? extends BaseComponent>> components = new HashMap<String, Class<? extends BaseComponent>>();

	public Matching(BaseLogic logic) {
		this.logic = logic;
	}

	public Matching(String match) {
		this.logic = parse(match);
	}

	BaseLogic logic;

	public boolean resolve(Collection<BaseComponent> categories) {
        return logic == null || logic.resolve(categories);
    }

	static public BaseLogic AND(BaseLogic a, BaseLogic b) {
		return new AND(a, b);
	}

	static public BaseLogic AND(Class<BaseComponent> a, Class<BaseComponent> b) {
		return new AND(a, b);
	}

	static public BaseLogic AND(BaseLogic a, Class<BaseComponent> b) {
		return new AND(a, MATCH(b));
	}

	static public BaseLogic AND(Class<BaseComponent> a, BaseLogic b) {
		return new AND(MATCH(a), b);
	}

	static public BaseLogic OR(BaseLogic a, BaseLogic b) {
		return new OR(a, b);
	}

	static public BaseLogic OR(Class<BaseComponent> a, Class<BaseComponent> b) {
		return new OR(a, b);
	}

	static public BaseLogic OR(BaseLogic a, Class<BaseComponent> b) {
		return new OR(a, MATCH(b));
	}

	static public BaseLogic OR(Class<BaseComponent> a, BaseLogic b) {
		return new OR(MATCH(a), b);
	}

	static public BaseLogic XOR(BaseLogic a, BaseLogic b) {
		return new XOR(a, b);
	}

	static public BaseLogic XOR(Class<BaseComponent> a, Class<BaseComponent> b) {
		return new XOR(a, b);
	}

	static public BaseLogic XOR(BaseLogic a, Class<BaseComponent> b) {
		return new XOR(a, MATCH(b));
	}

	static public BaseLogic XOR(Class<BaseComponent> a, BaseLogic b) {
		return new XOR(MATCH(a), b);
	}

	static public BaseLogic NOT(BaseLogic a) {
		return new NOT(a);
	}

	static public BaseLogic NOT(Class<BaseComponent> a) {
		return new NOT(a);
	}

	static public BaseLogic MATCH(BaseLogic a) {
		return new MATCH(a);
	}

	static public BaseLogic MATCH(Class<? extends BaseComponent> a) {
		return new MATCH(a);
	}

	static public BaseLogic ALL() {
		return new ALL();
	}

	static private void fillComponents() {

		components.put("ability", Abilities.class);
		components.put("bounces", Bounces.class);
		components.put("collision", Collision.class);
		components.put("enemy", Enemy.class);
		components.put("fighter", Fighter.class);
		components.put("gravity", Gravity.class);
		components.put("grenade", Grenade.class);
		components.put("life", Life.class);
		components.put("maximalMovement", MaximalMovement.class);
		components.put("menu", Menu.class);
		components.put("miniSpinner", MiniSpinner.class);
		components.put("movement", Movement.class);
		components.put("player", Player.class);
		components.put("position", Position.class);
		components.put("projectile", Projectile.class);
		components.put("shooter", Shooter.class);
		components.put("spinner", Spinner.class);
		components.put("timeToLife", TimeToLife.class);
		components.put("destroyedOnContact", DestroyedOnContact.class);

	}

	/**
	 * parses a string into a matching expression
	 *
	 * example:
	 * (MOVEMENT&GRAVITY) becomes AND(MOVEMENT,GRAVITY);
	 *
	 * @param str
	 * @return
	 */
	static public BaseLogic parse(String str) {
		str = str.replace(" ", "");
		if (components.isEmpty()) {
			fillComponents();
		}

		if (str.equals("ALL")) {
			return ALL();
		}

		if (!str.contains("(")) {
			String[] temp;
			if (str.contains("&")) {
				temp = str.split("&", 2);
				return AND(parse(temp[0]), parse(temp[1]));
			} else if (str.contains("|")) {
				temp = str.split("\\|", 2);
				return OR(parse(temp[0]), parse(temp[1]));
			} else if (str.contains("^")) {
				temp = str.split("\\^", 2);
				return XOR(parse(temp[0]), parse(temp[1]));
			} else if (str.contains("!")) {
				temp = str.split("!", 2);
				return NOT(parse(temp[1]));
			} else {
				return MATCH(components.get(str));
			}
		} else {
			int bracketsCounter = 0;
			int firstBracket = -1;
			int i = 0;
			for (char c : str.toCharArray()) {
				if (c == '(') {
					if (firstBracket == -1) {
						firstBracket = i;
					}
					bracketsCounter++;
				} else if (c == ')') {
					bracketsCounter--;
					if (bracketsCounter == 0) {
						if (str.length() == i + 1) {
							return parse(str.substring(firstBracket + 1, i));
						} else if (str.charAt(i + 1) == '&') {
							return AND(parse(str.substring(firstBracket + 1, i)), parse(str.substring(i + 2, str.length())));
						} else if (str.charAt(i + 1) == '|') {
							return OR(parse(str.substring(firstBracket + 1, i)), parse(str.substring(i + 2, str.length())));
						} else if (str.charAt(i + 1) == '^') {
							return XOR(parse(str.substring(firstBracket + 1, i)), parse(str.substring(i + 2, str.length())));
						}
					}
				} else if (bracketsCounter == 0) {
					if (str.charAt(i) == '&') {
						return AND(parse(str.substring(0, i)), parse(str.substring(i + 1, str.length())));
					} else if (str.charAt(i) == '|') {
						return OR(parse(str.substring(0, i)), parse(str.substring(i + 1, str.length())));
					} else if (str.charAt(i) == '^') {
						return XOR(parse(str.substring(0, i)), parse(str.substring(i + 1, str.length())));
					} else if (str.charAt(i) == '!') {
						return NOT(parse(str.substring(i + 1, str.length())));
					}
				}
				i++;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return logic.toString();
	}
}
