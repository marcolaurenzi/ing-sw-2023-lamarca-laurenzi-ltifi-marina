package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public interface GameState {
    public void addPlayer(Player player);
    public void addPlayer(String playerID);
    public void initializeBoard();
    public void nextPlayer();
    public Player getCurrentPlayer();
    public int getMaxPlayers();
    public String getGameID();
    public ArrayList<Player> getPlayers();
    public void startGame();



}
