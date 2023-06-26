package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when attempting to perform an action on a finished game.
 */
public class FinishedGameException extends Exception {
    /**
     * Constructs a new FinishedGameException object.
     */
    public FinishedGameException() {
        super();
    }

    /**
     * Constructs a new FinishedGameException object with the specified message.
     *
     * @param message the message to print
     */
    public FinishedGameException(String message) {
        super(message);
    }
}
