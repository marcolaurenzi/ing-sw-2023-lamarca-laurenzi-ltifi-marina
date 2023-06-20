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
        return !isAnyTileNull(tilesSelection, board) &&
                (areAllSameColumn(tilesSelection) || areAllSameRow(tilesSelection)) &&
                (areAllAdjacents(tilesSelection) || areAllAjdacentsNotOrdered(tilesSelection)) &&
                haveAllOneSidesFree(tilesSelection, board);
    }
    private boolean isAnyTileNull(ArrayList<Coordinates> tilesSelection, Board board) {
        boolean ret = false;

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (board.getGameBoard().get(tilesSelection.get(i).getY(), tilesSelection.get(i).getX()).getPlacedItem() == null) {
                ret = true;
                break;
            }
        }
        return ret;
    }
    private Boolean areAllSameRow(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 1; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getX() != x) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public Boolean areAllAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;
        for(int i = 0; i < tilesSelection.size() - 1; i ++ ) {
            Coordinates curr = tilesSelection.get(i);
            Coordinates next = tilesSelection.get(i + 1);
            if(!(Math.abs(curr.getX() - next.getX()) == 1 && Math.abs(curr.getY() - next.getY()) == 0 || Math.abs(curr.getX() - next.getX()) == 0 && Math.abs(curr.getY() - next.getY()) == 1)) {
                ret = false;
                break;
            }
        }

        return ret;
    }
    public boolean areAllAjdacentsNotOrdered(ArrayList<Coordinates> tilesSelection) {
        boolean ret = false;
        if(tilesSelection.size() == 3) {
            Coordinates first = tilesSelection.get(0);
            Coordinates second = tilesSelection.get(1);
            Coordinates third = tilesSelection.get(2);
            //sopra sotto centro
            if(Math.abs(first.getX() - second.getX()) == 2 && Math.abs(first.getY() - second.getY()) == 0) {
                if(Math.abs(first.getX() + second.getX()) / 2 == third.getX() && first.getY() == third.getY())
                    ret = true;
            } else if(Math.abs(first.getX()) - second.getX() == 0 && Math.abs(first.getY() - second.getY()) == 2){
                if(Math.abs(first.getY() + second.getY()) / 2 == third.getY() && first.getX() == third.getX())
                    ret = true;
            } else if(Math.abs(first.getX() - second.getX()) == 1 && Math.abs(first.getY() - second.getY()) == 0) {
                if(Math.abs(third.getX() + second.getX()) / 2 == first.getX() && first.getY() == third.getY())
                    ret = true;
            } else if(Math.abs(first.getX()) - second.getX() == 0 && Math.abs(first.getY() - second.getY()) == 1){
                if(Math.abs(third.getY() + second.getY()) / 2 == first.getY() && first.getX() == third.getX())
                    ret = true;
            }

        }
        return ret;
    }
    private Boolean areAllSameColumn(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        //same column
        for(int i = 0; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getY() != y) {
                ret = false;
                break;
            }
        }


        return ret;
    }
    private Boolean haveAllOneSidesFree(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
        Boolean ret = true;

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (board.hasFree(tilesSelection.get(i).getY(), tilesSelection.get(i).getX()) == 0) {
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

    public abstract int getStateNumber();
}
