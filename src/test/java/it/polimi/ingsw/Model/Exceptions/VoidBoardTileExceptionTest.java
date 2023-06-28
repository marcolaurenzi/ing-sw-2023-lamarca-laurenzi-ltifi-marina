package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VoidBoardTileExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        VoidBoardTileException exception = new VoidBoardTileException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The board tile is void.";

        // Act
        VoidBoardTileException exception = new VoidBoardTileException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}