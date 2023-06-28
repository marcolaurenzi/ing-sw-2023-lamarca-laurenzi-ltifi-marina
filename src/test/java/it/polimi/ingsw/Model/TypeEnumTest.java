package it.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeEnumTest {
    public TypeEnumTest() {

    }

    @Test
    public void toString_returnsCorrectStringRepresentation() {
        // Arrange
        TypeEnum type = TypeEnum.CATS;

        // Act & Assert
        assertEquals("CATS", type.toString());
    }

    @org.junit.Test
    public void getType() {
        assert (TypeEnum.CATS.toString().equals("CATS"));
        assert (TypeEnum.BOOKS.toString().equals("BOOKS"));
        assert (TypeEnum.TROPHIES.toString().equals("TROPHIES"));
        assert (TypeEnum.FRAMES.toString().equals("FRAMES"));
        assert (TypeEnum.GAMES.toString().equals("GAMES"));
        assert (TypeEnum.PLANTS.toString().equals("PLANTS"));
    }
}
