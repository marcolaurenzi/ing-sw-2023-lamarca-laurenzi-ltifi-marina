package it.polimi.ingsw.Model.PlayerStates;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BookShelf;
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
    public void select(ArrayList<Coordinates> tilesSelection, Board board, int i, int j) throws PlayerIsWaitingException {
        throw new PlayerIsWaitingException();
    }
    /**
     *
     * @throws PlayerIsWaitingException
     */
    @Override
    public ArrayList<Item> pick(ArrayList<Coordinates> tilesSelection, Board board) throws PlayerIsWaitingException{
        throw new PlayerIsWaitingException();
    }

    /**
     *
     * @throws PlayerIsWaitingException
     */
    @Override
    public void insertPickInBookShelf(ArrayList<Item> pickedItems, BookShelf bookShelf, int column, int[] order) throws PlayerIsWaitingException {
        throw new PlayerIsWaitingException();
    }


}
