package it.polimi.ingsw.Model.PlayerStates;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;

/**
 * This state represent the state of a player that is selecting the tiles from the board
 */
public class PlayerStateSelecting extends PlayerState{

    /**
     * This method take in input an ArrayList of coordinates from the controller indicating the tiles that the player
     * want to pick and inserts the items in those tiles in the bookshelf in the order specified from the player. It
     * also controls if it is possible to pick the selection according to the game's rules.
     * Explanation of the order:
     * The controller specifies the order using an order array. The order array is an array of integer from 0 to
     * tilesSelection.size()-1. The number n in a certain index i of the order array indicates that the player wants
     * the tiles of index n in the tilesSelection in the index i of the orderedTilesSelection, for example:
     * The player want picked the tiles in this order [CATS, TROPHIES, PLANTS] and the order is [1, 2, 0]. This means
     * that the resulting ordered ArrayList inserted in the bookshelf will be : [TROPHIES, PLANTS, CATS]
     * @param board the board of the game
     *
     * @throws SelectionNotValidException if the selection is not valid AKA not all the selected tiles have at least one
     * side free or the selected tiles are not adjacent
     * @throws SelectionIsEmptyException if the selection is empty
     */
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, Board board, Bookshelf bookshelf, int column, int[] order) throws SelectionNotValidException, SelectionIsEmptyException, PlayerIsWaitingException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException {
        ArrayList<Item> itemsToInsert = new ArrayList<>(3);

        if (tilesSelection.get(0) == null)
            throw new SelectionIsEmptyException();
        else if (isSelectionValid(tilesSelection, board))
            throw new SelectionNotValidException();
        else if (tilesSelection.size() != order.length)
            throw new TilesSelectionSizeDifferentFromOrderLengthException();

        else {
            for (Coordinates coordinates : tilesSelection)
                itemsToInsert.add(board.drawItem(coordinates.getX(), coordinates.getY()));

            insertPickInBookShelf(itemsToInsert, bookshelf, column, order);
        }
    }

    /**
     * insert the pickedItems in bookShelf
     * @param column the column where the player wants to put the items
     *
     * @throws ColumnNotValidException if the items don't fit the column
     */
    @Override
    public void insertPickInBookShelf(ArrayList<Item> pickedItems, Bookshelf bookshelf, int column, int[] order) throws ColumnNotValidException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        if(!isColumnValid(pickedItems.size(), column, bookshelf)) {
            throw new ColumnNotValidException();
        }

        //reordering the ArrayList of picked items according to the order wanted to player
        ArrayList<Item> orderedPickedItems = new ArrayList<>();

        for(int i = 0; i < pickedItems.size(); i++)
            orderedPickedItems.add(null);

        int i = 0;
        for (int n : order) {
            orderedPickedItems.set(i, pickedItems.get(n));
            i++;
        }

        //actual insert in bookshelf
        bookshelf.insert(column, orderedPickedItems);
    }

}
