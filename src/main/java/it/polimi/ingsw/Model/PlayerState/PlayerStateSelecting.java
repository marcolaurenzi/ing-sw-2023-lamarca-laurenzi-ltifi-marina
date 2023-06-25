package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;

/**
 * This state represents the state of a player that is selecting the tiles from the board.
 */
public class PlayerStateSelecting extends PlayerState {

    /**
     * This method takes an ArrayList of coordinates from the controller indicating the tiles that the player
     * wants to pick and inserts the items in those tiles in the bookshelf in the order specified by the player. It
     * also checks if it is possible to pick the selection according to the game's rules.
     *
     * Explanation of the order:
     * The controller specifies the order using an order array. The order array is an array of integers from 0 to
     * tilesSelection.size()-1. The number n in a certain index i of the order array indicates that the player wants
     * the tiles of index n in the tilesSelection in the index i of the orderedTilesSelection. For example:
     * The player wants to pick the tiles in this order [CATS, TROPHIES, PLANTS] and the order is [1, 2, 0]. This means
     * that the resulting ordered ArrayList inserted in the bookshelf will be: [TROPHIES, PLANTS, CATS]
     *
     * @param tilesSelection the ArrayList of coordinates representing the selected tiles
     * @param board the board of the game
     * @param bookshelf the bookshelf where the items will be inserted
     * @param column the column where the items will be placed
     * @param order the order in which the items will be inserted
     *
     * @throws SelectionNotValidException if the selection is not valid, meaning that not all the selected tiles have at least one
     * side free or the selected tiles are not adjacent
     * @throws SelectionIsEmptyException if the selection is empty
     * @throws PlayerIsWaitingException if the player is waiting for an action
     * @throws ColumnNotValidException if the selected column is not valid
     * @throws PickedColumnOutOfBoundsException if the selected column is out of bounds
     * @throws PickDoesntFitColumnException if the picked items don't fit the column
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tilesSelection is different from the length of the order array
     * @throws VoidBoardTileException if a selected tile on the board is empty
     */
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, Board board, Bookshelf bookshelf, int column, int[] order) throws SelectionNotValidException, SelectionIsEmptyException, PlayerIsWaitingException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException {
        ArrayList<Item> itemsToInsert = new ArrayList<>(3);

        if (tilesSelection.get(0) == null)
            throw new SelectionIsEmptyException();
        else if (!isSelectionValid(tilesSelection, board))
            throw new SelectionNotValidException();
        else if (tilesSelection.size() != order.length)
            throw new TilesSelectionSizeDifferentFromOrderLengthException();

        else {
            for (Coordinates coordinates : tilesSelection)
                itemsToInsert.add(board.drawItem(coordinates.getY(), coordinates.getX()));

            insertPickInBookShelf(itemsToInsert, bookshelf, column, order);
        }
    }

    /**
     * Inserts the pickedItems in the bookshelf.
     *
     * @param pickedItems the ArrayList of picked items
     * @param bookshelf the bookshelf where the items will be inserted
     * @param column the column where the items will be placed
     * @param order the order in which the items will be inserted
     *
     * @throws ColumnNotValidException if the items don't fit the column
     * @throws PickDoesntFitColumnException if the picked items don't fit the column
     * @throws PickedColumnOutOfBoundsException if the selected column is out of bounds
     */
    @Override
    public void insertPickInBookShelf(ArrayList<Item> pickedItems, Bookshelf bookshelf, int column, int[] order) throws ColumnNotValidException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        if (!isColumnValid(pickedItems.size(), column, bookshelf)) {
            throw new ColumnNotValidException();
        }

        // Reordering the ArrayList of picked items according to the order wanted by the player.
        ArrayList<Item> orderedPickedItems = new ArrayList<>();

        for (int i = 0; i < pickedItems.size(); i++)
            orderedPickedItems.add(null);

        int i = 0;
        for (int n : order) {
            orderedPickedItems.set(i, pickedItems.get(n));
            i++;
        }

        // Actual insert in the bookshelf.
        bookshelf.insert(column, orderedPickedItems);
    }

    /**
     * Returns the state number.
     *
     * @return the state number
     */
    public int getStateNumber() {
        return 0;
    }

}
