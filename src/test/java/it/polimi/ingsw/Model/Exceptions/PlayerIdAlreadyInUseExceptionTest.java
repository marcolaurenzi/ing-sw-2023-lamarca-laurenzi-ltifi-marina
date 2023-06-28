package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerIdAlreadyInUseExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        PlayerIdAlreadyInUseException exception = new PlayerIdAlreadyInUseException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The player ID is already in use.";

        // Act
        PlayerIdAlreadyInUseException exception = new PlayerIdAlreadyInUseException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}