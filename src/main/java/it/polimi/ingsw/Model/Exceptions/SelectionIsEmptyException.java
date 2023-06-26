package it.polimi.ingsw.Model.Exceptions;

/**
 * An exception that is thrown when a selection is empty.
 */
public class SelectionIsEmptyException extends Exception {
    /**
     * Constructs a new SelectionIsEmptyException object.
     */
    public SelectionIsEmptyException() {
        super();
    }

    /**
     * Constructs a new SelectionIsEmptyException object with the specified message.
     *
     * @param message the message to print
     */
    public SelectionIsEmptyException(String message) {
        super(message);
    }
}
