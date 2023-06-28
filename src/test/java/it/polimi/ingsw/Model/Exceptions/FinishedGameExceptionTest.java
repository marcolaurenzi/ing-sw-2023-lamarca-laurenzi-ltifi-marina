package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FinishedGameExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        FinishedGameException exception = new FinishedGameException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The game has already finished.";

        // Act
        FinishedGameException exception = new FinishedGameException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}