package it.polimi.ingsw.Model;

public class CoordinatesTest {
    public CoordinatesTest() {

    }

    Coordinates coordinates = new Coordinates(1, 2);
    @org.junit.Test
    public void getX() {
        assert (coordinates.getX() == 1);
    }

    @org.junit.Test
    public void getY() {
        assert (coordinates.getY() == 2);
    }

    @org.junit.Test
    public void equals() {
        assert (coordinates.equals(new Coordinates(1, 2)));
        assert (!coordinates.equals(new Coordinates(1, 3)));
        assert (!coordinates.equals(new Coordinates(2, 2)));
        assert (!coordinates.equals(new Coordinates(2, 3)));
    }
}
