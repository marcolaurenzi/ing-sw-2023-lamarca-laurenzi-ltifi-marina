package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;

import java.io.IOException;
import java.util.ArrayList;

public class  GameStateStarting implements GameState{

    @Override
    public int nextPlayer(Game game, int currentPlayer, ArrayList<Player> players) {
        return 0;
    }

}
