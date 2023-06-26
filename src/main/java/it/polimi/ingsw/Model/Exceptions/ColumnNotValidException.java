package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when a column is not valid in a certain context.
 */
public class ColumnNotValidException extends Exception {
    /**
     * Constructs a new ColumnNotValidException object.
     */
    public ColumnNotValidException() {
        super();
    }

    /**
     * Constructs a new ColumnNotValidException object with the specified message.
     *
     * @param message the message to print
     */
    public ColumnNotValidException(String message) {
        super(message);
    }
}
