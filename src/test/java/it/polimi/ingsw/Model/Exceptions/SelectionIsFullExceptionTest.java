package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SelectionIsFullExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        SelectionIsFullException exception = new SelectionIsFullException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The selection is full.";

        // Act
        SelectionIsFullException exception = new SelectionIsFullException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}