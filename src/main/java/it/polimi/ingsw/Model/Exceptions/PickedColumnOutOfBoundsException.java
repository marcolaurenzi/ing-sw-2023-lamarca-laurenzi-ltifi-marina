package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when a column index is out of bounds in the context of the game board.
 */
public class PickedColumnOutOfBoundsException extends Exception {
    /**
     * Constructs a new PickedColumnOutOfBoundsException object.
     */
    public PickedColumnOutOfBoundsException() {
        super();
    }

    /**
     * Constructs a new PickedColumnOutOfBoundsException object with the specified message.
     *
     * @param message the message to print
     */
    public PickedColumnOutOfBoundsException(String message) {
        super(message);
    }
}
