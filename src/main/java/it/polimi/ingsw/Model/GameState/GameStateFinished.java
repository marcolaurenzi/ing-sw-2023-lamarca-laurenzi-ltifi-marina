package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateFinished implements GameState{

    @Override
    public int nextPlayer(Game game, int currentPlayer, ArrayList<Player> players) {
        return 0;
    }

}
