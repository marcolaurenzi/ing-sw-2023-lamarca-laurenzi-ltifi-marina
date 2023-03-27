package it.polimi.ingsw.Model.PlayerStates;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;

public abstract class PlayerState {

    /**
     *
     * @param tilesSelection selection to verify
     *
     * @return return true if and only if the selection is full AKA all the 3 elements are not null
     */
    protected Boolean selectionIsFull(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;
        for(int i = 0; i < 3; i++)
            if(tilesSelection.get(i) == null)
                ret = false;

        return ret;
    }

    /**
     *
     * @param tilesSelection tilesSelection to check
     *
     * @return true if and only if the tiles selected are all on the same column AKA all have the same y
     */
    private Boolean areAllSameColumnAndAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 0; i < 3; i++) {
            if(tilesSelection.get(i) == null)
                break;
            else if (tilesSelection.get(i).getY() != y || tilesSelection.get(i).getX() != x + i) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     *
     * @param tilesSelection
     *
     * @return
     */
    private Boolean areAllSameRowAndAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 1; i < 3; i++) {
            if(tilesSelection.get(i) == null)
                break;
            else if (tilesSelection.get(i).getX() != x || tilesSelection.get(i).getY() != y + i ) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     *
     * @param tilesSelection
     * @param board
     *
     * @return
     */
    private Boolean haveAllOneSidesFree(ArrayList<Coordinates> tilesSelection, Board board) {
        Boolean ret = true;

        for(int i = 0; i < 3; i++) {
            if(tilesSelection.get(i) == null)
                break;
            else if (board.hasFree(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()) == 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     *
     * @param tilesSelection
     * @param board
     *
     * @return
     */
    protected Boolean isSelectionValid(ArrayList<Coordinates> tilesSelection, Board board) {
        return (areAllSameColumnAndAdjacents(tilesSelection) || areAllSameRowAndAdjacents(tilesSelection)) && haveAllOneSidesFree(tilesSelection, board);
    }

    /**
     *
     * @param pickLen
     * @param column
     * @param bookShelf
     *
     * @return
     */
    protected Boolean isColumnValid(int pickLen, int column, BookShelf bookShelf) {
        int freeTiles = 0;
        for(int i = 0; i < bookShelf.getColumnDimension(); i++) {
            if(bookShelf.get(i, column) != null)
                break;
            else
                freeTiles++;
        }

        return pickLen <= freeTiles;
    }

    /**
     *
     * @param tilesSelection
     * @param board
     * @param i
     * @param j
     *
     * @throws SelectionIsFullException
     * @throws PlayerIsWaitingException
     */
    public abstract void select(ArrayList<Coordinates> tilesSelection, Board board, int i, int j) throws SelectionIsFullException, PlayerIsWaitingException;

    /**
     *
     * @param tilesSelection
     * @param board
     *
     * @return
     * @throws PlayerIsWaitingException
     * @throws SelectionIsEmptyException
     * @throws SelectionNotValidException
     */
    public abstract ArrayList<Item> pick(ArrayList<Coordinates> tilesSelection, Board board) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException;

    /**
     *
     * @param pickedItems
     * @param bookShelf
     * @param column
     * @param order
     *
     * @throws ColumnNotValidException
     * @throws PlayerIsWaitingException
     */
    public abstract void insertPickInBookShelf(ArrayList<Item> pickedItems, BookShelf bookShelf, int column, int[] order) throws ColumnNotValidException, PlayerIsWaitingException;
}
