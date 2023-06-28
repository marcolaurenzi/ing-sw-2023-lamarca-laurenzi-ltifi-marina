package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NumberOfPlayersExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        NumberOfPlayersException exception = new NumberOfPlayersException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The number of players is not valid.";

        // Act
        NumberOfPlayersException exception = new NumberOfPlayersException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}