package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WrongMessageClassEnumExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        WrongMessageClassEnumException exception = new WrongMessageClassEnumException();

        // Assert
        assertEquals(null, exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // Arrange
        String message = "Incorrect message class enumeration encountered.";

        // Act
        WrongMessageClassEnumException exception = new WrongMessageClassEnumException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}