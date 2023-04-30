package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerStates.PlayerStateSelecting;
import it.polimi.ingsw.Model.PlayerStates.PlayerStateWaiting;

import java.util.ArrayList;

public class GameStateRunning implements GameState {

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
}
