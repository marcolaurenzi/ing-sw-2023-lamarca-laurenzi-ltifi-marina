package it.polimi.ingsw.Model;

public class MatrixTest {

    Matrix<Integer> matrix = new Matrix<>(3, 3, 0);

    @org.junit.Test
    public void getColumnDimension() {
        assert (matrix.getColumnDimension() == 3);
    }
    @org.junit.Test
    public void getRowDimension() {
        assert (matrix.getRowDimension() == 3);
    }
    @org.junit.Test
    public void get() {
        assert (matrix.get(0, 0) == 0);
        assert (matrix.get(1, 1) == 0);
        assert (matrix.get(2, 2) == 0);
    }

}
