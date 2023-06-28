package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MaxNumberOfPlayersExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        MaxNumberOfPlayersException exception = new MaxNumberOfPlayersException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The maximum number of players has been exceeded.";

        // Act
        MaxNumberOfPlayersException exception = new MaxNumberOfPlayersException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}