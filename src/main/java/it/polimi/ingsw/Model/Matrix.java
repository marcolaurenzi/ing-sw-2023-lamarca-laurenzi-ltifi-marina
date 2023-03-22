package it.polimi.ingsw.Model;

public class Matrix<T> {
    private final T[][] Matrix;
    private final int  ColumnDimension;
    private final int RowDimension;

    public Matrix(int numberOfRows, int numberOfColumns) {
        Matrix = (T[][]) new Object[numberOfRows][numberOfColumns];
        ColumnDimension = numberOfColumns;
        RowDimension = numberOfRows;
    }
    public int getColumnDimension() {
        return ColumnDimension;
    }
    public int getRowDimension() {
        return RowDimension;
    }
    public T get(int i, int j) {
        return Matrix[i][j];
    }
    public void set(int row, int col, T elem) {
        Matrix[row][col] = elem;
    }

}
