package me.reckter.game.Entity.Exception;

/**
 * Created by mediacenter on 11.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class StoppEventExecutionException extends RuntimeException {

	public boolean needsToBeDisplayed;
	public StoppEventExecutionException() {
		needsToBeDisplayed = false;
	}

	public StoppEventExecutionException(String s) {
		this(s, false);
	}

	public StoppEventExecutionException(String s, boolean needsToBeDisplayed) {
		super(s);
		this.needsToBeDisplayed = needsToBeDisplayed;
	}

	public StoppEventExecutionException(String s, Throwable throwable, boolean needsToBeDisplayed) {
		super(s, throwable);
		this.needsToBeDisplayed = needsToBeDisplayed;
	}

	public StoppEventExecutionException(String s, Throwable throwable) {
		this(s, throwable, false);
	}

	public StoppEventExecutionException(Throwable throwable) {
		super(throwable);
		this.needsToBeDisplayed = false;
	}
}
