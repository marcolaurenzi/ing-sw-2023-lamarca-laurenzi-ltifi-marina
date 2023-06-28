package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TilesSelectionSizeDifferentFromOrderLengthExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        TilesSelectionSizeDifferentFromOrderLengthException exception = new TilesSelectionSizeDifferentFromOrderLengthException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The size of the selected tiles is different from the length of the order.";

        // Act
        TilesSelectionSizeDifferentFromOrderLengthException exception = new TilesSelectionSizeDifferentFromOrderLengthException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}