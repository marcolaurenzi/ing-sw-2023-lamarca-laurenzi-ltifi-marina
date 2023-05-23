package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import it.polimi.ingsw.Model.PlayerState.PlayerStateWaiting;

import java.util.ArrayList;

public class GameStateLastTurn implements GameState{

    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) {
        players.get(game.getCurrentPlayerIndex()).changeState(new PlayerStateWaiting());
        game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1)%players.size());
        players.get(game.getCurrentPlayerIndex()).changeState(new PlayerStateSelecting());
        if(game.getCurrentPlayerIndex() == 0) {
            game.setGameState(new GameStateFinished());
        }
    }
    public int getStateNumber() {
        return 1;
    };
}
