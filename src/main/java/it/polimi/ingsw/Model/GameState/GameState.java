package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;

import java.io.IOException;
import java.util.ArrayList;

public interface GameState {
    public void addPlayer(Game game, Player player) throws IOException, NumberOfPlayersException;
    public void addPlayer(Game game, String playerID) throws IOException, NumberOfPlayersException;
    public void initializeBoard(Board board, ItemDeck itemDeck, int maxPlayers);
    public void nextPlayer(int currentPlayer, ArrayList<Player> players);
    public Player getCurrentPlayer(ArrayList<Player> players, int currentPlayer);
    public void startGame(Board board, ItemDeck itemdeck, int maxPlayers);




}
