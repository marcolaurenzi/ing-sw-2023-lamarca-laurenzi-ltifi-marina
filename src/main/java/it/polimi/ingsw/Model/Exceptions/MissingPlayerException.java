package it.polimi.ingsw.Model.Exceptions;

/**
 * An exception that is thrown when a player is missing in the game.
 */
public class MissingPlayerException extends Exception {
    /**
     * Constructs a new MissingPlayerException object.
     */
    public MissingPlayerException() {
        super();
    }

    /**
     * Constructs a new MissingPlayerException object with the specified message.
     *
     * @param message the message to print
     */
    public MissingPlayerException(String message) {
        super(message);
    }
}
