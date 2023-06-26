package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

/**
 * Represents the state of the game when it has finished.
 */
public class GameStateFinished implements GameState{
    /**
     * Constructs a GameStateFinished instance.
     */
    public GameStateFinished() {

    }
    /**
     * Throws a FinishedGameException to indicate that the game has already finished.
     *
     * @param game    the current game instance
     * @param players the list of players in the game
     * @throws FinishedGameException if the game has already finished
     */
    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) throws FinishedGameException {
        throw new FinishedGameException();
    }

    /**
     * Returns the number representing the state of the game.
     *
     * @return the state number (always 0 for GameStateFinished)
     */
    public int getStateNumber() {
        return 0;
    };
}
