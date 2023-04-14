package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateLastTurn implements GameState{

    @Override
    public int nextPlayer(Game game, int currentPlayer, ArrayList<Player> players) {
        currentPlayer = (currentPlayer + 1) % players.size();
        if(currentPlayer == 0) {
            game.setGameState(new GameStateFinished());
            return 0;
        }
        else {
            return currentPlayer;
        }
    }

}
