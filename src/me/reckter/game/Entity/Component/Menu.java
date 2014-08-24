package me.reckter.game.Entity.Component;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Menu extends BaseComponent {

	public String name;
	public boolean isHighlighted;

	public Menu(String name) {
		this.name = name;
		this.isHighlighted = false;
	}

	public Menu() {
		this("NO NAME");
	}

}
