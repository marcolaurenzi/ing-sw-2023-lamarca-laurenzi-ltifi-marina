package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when an incorrect message class enumeration is encountered.
 */
public class WrongMessageClassEnumException extends Exception {
    /**
     * Constructs a new WrongMessageClassEnumException object.
     */
    public WrongMessageClassEnumException() {
        super();
    }

    /**
     * Constructs a new WrongMessageClassEnumException object with the specified message.
     *
     * @param message the message to print
     */
    public WrongMessageClassEnumException(String message) {
        super(message);
    }
}
