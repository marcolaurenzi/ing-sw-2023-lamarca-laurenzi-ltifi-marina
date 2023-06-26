package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when attempting to access a void board tile.
 */
public class VoidBoardTileException extends Exception{
    /**
     * Constructs a new VoidBoardTileException object.
     */
    public VoidBoardTileException() {
        super();
    }

    /**
     * Constructs a new VoidBoardTileException object with the specified message.
     *
     * @param message the message to print
     */
    public VoidBoardTileException(String message) {
        super(message);
    }
}
