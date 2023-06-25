package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import it.polimi.ingsw.Model.PlayerState.PlayerStateWaiting;

import java.util.ArrayList;

public class GameStateRunning implements GameState {

    /**
     * Moves the game to the next player's turn.
     * If the current player's bookshelf is full:
     * - If the current player is the last player in the list, the game state is set to GameStateFinished.
     * - Otherwise, the game state is set to GameStateLastTurn.
     * Changes the current player's state to PlayerStateWaiting and the next player's state to PlayerStateSelecting.
     *
     * @param game    the game object
     * @param players the list of players in the game
     */
    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) {
        if(players.get(game.getCurrentPlayerIndex()).getBookshelf().isFull()) {
            if(players.get(game.getCurrentPlayerIndex()).equals(players.get(players.size()-1))) {
                game.setGameState(new GameStateFinished());
            }
            else {
                game.setGameState(new GameStateLastTurn());
            }
        }
        players.get(game.getCurrentPlayerIndex()).changeState(new PlayerStateWaiting());
        game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1)%players.size());
        players.get(game.getCurrentPlayerIndex()).changeState(new PlayerStateSelecting());
    }

    /**
     * Returns the number representing the state of the game.
     *
     * @return the state number
     */
    public int getStateNumber() {
        return 2;
    }
}
