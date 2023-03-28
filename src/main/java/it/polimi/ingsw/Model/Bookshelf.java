package it.polimi.ingsw.Model;

import java.util.ArrayList;

/**
 * This class represents the BookSelf of the game, a 6x5 Matrix of int elements.
 */
public class Bookshelf {
    private final Matrix<Item> items;
    private int freeTiles;

    public Bookshelf() {
        items = new Matrix<>(6, 5);
        freeTiles = 30;
    }

    /**
     * This method checks whether the Matrix has some free spaces left or it is full.
     *
     * @return true if freeTiles == 0, false otherwise
     */
    public boolean isFull() {
        return freeTiles == 0;
    }

    /**
     * This method checks whether the Matrix is empty of not
     */
    public boolean isEmpty() {return freeTiles == 30; }

    /**
     *
     * Insert the array of items pick in column
     * @param column in which you put pick
     * @param pick the pick you put in column
     */
    public void insert(int column, ArrayList<Item> pick) {
        int base = 0;
        for(int i = this.getColumnDimension() - 1; i >= 0; i--)
            if(this.get(i, column) == null) {
                base = i;
                break;
            }

        for(int i = 0; i < 3; i++) {
            if(pick.get(i) == null)
                break;
            else {
                this.set(base - i, column, pick.get(i));
                freeTiles--;
            }
        }
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    public Item get(int i, int j) {
        return this.items.get(i,j);
    }

    /**
     *
     * @return
     */
    public int getRowDimension() {
        return items.getRowDimension();
    }

    /**
     *
     * @return
     */
    public int getColumnDimension() {
        return items.getColumnDimension();
    }

    //public for debugging reasons
    public void set(int i, int j, Item item) {
        items.set(i, j, item);
    }
}
