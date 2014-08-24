package me.reckter.game.Entity.Exception;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class ComponentNotFoundException extends RuntimeException {
	public ComponentNotFoundException() {
	}

	public ComponentNotFoundException(String s) {
		super(s);
	}

	public ComponentNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ComponentNotFoundException(Throwable throwable) {
		super(throwable);
	}
}
