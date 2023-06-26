package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when an incorrect password is provided.
 */
public class WrongPasswordException extends Exception {
    /**
     * Constructs a new WrongPasswordException object.
     */
    public WrongPasswordException() {
        super();
    }

    /**
     * Constructs a new WrongPasswordException object with the specified message.
     *
     * @param message the message to print
     */
    public WrongPasswordException(String message) {
        super(message);
    }
}
