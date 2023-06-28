package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerIsWaitingExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        PlayerIsWaitingException exception = new PlayerIsWaitingException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The player is waiting.";

        // Act
        PlayerIsWaitingException exception = new PlayerIsWaitingException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}