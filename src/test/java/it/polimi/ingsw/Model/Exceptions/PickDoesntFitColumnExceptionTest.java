package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PickDoesntFitColumnExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        PickDoesntFitColumnException exception = new PickDoesntFitColumnException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The picked item doesn't fit in the column.";

        // Act
        PickDoesntFitColumnException exception = new PickDoesntFitColumnException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}