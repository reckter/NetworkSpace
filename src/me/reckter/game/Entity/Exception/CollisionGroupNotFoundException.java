package me.reckter.game.Entity.Exception;

/**
 * Created by hannes on 12/08/14.
 */
public class CollisionGroupNotFoundException extends RuntimeException {
    public CollisionGroupNotFoundException() {
    }

    public CollisionGroupNotFoundException(String message) {
        super(message);
    }

    public CollisionGroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollisionGroupNotFoundException(Throwable cause) {
        super(cause);
    }

    public CollisionGroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
