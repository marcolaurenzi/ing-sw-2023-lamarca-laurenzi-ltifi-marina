package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.Creator;
import it.polimi.ingsw.Model.Decks.Deck;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.GameState.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private GameState gameState; //to be fixed by moving the methods body into the states and callign gameState.methods in this class
    final String gameID;
    private Deck itemDeck;
    private Deck commonGoalDeck;
    private Deck personalGoalDeck;
    final int maxPlayers;
    private int currentPlayer;
    ArrayList<Player> players;
    Board board;
    public Game(String gameId, int maxPlayers){
        gameState = new GameStateStarting();
        this.gameID = gameId;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
    }

    /**
     * This method is called when a player joins the game and adds him to the list of players
     * but does not create a new Player object
     * @param player
     */
    public void addPlayer(Player player){
        if(players.size() < maxPlayers){
            players.add(player);
        }
        if(players.size() == maxPlayers){
            startGame();
        }
    }

    /**
     * This method is called when a player joins the game and adds him to the list of players
     * after creating a new Player object
     * @param playerID
     */
    public void addPlayer(String playerID){
        if (players.size() < maxPlayers){
            players.add(new Player(playerID, this));
        }else if(players.size() == maxPlayers){
            startGame();
        }
    }
    public void initializeBoard(){
        this.board = new Board();
    }
    public void nextPlayer(){
        currentPlayer = (currentPlayer + 1) % players.size();
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }
    public int getMaxPlayers(){
        return maxPlayers;
    }
    public String getGameID(){
        return gameID;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void startGame(){
        this.itemDeck = Creator.createItemDeck(); //TODO: check if this is the right place to create the decks
        this.commonGoalDeck = Creator.createCommonGoalDeck();//TODO: check if this is the right way of implementing the decks through the factory method
        this.personalGoalDeck = Creator.createPersonalGoalDeck();
        this.gameState = new GameStatePlaying();
    }




}
