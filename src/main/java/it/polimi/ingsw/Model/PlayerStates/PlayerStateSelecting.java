package it.polimi.ingsw.Model.PlayerStates;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;

/**
 * This state represent the state of a player that is selecting the tiles from the board
 */
public class PlayerStateSelecting extends PlayerState{
    /**
     * This method adds the coordinate i, j to the selection. It is called by the controller
     * when the player selects a tile
     * @param board the game board
     * @param i the x coordinate of the tile to select
     * @param j the y coordinate of the tile to select
     */
    @Override
    public void select(ArrayList<Coordinates> tilesSelection, Board board, int i, int j) throws SelectionIsFullException {
        if(selectionIsFull(tilesSelection)) {
            throw new SelectionIsFullException();
        }
        tilesSelection.add(new Coordinates(i, j));
    }
    /**
     * Assign the selected tiles to the pickedItems array. Only if the selection is the pick conditions are met
     * @param board the board of the game
     * @throws SelectionNotValidException if the selection is not valid AKA not all the selected tiles have at least one
     * side free or the selected tiles are not adjacent
     * @throws SelectionIsEmptyException if the selection is empty
     */
    @Override
    public ArrayList<Item> pick(ArrayList<Coordinates> tilesSelection, Board board) throws SelectionNotValidException, SelectionIsEmptyException {
        ArrayList<Item> ret = new ArrayList<>(3);

        if(tilesSelection.get(0) == null)
            throw new SelectionIsEmptyException();
        else if(isSelectionValid(tilesSelection, board))
            throw new SelectionNotValidException();

        else {
            for(int i = 0; i < 3; i++) {
                if(tilesSelection.get(i) == null) {
                    break;
                }
                else
                    ret.add(board.drawItem(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()));
            }
        }


        return ret;
    }
    /**
     * insert the pickedItems in bookShelf
     * @param column the column where the player wants to put the items
     * @throws ColumnNotValidException if the items don't fit the column
     */
    @Override
    public void insertPickInBookShelf(ArrayList<Item> pickedItems, BookShelf bookShelf, int column, int[] order) throws ColumnNotValidException {
        //checkin len of pickedItems
        int pickedItemsLen = 0;
        for(int i = 0; i < 3; i++) {
            if(pickedItems.get(i) == null)
                break;
            else
                pickedItemsLen++;
        }

        if(!isColumnValid(pickedItemsLen, column, bookShelf)) {
            throw new ColumnNotValidException();
        }

        bookShelf.insert(column, pickedItems);
    }

}
