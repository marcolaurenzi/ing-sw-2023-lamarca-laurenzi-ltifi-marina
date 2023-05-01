package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
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
        for(int i = 0; i < tilesSelection.size(); i++)
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


    /**
     *
     * @param tilesSelection
     * @param board
     *
     * @return
     */
    protected Boolean isSelectionValid(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
        return !isAnyTileNull(tilesSelection, board) && (areAllSameColumnAndAdjacents(tilesSelection) || areAllSameRowAndAdjacents(tilesSelection)) && haveAllOneSidesFree(tilesSelection, board);
    }
    private boolean isAnyTileNull(ArrayList<Coordinates> tilesSelection, Board board) {
        boolean ret = false;

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (board.getGameBoard().get(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()).getPlacedItem() == null) {
                ret = true;
                break;
            }
        }
        return ret;
    }
    private Boolean areAllSameRowAndAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 1; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getX() != x || tilesSelection.get(i).getY() != y + i ) {
                ret = false;
                break;
            }
        }

        return ret;
    }
    private Boolean areAllSameColumnAndAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getY() != y || tilesSelection.get(i).getX() != x + i) {
                ret = false;
                break;
            }
        }

        return ret;
    }
    private Boolean haveAllOneSidesFree(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
        Boolean ret = true;

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (board.hasFree(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()) == 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     *
     * @param pickLen
     * @param column
     * @param bookshelf
     *
     * @return
     */
    protected Boolean isColumnValid(int pickLen, int column, Bookshelf bookshelf) {
        int freeTiles = 0;
        for(int i = 0; i < bookshelf.getColumnDimension(); i++) {
            if(bookshelf.get(i, column) != null)
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
     *
     * @return
     * @throws PlayerIsWaitingException
     * @throws SelectionIsEmptyException
     * @throws SelectionNotValidException
     */
    public abstract void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, Board board, Bookshelf bookshelf, int column, int[] order) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException;

    /**
     *
     * @param pickedItems
     * @param bookshelf
     * @param column
     * @param order
     *
     * @throws ColumnNotValidException
     * @throws PlayerIsWaitingException
     */
    public abstract void insertPickInBookShelf(ArrayList<Item> pickedItems, Bookshelf bookshelf, int column, int[] order) throws ColumnNotValidException, PlayerIsWaitingException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException;
}
