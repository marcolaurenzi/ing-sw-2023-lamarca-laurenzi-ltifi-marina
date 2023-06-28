package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ColumnNotValidExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        ColumnNotValidException exception = new ColumnNotValidException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The selected column is not valid.";

        // Act
        ColumnNotValidException exception = new ColumnNotValidException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}