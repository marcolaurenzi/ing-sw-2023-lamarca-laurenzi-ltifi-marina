package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PickedColumnOutOfBoundsExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        PickedColumnOutOfBoundsException exception = new PickedColumnOutOfBoundsException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The picked column index is out of bounds.";

        // Act
        PickedColumnOutOfBoundsException exception = new PickedColumnOutOfBoundsException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}