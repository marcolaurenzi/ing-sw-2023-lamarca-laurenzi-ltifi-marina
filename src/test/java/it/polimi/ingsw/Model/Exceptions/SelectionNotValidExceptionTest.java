package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SelectionNotValidExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        SelectionNotValidException exception = new SelectionNotValidException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The selection is not valid.";

        // Act
        SelectionNotValidException exception = new SelectionNotValidException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}