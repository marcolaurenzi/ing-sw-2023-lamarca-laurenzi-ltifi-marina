package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.PickDoesntFitColumnException;
import it.polimi.ingsw.Model.Exceptions.PickedColumnOutOfBoundsException;

import java.util.ArrayList;

/**
 * This class represents the BookSelf of the game, a 6x5 Matrix of int elements.
 */
public class Bookshelf {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */
    private final Matrix<Item> items;
    private int freeTiles;

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */


    public Bookshelf() {
        items = new Matrix<>(6, 5);
        freeTiles = 30;
    }

    /* ************************************************************************************************************
     *                          END OF CONSTRUCTORS
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */


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
     * Insert the array of items picks in column, the first element of the array is the first inserted
     * @param column in which you put pick
     * @param pick the pick you put in column
     */
    public void insert(int column, ArrayList<Item> pick) throws PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        if(column < 0 || column > getColumnDimension() - 1)
            throw new PickedColumnOutOfBoundsException();

        //checking if the pick fits in the column
        int freeTiles = 0;
        for(int i = 0; i < this.getColumnDimension(); i++) {
            if(this.get(i, column) != null)
                break;
            else
                freeTiles++;
        }

        if(pick.size() > freeTiles)
            throw new PickDoesntFitColumnException();
        //end of check

        int base = 0;
        for(int i = this.getColumnDimension() - 1; i >= 0; i--)
            if(this.get(i, column) == null) {
                base = i;
                break;
            }

        for(int i = 0; i < pick.size(); i++)
            this.set(base - i, column, pick.get(i));
    }

    /**
     * This method returns the specified Item
     * @param i row index
     * @param j column index
     * @return the specified Item
     */
    public Item get(int i, int j) {
        return this.items.get(i,j);
    }

    /**
     *
     * @return dimension of the row
     */
    public int getRowDimension() {
        return items.getRowDimension();
    }

    /**
     * @return dimension of the column
     */
    public int getColumnDimension() {
        return items.getColumnDimension();
    }

    //public for debugging reasons
    public void set(int i, int j, Item item) {
        items.set(i, j, item);
        freeTiles--;
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     ************************************************************************************************************ */

}
