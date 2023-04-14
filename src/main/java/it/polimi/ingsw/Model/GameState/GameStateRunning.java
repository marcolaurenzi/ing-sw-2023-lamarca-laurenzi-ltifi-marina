package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateRunning implements GameState {
    @Override
    public void addPlayer(Game game, Player player) throws NumberOfPlayersException {
        System.out.println("Game is already running, you can't join it");
    }

    @Override
    public void addPlayer(Game game, String playerID) throws NumberOfPlayersException {
        System.out.println("Game is already running, you can't join it");
    }

    @Override
    public void initializeBoard(Board board, ItemDeck itemDeck, int maxPlayers) {
        if(board.toRefresh()){
            board.refreshBoard();
        }
    }

    @Override
    public void nextPlayer(int currentPlayer, ArrayList<Player> players) {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    @Override
    public Player getCurrentPlayer(ArrayList<Player> players, int currentPlayer) {
        return null;
    }

    @Override
    public void startGame(Board board, ItemDeck itemdeck, int maxPlayers) {

    }
}
