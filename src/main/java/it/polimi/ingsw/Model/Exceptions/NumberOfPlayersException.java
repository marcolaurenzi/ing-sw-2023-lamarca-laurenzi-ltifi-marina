package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when the number of players is not valid.
 */
public class NumberOfPlayersException extends Exception {
    /**
     * Constructs a new NumberOfPlayersException object.
     */
    public NumberOfPlayersException() {
        super();
    }

    /**
     * Constructs a new NumberOfPlayersException object with the specified message.
     *
     * @param message the message to print
     */
    public NumberOfPlayersException(String message) {
        super(message);
    }
}
