package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import it.polimi.ingsw.Model.PlayerState.PlayerStateWaiting;

import java.util.ArrayList;

/**
 * Represents the game state for the last turn of the game.
 */
public class GameStateLastTurn implements GameState {
    /**
     * Constructs a GameStateLastTurn instance.
     */
    public GameStateLastTurn() {

    }
    /**
     * Advances the game to the next player's turn.
     *
     * @param game    The game instance.
     * @param players The list of players in the game.
     */
    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) {
        players.get(game.getCurrentPlayerIndex()).changeState(new PlayerStateWaiting());
        game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1) % players.size());
        players.get(game.getCurrentPlayerIndex()).changeState(new PlayerStateSelecting());
        if (game.getCurrentPlayerIndex() == 0) {
            game.setGameState(new GameStateFinished());
        }
    }

    /**
     * Gets the number representing the state.
     *
     * @return The state number.
     */
    public int getStateNumber() {
        return 1;
    };
}
