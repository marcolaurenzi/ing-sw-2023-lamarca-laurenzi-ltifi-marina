package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.*;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;
import it.polimi.ingsw.Model.GameState.*;
import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private GameState gameState; //to be fixed by moving the methods body into the states and callign gameState.methods in this class
    final String gameID;
    private Deck itemDeck;
    private Deck commonGoalDeck;
    private Deck personalGoalDeck;
    private CommonGoalPointStack[] commonGoalPointStacks;
    final int maxPlayers;
    private int currentPlayer;
    private ArrayList<Player> players;
    private Board board;
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
    public void addPlayer(Player player) throws IOException, NumberOfPlayersException {
        gameState.addPlayer(this, player);
    }

    /**
     * This method is called when a player joins the game and adds him to the list of players
     * after creating a new Player object
     * @param playerID
     */
    public void addPlayer(String playerID) throws IOException, NumberOfPlayersException {
        gameState.addPlayer(this, playerID);
    }
    public void initializeBoard(Board board, ItemDeck itemDeck, int maxPlayers){
        gameState.initializeBoard(board, itemDeck, maxPlayers);
    }
    public void nextPlayer(){
        gameState.nextPlayer(currentPlayer, players);
    }
    public Player getCurrentPlayer(){
        return gameState.getCurrentPlayer(players, currentPlayer);
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
    public void startGame() throws IOException, NumberOfPlayersException {

        DeckFactory deckFactory = new DeckFactory();

        this.itemDeck = deckFactory.factoryMethod(DeckEnum.ITEM).initializeDeck();
        this.personalGoalDeck = deckFactory.factoryMethod(DeckEnum.PERSONAL).initializeDeck();
        this.commonGoalDeck = deckFactory.factoryMethod(DeckEnum.COMMON).initializeDeck();
        this.commonGoalPointStacks = new CommonGoalPointStack[2];

        commonGoalPointStacks[0] = new CommonGoalPointStack((CommonGoal) commonGoalDeck.draw(), maxPlayers); //TODO cast to be solved
        commonGoalPointStacks[1] = new CommonGoalPointStack((CommonGoal) commonGoalDeck.draw(), maxPlayers); //TODO cast to be solved

        gameState.initializeBoard(this.board, (ItemDeck) itemDeck, this.maxPlayers); //TODO cast to be solved

        this.gameState = new GameStateRunning();
    }


    public Deck getPersonalGoalDeck() {
        return personalGoalDeck;
    }
    public CommonGoalPointStack[] getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

}
