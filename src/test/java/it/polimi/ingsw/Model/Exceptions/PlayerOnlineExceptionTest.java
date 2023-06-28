package it.polimi.ingsw.Model.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerOnlineExceptionTest {
    @Test
    void constructor_withoutMessage() {
        // Arrange & Act
        PlayerOnlineException exception = new PlayerOnlineException();

        // Assert
        assertNull(exception.getMessage());
    }
}