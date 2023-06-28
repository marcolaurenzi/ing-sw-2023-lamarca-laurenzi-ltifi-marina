package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConnectionExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        ConnectionException exception = new ConnectionException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "An error occurred while connecting to the server.";

        // Act
        ConnectionException exception = new ConnectionException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}