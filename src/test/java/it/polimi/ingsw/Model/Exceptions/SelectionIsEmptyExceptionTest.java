package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SelectionIsEmptyExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        SelectionIsEmptyException exception = new SelectionIsEmptyException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The selection is empty.";

        // Act
        SelectionIsEmptyException exception = new SelectionIsEmptyException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}