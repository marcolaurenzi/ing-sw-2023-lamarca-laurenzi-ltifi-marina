package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AlreadyStartedGameExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        AlreadyStartedGameException exception = new AlreadyStartedGameException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The game has already started.";

        // Act
        AlreadyStartedGameException exception = new AlreadyStartedGameException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}