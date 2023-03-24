package it.polimi.ingsw.Model;
import java.util.ArrayList;

/**
 * This class represents the BookSelf of the game, a 6x5 Matrix of int elements.
 */


public class BookShelf {
    private final Matrix<Item> items;
    private int freeTiles;

    public BookShelf() {
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
     *
     * @param column
     * @param pick
     */
    public void Insert(int column, ArrayList<Item> pick) {
        //TODO
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


    private void set(int i, int j, Item item) {
        items.set(i, j, item);
    }
}
