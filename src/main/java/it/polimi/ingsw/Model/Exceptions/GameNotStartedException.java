package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when attempting to perform an operation on a game that has not started yet.
 */
public class GameNotStartedException extends Exception {
    /**
     * Constructs a new GameNotStartedException object.
     */
    public GameNotStartedException() {
        super();
    }

    /**
     * Constructs a new GameNotStartedException object with the specified message.
     *
     * @param message the message to print
     */
    public GameNotStartedException(String message) {
        super(message);
    }
}
