package it.polimi.ingsw.Model;

/**
 * Matrix is the simplest possible implementation for a matrix of T type elements
 * @param <T> the generic element of the matrix
 */
public class Matrix<T> {
    private final T[][] matrix;
    private final int columnDimension;
    private final int rowDimension;

    /**
     * This constructor builds a Matrix with numberOfRows rows and numberOfColumns columns
     * @param numberOfRows number of rows the matrix will have
     * @param numberOfColumns number of columns the matrix will have
     */
    public Matrix(int numberOfRows, int numberOfColumns) {
        matrix = (T[][]) new Object[numberOfRows][numberOfColumns];
        columnDimension = numberOfRows;
        rowDimension = numberOfColumns;
    }

    /**
     * This constructor builds a Matrix with numberOfRows rows and numberOfColumns columns and sets
     * all the values to value
     * @param numberOfRows number of rows the matrix will have
     * @param numberOfColumns number of columns the matrix will have
     * @param value the value the matrix gets initialized with
     */
    public Matrix(int numberOfRows, int numberOfColumns, T value) {
        matrix = (T[][]) new Object[numberOfRows][numberOfColumns];
        columnDimension = numberOfRows;
        rowDimension = numberOfColumns;
        for(int i = 0; i < this.getColumnDimension(); i++) {
            for(int j = 0; j < this.getRowDimension(); j++) {
                this.set(i, j, value);
            }
        }
    }

    /**
     * This method returns an int representing the number of columns the matrix has
     * @return the number of columns the matrix has
     */
    public int getColumnDimension() {
        return columnDimension;
    }

    /**
     * This method returns an int representing the number of rows the matrix has
     * @return the number of rows the matrix has
     */
    public int getRowDimension() {
        return rowDimension;
    }

    /**
     * This is a simple getter method that returns the value in (i, j) position
     * @param i row index
     * @param j column index
     *
     * @return thr value in (i, j) position
     */
    public T get(int i, int j) {
        return matrix[i][j];
    }

    /**
     * This is a simple setter method that sets the (i, j) position to the value elem
     * @param row row index
     * @param col column index
     *
     * @param elem value the (i, j) position is set to
     */
    public void set(int row, int col, T elem) {
        matrix[row][col] = elem;
    }

}
