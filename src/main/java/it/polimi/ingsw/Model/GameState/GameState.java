package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.io.IOException;
import java.util.ArrayList;

public interface GameState {
    public int nextPlayer(Game game, int currentPlayer, ArrayList<Player> players);
}
