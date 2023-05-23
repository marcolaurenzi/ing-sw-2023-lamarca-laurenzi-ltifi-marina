package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateFinished implements GameState{

    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) throws FinishedGameException {
        throw new FinishedGameException();
    }
    public int getStateNumber() {
        return 0;
    };
}
