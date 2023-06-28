package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WrongPasswordExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        WrongPasswordException exception = new WrongPasswordException();

        // Assert
        assertEquals(null, exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "Custom message";

        // Act
        WrongPasswordException exception = new WrongPasswordException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}