package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.PlayerIsWaitingException;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;

/**
 * This state represents the state of a player that is waiting his turn
 */
public class PlayerStateWaiting extends PlayerState{

    /**
     *
     * @throws PlayerIsWaitingException
     */
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, Board board, Bookshelf bookshelf, int column, int[] order) throws PlayerIsWaitingException{
        throw new PlayerIsWaitingException();
    }

    /**
     *
     * @throws PlayerIsWaitingException
     */
    @Override
    public void insertPickInBookShelf(ArrayList<Item> pickedItems, Bookshelf bookshelf, int column, int[] order) throws PlayerIsWaitingException {
        throw new PlayerIsWaitingException();
    }

    public int getStateNumber() {
        return 1;
    }
}
