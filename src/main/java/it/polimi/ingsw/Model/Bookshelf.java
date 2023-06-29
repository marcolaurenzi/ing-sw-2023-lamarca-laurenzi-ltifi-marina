package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.PickDoesntFitColumnException;
import it.polimi.ingsw.Model.Exceptions.PickedColumnOutOfBoundsException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the Bookshelf of the game, a 6x5 Matrix of Item elements.
 */
public class Bookshelf implements Serializable {
    /**
     * Represents the Bookshelf.
     */
    private final Matrix<Item> items;
    /**
     * Represents the number of free tiles in the Bookshelf.
     */
    private int freeTiles;

    /**
     * Constructs a Bookshelf object with default values.
     */
    public Bookshelf() {
        items = new Matrix<>(6, 5);
        freeTiles = 30;
    }

    /**
     * Checks whether the Bookshelf is full or not.
     *
     * @return true if the Bookshelf is full, false otherwise
     */
    public boolean isFull() {
        return freeTiles == 0;
    }

    /**
     * Checks whether the Bookshelf is empty or not.
     *
     * @return true if the Bookshelf is empty, false otherwise
     */
    public boolean isEmpty() {
        return freeTiles == 30;
    }

    /**
     * Checks whether the specified position in the Bookshelf is empty or not.
     *
     * @param i the row index
     * @param j the column index
     * @return true if the position is empty, false otherwise
     */
    public boolean isEmpty(int i, int j) {
        return items.get(i, j) == null;
    }

    /**
     * Inserts the array of items in the specified column of the Bookshelf.
     *
     * @param column the column index
     * @param pick   the array of items to insert
     * @throws PickDoesntFitColumnException      if the pick doesn't fit in the column
     * @throws PickedColumnOutOfBoundsException if the column index is out of bounds
     */
    public void insert(int column, ArrayList<Item> pick) throws PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        if (column < 0 || column > getColumnDimension() - 1)
            throw new PickedColumnOutOfBoundsException();

        // Checking if the pick fits in the column
        int freeTiles = 0;
        for (int i = 0; i < this.getColumnDimension(); i++) {
            if (this.get(i, column) != null)
                break;
            else
                freeTiles++;
        }

        if (pick.size() > freeTiles)
            throw new PickDoesntFitColumnException();
        // End of check

        int base = 0;
        for (int i = this.getColumnDimension() - 1; i >= 0; i--)
            if (this.get(i, column) == null) {
                base = i;
                break;
            }

        for (int i = 0; i < pick.size(); i++)
            this.set(base - i, column, pick.get(i));
    }

    /**
     * Returns the item at the specified position in the Bookshelf.
     *
     * @param i the row index
     * @param j the column index
     * @return the item at the specified position
     */
    public Item get(int i, int j) {
        return this.items.get(i, j);
    }

    /**
     * Returns the number of rows in the Bookshelf.
     *
     * @return the number of rows
     */
    public int getRowDimension() {
        return items.getRowDimension();
    }

    /**
     * Returns the number of columns in the Bookshelf.
     *
     * @return the number of columns
     */
    public int getColumnDimension() {
        return items.getColumnDimension();
    }

    /**
     * Sets the specified item at the given position in the Bookshelf.
     *
     * @param i    the row index
     * @param j    the column index
     * @param item the item to set
     */
    public void set(int i, int j, Item item) {
        items.set(i, j, item);
        freeTiles--;
    }

    /**
     * Returns the number of empty spaces in the specified column of the Bookshelf.
     *
     * @param column the column index
     * @return the number of empty spaces in the column
     */
    public int getEmptySpaces(int column) {
        int emptySpaces = 0;
        for (int j = 0; j < 6; j++) {
            if (get(j, column) == null)
                emptySpaces++;
        }
        return emptySpaces;
    }

    /**
     * Returns the maximum number of empty spaces among all the columns in the Bookshelf.
     *
     * @return the maximum number of empty spaces
     */
    public int getMaxEmptySpaces() {
        int maxEmptySpaces = 0;
        int emptySpaces;
        for (int i = 0; i < 5; i++) {
            emptySpaces = 0;
            for (int j = 0; j < 6; j++) {
                if (get(j, i) == null)
                    emptySpaces++;
            }
            if (emptySpaces > maxEmptySpaces)
                maxEmptySpaces = emptySpaces;
        }
        return maxEmptySpaces;
    }
}
