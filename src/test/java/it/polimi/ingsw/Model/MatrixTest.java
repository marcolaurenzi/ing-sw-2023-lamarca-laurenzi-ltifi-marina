package it.polimi.ingsw.Model;

/**
 * Unit test for the Matrix class.
 */
public class MatrixTest {

    /**
     * The matrix used for testing.
     */
    Matrix<Integer> matrix = new Matrix<>(3, 3, 0);

    /**
     * Tests the getColumnDimension method.
     */
    @org.junit.Test
    public void getColumnDimension() {
        assert (matrix.getColumnDimension() == 3);
    }

    /**
     * Tests the getRowDimention method.
     */
    @org.junit.Test
    public void getRowDimension() {
        assert (matrix.getRowDimension() == 3);
    }

    /**
     * Tests the set method.
     */
    @org.junit.Test
    public void get() {
        assert (matrix.get(0, 0) == 0);
        assert (matrix.get(1, 1) == 0);
        assert (matrix.get(2, 2) == 0);
    }

}
