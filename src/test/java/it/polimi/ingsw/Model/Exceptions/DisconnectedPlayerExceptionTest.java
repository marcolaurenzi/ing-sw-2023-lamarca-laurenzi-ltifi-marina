package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DisconnectedPlayerExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        DisconnectedPlayerException exception = new DisconnectedPlayerException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "A player has disconnected.";

        // Act
        DisconnectedPlayerException exception = new DisconnectedPlayerException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}
