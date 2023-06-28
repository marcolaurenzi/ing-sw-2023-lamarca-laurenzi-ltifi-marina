package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Exceptions.GameNotStartedException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;
/**
 *This interface represents the abstract concept of a generic GameState
 */
public interface GameState {
    /**
     * Advances the game to the next player's turn.
     *
     * @param game    the current game instance
     * @param players the list of players participating in the game
     * @throws FinishedGameException   if the game has already finished
     * @throws GameNotStartedException if the game has not yet started
     */

    void nextPlayer(Game game, ArrayList<Player> players) throws FinishedGameException, GameNotStartedException;

    /**
     * Retrieves the number associated with the state.
     *
     * @return the state number
     */
    int getStateNumber();
}
