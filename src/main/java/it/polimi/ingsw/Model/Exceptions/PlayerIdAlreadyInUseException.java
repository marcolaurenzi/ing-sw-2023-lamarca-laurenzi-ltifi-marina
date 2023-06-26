package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when a player ID is already in use.
 */
public class PlayerIdAlreadyInUseException extends Exception {
    /**
     * Constructs a new PlayerIdAlreadyInUseException object.
     */
    public PlayerIdAlreadyInUseException() {
        super();
    }

    /**
     * Constructs a new PlayerIdAlreadyInUseException object with the specified message.
     *
     * @param message the message to print
     */
    public PlayerIdAlreadyInUseException(String message) {
        super(message);
    }
}
