package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.Creator;
import it.polimi.ingsw.Model.Decks.Deck;
import it.polimi.ingsw.Model.Decks.DeckEnum;
import it.polimi.ingsw.Model.Decks.DeckFactory;
import it.polimi.ingsw.Model.GameState.*;

import java.io.IOException;
import java.util.ArrayList;

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
    public void startGame() throws IOException {

        DeckFactory deckFactory = new DeckFactory();

        this.itemDeck = deckFactory.factoryMethod(DeckEnum.ITEM).initializeDeck();
        this.personalGoalDeck = deckFactory.factoryMethod(DeckEnum.PERSONAL).initializeDeck();
        this.commonGoalDeck = deckFactory.factoryMethod(DeckEnum.COMMON).initializeDeck();

        this.gameState = new GameStatePlaying();
    }




}
