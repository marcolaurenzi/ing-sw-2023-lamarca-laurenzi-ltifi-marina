package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when a picked item doesn't fit in a column.
 */
public class PickDoesntFitColumnException extends Exception {
    /**
     * Constructs a new PickDoesntFitColumnException object.
     */
    public PickDoesntFitColumnException() {
        super();
    }

    /**
     * Constructs a new PickDoesntFitColumnException object with the specified message.
     *
     * @param message the message to print
     */
    public PickDoesntFitColumnException(String message) {
        super(message);
    }
}
