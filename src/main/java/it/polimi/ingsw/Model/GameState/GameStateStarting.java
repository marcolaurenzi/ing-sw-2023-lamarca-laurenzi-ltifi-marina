package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;

import java.io.IOException;
import java.util.ArrayList;

public class GameStateStarting implements GameState{
    @Override
    public void addPlayer(Game game, Player player) throws IOException, NumberOfPlayersException {
        if (game.getPlayers().size() < game.getMaxPlayers()){
            game.getPlayers().add(player);
        }
        if (game.getPlayers().size() == game.getMaxPlayers()){
            game.startGame();
        }
    }

    @Override
    public void addPlayer(Game game, String playerID) throws IOException, NumberOfPlayersException {
        if (game.getPlayers().size() < game.getMaxPlayers()){
            game.getPlayers().add(new Player(playerID, game));
        }
        if (game.getPlayers().size() == game.getMaxPlayers()){
            game.startGame();
        }
    }

    @Override
    public void initializeBoard(Board board, ItemDeck itemDeck, int maxPlayers) {
        board = new Board();

        Matrix<BoardTile> gameBoard = board.getGameBoard();

        for(int i = 0; i < gameBoard.getColumnDimension(); i++) {
            for(int j = 0; j < gameBoard.getRowDimension(); j++) {
                if(gameBoard.get(i, j).getNumberOfPlayersSign() <= maxPlayers) {
                    gameBoard.get(i, j).placeItem(itemDeck.draw());
                }
            }
        }
    }

    @Override
    public void nextPlayer(int currentPlayer, ArrayList<Player> players) {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    @Override
    public Player getCurrentPlayer(ArrayList<Player> players, int currentPlayer) {
        return players.get(currentPlayer);
    }

    @Override
    public void startGame(Game game) {

    }
}
