package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameAlreadyCreatedExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        GameAlreadyCreatedException exception = new GameAlreadyCreatedException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "A game has already been created.";

        // Act
        GameAlreadyCreatedException exception = new GameAlreadyCreatedException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}