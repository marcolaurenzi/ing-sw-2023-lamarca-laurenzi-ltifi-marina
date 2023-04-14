package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateRunning implements GameState {

    @Override
    public int nextPlayer(Game game, int currentPlayer, ArrayList<Player> players) {
        if(players.get(currentPlayer).getBookshelf().isFull()) {
            game.setGameState(new GameStateLastTurn());
        }
        return (currentPlayer + 1) % players.size();
    }
}
