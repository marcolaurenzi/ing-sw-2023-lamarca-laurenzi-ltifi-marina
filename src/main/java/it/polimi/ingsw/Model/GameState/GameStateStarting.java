package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.GameNotStartedException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class  GameStateStarting implements GameState{

    @Override
    public void nextPlayer(Game game, ArrayList<Player> players) throws GameNotStartedException {
        throw new GameNotStartedException();
    }

    public int getStateNumber() {
        return 3;
    };
}
