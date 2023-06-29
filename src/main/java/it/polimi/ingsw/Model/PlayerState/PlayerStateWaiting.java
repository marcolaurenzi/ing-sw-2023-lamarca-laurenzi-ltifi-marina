package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.PlayerIsWaitingException;
import it.polimi.ingsw.Model.Item;
import java.util.ArrayList;

/**
 * This state represents the state of a player that is waiting for their turn.
 */
public class PlayerStateWaiting extends PlayerState {
    /**
     * Constructs a PlayerStateWaiting object.
     */
    public PlayerStateWaiting() {
        super();
    }

    /**
     * Throws a PlayerIsWaitingException indicating that the player cannot pick and insert tiles into the bookshelf in the waiting state.
     *
     * @param tilesSelection the selected tiles' coordinates
     * @param board the game board
     * @param bookshelf the player's bookshelf
     * @param column the column of the bookshelf
     * @param order the order of the selected tiles
     * @throws PlayerIsWaitingException if the player is in the waiting state
     */
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, Board board, Bookshelf bookshelf, int column, int[] order) throws PlayerIsWaitingException {
        throw new PlayerIsWaitingException();
    }

    /**
     * Throws a PlayerIsWaitingException indicating that the player cannot insert the picked items into the bookshelf in the waiting state.
     *
     * @param pickedItems the items picked by the player
     * @param bookshelf the player's bookshelf
     * @param column the column of the bookshelf
     * @param order the order of the picked items
     * @throws PlayerIsWaitingException if the player is in the waiting state
     */
    @Override
    public void insertPickInBookShelf(ArrayList<Item> pickedItems, Bookshelf bookshelf, int column, int[] order) throws PlayerIsWaitingException {
        throw new PlayerIsWaitingException();
    }

    /**
     * Returns the number representing the state of a player waiting for their turn.
     *
     * @return the state number representing the waiting state
     */
    public int getStateNumber() {
        return 1;
    }
}
