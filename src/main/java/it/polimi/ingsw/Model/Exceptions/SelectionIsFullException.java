package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when a selection is full.
 */
public class SelectionIsFullException extends Exception {
    /**
     * Constructs a new SelectionIsFullException object.
     */
    public SelectionIsFullException() {
        super();
    }

    /**
     * Constructs a new SelectionIsFullException object with the specified message.
     *
     * @param message the message to print
     */
    public SelectionIsFullException(String message) {
        super(message);
    }
}
