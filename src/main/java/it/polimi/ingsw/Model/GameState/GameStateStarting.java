package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.GameNotStartedException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;
/**
 *This class represents the state of the game when it has not started yet.
 */
public class GameStateStarting implements GameState {

    /**
     * Throws a GameNotStartedException when attempting to move to the next player's turn.
     *
     * @param game    The game instance.
     * @param players The list of players.
     * @throws GameNotStartedException If the game has not started yet.
     */
    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) throws GameNotStartedException {
        throw new GameNotStartedException();
    }

    /**
     * Returns the number associated with this state.
     *
     * @return The state number.
     */
    public int getStateNumber() {
        return 3;
    }
}
