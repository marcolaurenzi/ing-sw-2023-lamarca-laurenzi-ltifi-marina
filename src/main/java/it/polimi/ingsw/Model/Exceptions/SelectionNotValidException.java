package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when a selection is not valid.
 */
public class SelectionNotValidException extends Exception {
    /**
     * Constructs a new SelectionNotValidException object.
     */
    public SelectionNotValidException() {
        super();
    }

    /**
     * Constructs a new SelectionNotValidException object with the specified message.
     *
     * @param message the message to print
     */
    public SelectionNotValidException(String message) {
        super(message);
    }
}
