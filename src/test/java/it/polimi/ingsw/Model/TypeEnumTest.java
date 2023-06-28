package it.polimi.ingsw.Model;

public class TypeEnumTest {
    public TypeEnumTest() {

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
