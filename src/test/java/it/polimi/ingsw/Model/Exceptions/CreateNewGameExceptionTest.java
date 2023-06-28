package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateNewGameExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        CreateNewGameException exception = new CreateNewGameException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "An error occurred while creating a new game.";

        // Act
        CreateNewGameException exception = new CreateNewGameException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}