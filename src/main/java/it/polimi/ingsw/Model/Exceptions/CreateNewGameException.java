package it.polimi.ingsw.Model.Exceptions;

/**
 * The CreateNewGameException class represents an exception that is thrown when there is an error creating a new game.
 */
public class CreateNewGameException extends Exception {
    /**
     * Constructs a new CreateNewGameException object.
     */
    public CreateNewGameException() {
        super();
    }

    /**
     * Constructs a new CreateNewGameException object with the specified message.
     *
     * @param message the message to print
     */
    public CreateNewGameException(String message) {
        super(message);
    }
}
