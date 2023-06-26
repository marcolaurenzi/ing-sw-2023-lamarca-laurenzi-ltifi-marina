package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when a connection error occurs.
 */
public class ConnectionException extends Exception {
    /**
     * Constructs a new ConnectionException object.
     */
    public ConnectionException() {
        super();
    }

    /**
     * Constructs a new ConnectionException object with the specified message.
     *
     * @param message the message to print
     */
    public ConnectionException(String message) {
        super(message);
    }
}
