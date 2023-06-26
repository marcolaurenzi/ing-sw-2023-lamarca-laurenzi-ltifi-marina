package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when a player is disconnected from the game.
 */
public class DisconnectedPlayerException extends Throwable {
    /**
     * Constructs a new DisconnectedPlayerException object.
     */
    public DisconnectedPlayerException() {
        super();
    }

    /**
     * Constructs a new DisconnectedPlayerException object with the specified message.
     *
     * @param message the message to print
     */
    public DisconnectedPlayerException(String message) {
        super(message);
    }
}
