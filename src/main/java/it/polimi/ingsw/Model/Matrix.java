package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * Matrix is the simplest possible implementation for a matrix of T type elements.
 *
 * @param <T> the generic element of the matrix
 */
public class Matrix<T> implements Serializable {
    /**
     * Represents a matrix.
     */

    private final T[][] matrix;
    /**
     * Represents the number of rows in the matrix.
     */
    private final int columnDimension;
    /**
     * Represents the number of columns in the matrix.
     */
    private final int rowDimension;

    /**
     * Constructs a Matrix with the specified number of rows and columns.
     *
     * @param numberOfRows    the number of rows the matrix will have
     * @param numberOfColumns the number of columns the matrix will have
     */
    public Matrix(int numberOfRows, int numberOfColumns) {
        matrix = (T[][]) new Object[numberOfRows][numberOfColumns];
        columnDimension = numberOfRows;
        rowDimension = numberOfColumns;
    }

    /**
     * Constructs a Matrix with the specified number of rows and columns, and initializes all values to the specified value.
     *
     * @param numberOfRows    the number of rows the matrix will have
     * @param numberOfColumns the number of columns the matrix will have
     * @param value           the value the matrix gets initialized with
     */
    public Matrix(int numberOfRows, int numberOfColumns, T value) {
        matrix = (T[][]) new Object[numberOfRows][numberOfColumns];
        columnDimension = numberOfRows;
        rowDimension = numberOfColumns;
        for (int i = 0; i < this.getColumnDimension(); i++) {
            for (int j = 0; j < this.getRowDimension(); j++) {
                this.set(i, j, value);
            }
        }
    }

    /**
     * Returns the number of columns in the matrix.
     *
     * @return the number of columns in the matrix
     */
    public int getColumnDimension() {
        return columnDimension;
    }

    /**
     * Returns the number of rows in the matrix.
     *
     * @return the number of rows in the matrix
     */
    public int getRowDimension() {
        return rowDimension;
    }

    /**
     * Returns the value at the specified position (i, j) in the matrix.
     *
     * @param i the row index
     * @param j the column index
     * @return the value at the specified position (i, j)
     */
    public T get(int i, int j) {
        return matrix[i][j];
    }

    /**
     * Sets the value at the specified position (row, col) in the matrix to the specified element.
     *
     * @param row  the row index
     * @param col  the column index
     * @param elem the value the position (row, col) is set to
     */
    public void set(int row, int col, T elem) {
        matrix[row][col] = elem;
    }

    /**
     * Sets all the values of the matrix to the specified element. This method is used to reset the matrix during testing.
     *
     * @param elem the value the matrix gets initialized with
     */
    public void setAll(T elem) {
        for (int i = 0; i < this.getColumnDimension(); i++) {
            for (int j = 0; j < this.getRowDimension(); j++) {
                this.set(i, j, elem);
            }
        }
    }
}
