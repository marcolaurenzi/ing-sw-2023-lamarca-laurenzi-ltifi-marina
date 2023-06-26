package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when the maximum number of players is exceeded.
 */
public class MaxNumberOfPlayersException extends Exception{
    /**
     * Constructs a new MaxNumberOfPlayersException object.
     */
    public MaxNumberOfPlayersException() {
        super();
    }

    /**
     * Constructs a new MaxNumberOfPlayersException object with the specified message.
     *
     * @param message the message to print
     */
    public MaxNumberOfPlayersException(String message) {
        super(message);
    }
}
