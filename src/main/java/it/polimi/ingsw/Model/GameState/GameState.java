package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Exceptions.GameNotStartedException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public interface GameState {
    void nextPlayer(Game game, ArrayList<Player> players) throws FinishedGameException, GameNotStartedException;
    int getStateNumber();
}
