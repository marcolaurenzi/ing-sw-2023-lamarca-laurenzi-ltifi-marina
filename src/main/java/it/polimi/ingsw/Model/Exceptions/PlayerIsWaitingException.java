package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when a player is in a waiting state.
 */
public class PlayerIsWaitingException extends Exception {
    /**
     * Constructs a new PlayerIsWaitingException object.
     */
    public PlayerIsWaitingException() {
        super();
    }

    /**
     * Constructs a new PlayerIsWaitingException object with the specified message.
     *
     * @param message the message to print
     */
    public PlayerIsWaitingException(String message) {
        super(message);
    }
}
