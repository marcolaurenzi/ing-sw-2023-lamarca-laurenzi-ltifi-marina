package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameNotStartedExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        GameNotStartedException exception = new GameNotStartedException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The game has not started yet.";

        // Act
        GameNotStartedException exception = new GameNotStartedException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}