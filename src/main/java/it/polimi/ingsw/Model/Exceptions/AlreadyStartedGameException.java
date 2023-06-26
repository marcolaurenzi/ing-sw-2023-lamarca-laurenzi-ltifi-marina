package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when attempting to start a game that has already been started.
 */
public class AlreadyStartedGameException extends Exception {
    /**
     * Constructs an AlreadyStartedGameException with the specified detail message.
     *
     */
    public AlreadyStartedGameException() {
        super();
    }
}
