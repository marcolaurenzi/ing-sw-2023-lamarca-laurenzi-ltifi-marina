package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateLastTurn implements GameState{
    @Override
    public void addPlayer(Game game, Player player) {
        throw new IllegalStateException("Game is already started");
    }
    public void addPlayer(Game game, String playerID) {
        throw new IllegalStateException("Game is already started");
    }

    @Override
    public void initializeBoard(Board board, ItemDeck itemDeck, int maxPlayers) {
        throw new IllegalStateException("Game is already started");
    }

    @Override
    public void nextPlayer(int currentPlayer, ArrayList<Player> players) {

    }

    @Override
    public Player getCurrentPlayer(ArrayList<Player> players, int currentPlayer) {
        return null;
    }

    @Override
    public void startGame(Game game) {
        throw new IllegalStateException("Game is already started");
    }
}
