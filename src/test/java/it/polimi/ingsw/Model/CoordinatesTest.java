package it.polimi.ingsw.Model;

/**
 * Unit tests for the Coordinates class.
 */
public class CoordinatesTest {
    /**
     * Class constructor.
     */
    public CoordinatesTest() {

    }
    /**
     * Coordinates instance to test.
     */
    Coordinates coordinates = new Coordinates(1, 2);

    /**
     * Test of getX method.
     */
    @org.junit.Test
    public void getX() {
        assert (coordinates.getX() == 1);
    }

    /**
     * Test of getY method.
     */
    @org.junit.Test
    public void getY() {
        assert (coordinates.getY() == 2);
    }

    /**
     * Test of equals method.
     */
    @org.junit.Test
    public void equals() {
        assert (coordinates.equals(new Coordinates(1, 2)));
        assert (!coordinates.equals(new Coordinates(1, 3)));
        assert (!coordinates.equals(new Coordinates(2, 2)));
        assert (!coordinates.equals(new Coordinates(2, 3)));
    }
}
