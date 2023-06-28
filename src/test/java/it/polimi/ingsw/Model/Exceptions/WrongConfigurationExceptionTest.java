package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WrongConfigurationExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        WrongConfigurationException exception = new WrongConfigurationException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "The game configuration is incorrect.";

        // Act
        WrongConfigurationException exception = new WrongConfigurationException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}