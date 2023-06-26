package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when the size of the selected tiles is different from the length of the order.
 */
public class TilesSelectionSizeDifferentFromOrderLengthException extends Exception {
    /**
     * Constructs a new TilesSelectionSizeDifferentFromOrderLengthException object.
     */
    public TilesSelectionSizeDifferentFromOrderLengthException() {
        super();
    }

    /**
     * Constructs a new TilesSelectionSizeDifferentFromOrderLengthException object with the specified message.
     *
     * @param message the message to print
     */
    public TilesSelectionSizeDifferentFromOrderLengthException(String message) {
        super(message);
    }
}
