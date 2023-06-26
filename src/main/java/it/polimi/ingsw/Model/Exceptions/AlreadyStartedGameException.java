package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when attempting to start a game that has already been started.
 */
public class AlreadyStartedGameException extends Exception {
    /**
     * Constructs a new AlreadyStartedGameException object.
     */
    public AlreadyStartedGameException() {
        super();
    }

    /**
     * Constructs a new AlreadyStartedGameException object with the specified message.
     *
     * @param message the message to print
     */
    public AlreadyStartedGameException(String message) {
        super(message);
    }
}
