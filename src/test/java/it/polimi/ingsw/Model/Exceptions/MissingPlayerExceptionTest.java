package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MissingPlayerExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        MissingPlayerException exception = new MissingPlayerException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "A player is missing.";

        // Act
        MissingPlayerException exception = new MissingPlayerException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}