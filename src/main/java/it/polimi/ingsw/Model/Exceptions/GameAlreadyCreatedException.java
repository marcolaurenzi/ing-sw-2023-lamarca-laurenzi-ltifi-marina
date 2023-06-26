package it.polimi.ingsw.Model.Exceptions;

/**
 * This exception is thrown when a game has already been created and another attempt to create a game is made.
 */
public class GameAlreadyCreatedException extends Exception {
    /**
     * Constructs a new GameAlreadyCreatedException object.
     */
    public GameAlreadyCreatedException() {
        super();
    }

    /**
     * Constructs a new GameAlreadyCreatedException object with the specified message.
     *
     * @param message the message to print
     */
    public GameAlreadyCreatedException(String message) {
        super(message);
    }
}
