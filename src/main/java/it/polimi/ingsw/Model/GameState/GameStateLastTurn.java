package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Player;

import java.util.ArrayList;

public class GameStateLastTurn implements GameState{
    @Override
    public void addPlayer(Player player) {
        throw new IllegalStateException("Game is already started");
    }
    public void addPlayer(String playerID) {
        throw new IllegalStateException("Game is already started");
    }

    @Override
    public void initializeBoard() {
        throw new IllegalStateException("Game is already started");
    }

    @Override
    public void nextPlayer() {

    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    @Override
    public int getMaxPlayers() {
        return 0;
    }

    @Override
    public String getGameID() {
        return null;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return null;
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("Game is already started");
    }
}
