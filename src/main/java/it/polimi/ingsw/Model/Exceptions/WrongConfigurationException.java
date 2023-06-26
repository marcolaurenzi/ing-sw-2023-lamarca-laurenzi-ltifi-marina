package it.polimi.ingsw.Model.Exceptions;

/**
 * Exception thrown when the configuration of the game is incorrect.
 */
public class WrongConfigurationException extends Exception {
    /**
     * Constructs a new WrongConfigurationException object.
     */
    public WrongConfigurationException() {
        super();
    }

    /**
     * Constructs a new WrongConfigurationException object with the specified message.
     *
     * @param message the message to print
     */
    public WrongConfigurationException(String message) {
        super(message);
    }
}
