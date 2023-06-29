package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;
/**
 *This class represents the state of a player during the game.
 */
public abstract class PlayerState {
    /**
     * Constructs a PlayerState
     */
    public PlayerState() {

    }

    /**
     * Checks if the tiles selected are all on the same column, i.e., all have the same y-coordinate.
     *
     * @param tilesSelection the tilesSelection to check
     * @return true if and only if the tiles selected are all on the same column
     */
    protected Boolean areAllSameColumn(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        // Same column
        for (Coordinates coordinates : tilesSelection) {
            if (coordinates.getY() != y) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * Checks if the tiles selected are all on the same row, i.e., all have the same x-coordinate.
     *
     * @param tilesSelection the tilesSelection to check
     * @return true if and only if the tiles selected are all on the same row
     */
    protected Boolean areAllSameRow(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for (int i = 1; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getX() != x) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * Checks if all the tiles in the selection are adjacent to each other.
     *
     * @param tilesSelection the tilesSelection to check
     * @return true if and only if all the tiles in the selection are adjacent
     */
    public Boolean areAllAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;
        for (int i = 0; i < tilesSelection.size() - 1; i++) {
            Coordinates curr = tilesSelection.get(i);
            Coordinates next = tilesSelection.get(i + 1);
            if (!(Math.abs(curr.getX() - next.getX()) == 1 && Math.abs(curr.getY() - next.getY()) == 0 || Math.abs(curr.getX() - next.getX()) == 0 && Math.abs(curr.getY() - next.getY()) == 1)) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * Checks if all the tiles in the selection are adjacent to each other but not ordered.
     *
     * @param tilesSelection the tilesSelection to check
     * @return true if and only if all the tiles in the selection are adjacent but not ordered
     */
    public boolean areAllAjdacentsNotOrdered(ArrayList<Coordinates> tilesSelection) {
        boolean ret = false;
        if (tilesSelection.size() == 3) {
            Coordinates first = tilesSelection.get(0);
            Coordinates second = tilesSelection.get(1);
            Coordinates third = tilesSelection.get(2);
            // Above, below, center
            if (Math.abs(first.getX() - second.getX()) == 2 && Math.abs(first.getY() - second.getY()) == 0) {
                if (Math.abs(first.getX() + second.getX()) / 2 == third.getX() && first.getY() == third.getY())
                    ret = true;
            } else if (Math.abs(first.getX()) - second.getX() == 0 && Math.abs(first.getY() - second.getY()) == 2) {
                if (Math.abs(first.getY() + second.getY()) / 2 == third.getY() && first.getX() == third.getX())
                    ret = true;
            } else if (Math.abs(first.getX() - second.getX()) == 1 && Math.abs(first.getY() - second.getY()) == 0) {
                if (Math.abs(third.getX() + second.getX()) / 2 == first.getX() && first.getY() == third.getY())
                    ret = true;
            } else if (Math.abs(first.getX()) - second.getX() == 0 && Math.abs(first.getY() - second.getY()) == 1) {
                if (Math.abs(third.getY() + second.getY()) / 2 == first.getY() && first.getX() == third.getX())
                    ret = true;
            }

        }
        return ret;
    }

    /**
     * Checks if all the tiles in the selection have at least one free side.
     *
     * @param tilesSelection the tilesSelection to check
     * @param board          the game board
     * @return true if and only if all the tiles in the selection have at least one free side
     * @throws VoidBoardTileException if a tile on the board is null
     */
    private Boolean haveAllOneSidesFree(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
        Boolean ret = true;

        for (Coordinates coordinates : tilesSelection) {
            if (board.hasFree(coordinates.getY(), coordinates.getX()) == 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * Checks if the selection is valid.
     *
     * @param tilesSelection the tilesSelection to check
     * @param board          the game board
     * @return true if and only if the selection is valid
     * @throws VoidBoardTileException if a tile on the board is null
     */
    protected Boolean isSelectionValid(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
        return !isAnyTileNull(tilesSelection, board) &&
                (areAllSameColumn(tilesSelection) || areAllSameRow(tilesSelection)) &&
                (areAllAdjacents(tilesSelection) || areAllAjdacentsNotOrdered(tilesSelection)) &&
                haveAllOneSidesFree(tilesSelection, board);
    }

    /**
     * Checks if any tile in the selection is null.
     *
     * @param tilesSelection the tilesSelection to check
     * @param board          the game board
     * @return true if and only if any tile in the selection is null
     */
    private boolean isAnyTileNull(ArrayList<Coordinates> tilesSelection, Board board) {
        boolean ret = false;

        for (Coordinates coordinates : tilesSelection) {
            if (board.getGameBoard().get(coordinates.getY(), coordinates.getX()).getPlacedItem() == null) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * Checks if the column in the bookshelf is valid for inserting the pick.
     *
     * @param pickLen    the length of the pick
     * @param column     the column in the bookshelf
     * @param bookshelf  the bookshelf
     * @return true if and only if the column is valid for inserting the pick
     */
    protected Boolean isColumnValid(int pickLen, int column, Bookshelf bookshelf) {
        int freeTiles = 0;
        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            if (bookshelf.get(i, column) != null)
                break;
            else
                freeTiles++;
        }

        return pickLen <= freeTiles;
    }

    /**
     * Abstract method for picking and inserting tiles into the bookshelf.
     *
     * @param tilesSelection the tilesSelection to pick and insert
     * @param board          the game board
     * @param bookshelf      the bookshelf
     * @param column         the column in the bookshelf
     * @param order          the order of insertion
     * @throws PlayerIsWaitingException                   if the player is waiting
     * @throws SelectionIsEmptyException                  if the selection is empty
     * @throws SelectionNotValidException                 if the selection is not valid
     * @throws ColumnNotValidException                    if the column is not valid
     * @throws PickedColumnOutOfBoundsException           if the picked column is out of bounds
     * @throws PickDoesntFitColumnException               if the pick doesn't fit the column
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tilesSelection is different from the length of the order
     * @throws VoidBoardTileException                     if a tile on the board is null
     */
    public abstract void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, Board board, Bookshelf bookshelf, int column, int[] order) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException;

    /**
     * Abstract method for inserting a pick into the bookshelf.
     *
     * @param pickedItems the items picked
     * @param bookshelf   the bookshelf
     * @param column      the column in the bookshelf
     * @param order       the order of insertion
     * @throws ColumnNotValidException          if the column is not valid
     * @throws PlayerIsWaitingException         if the player is waiting
     * @throws PickDoesntFitColumnException     if the pick doesn't fit the column
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds
     */
    public abstract void insertPickInBookShelf(ArrayList<Item> pickedItems, Bookshelf bookshelf, int column, int[] order) throws ColumnNotValidException, PlayerIsWaitingException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException;

    /**
     * Gets the state number.
     *
     * @return the state number
     */
    public abstract int getStateNumber();
}
