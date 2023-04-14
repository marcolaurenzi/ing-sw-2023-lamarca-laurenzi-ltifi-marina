package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.CommonGoalDeck;
import it.polimi.ingsw.Model.Decks.Deck;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Decks.PersonalGoalDeck;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;
import it.polimi.ingsw.Model.GameState.GameState;
import it.polimi.ingsw.Model.GameState.GameStateRunning;
import it.polimi.ingsw.Model.GameState.GameStateStarting;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private GameState gameState; //to be fixed by moving the methods body into the states and callign gameState.methods in this class
    final String gameID;
    private ItemDeck itemDeck;
    private CommonGoalDeck commonGoalDeck;
    private PersonalGoalDeck personalGoalDeck;
    private CommonGoalPointStack[] commonGoalPointStacks;
    final int maxPlayers;
    private int currentPlayer;
    private ArrayList<Player> players;
    public Board getBoard() {
        return board;
    }
    private final Board board;
    public Game(String gameId, int maxPlayers) throws MaxNumberOfPlayersException, IOException {
        if(maxPlayers > 4 || maxPlayers < 2) {
            throw new MaxNumberOfPlayersException();
        }
        gameState = new GameStateStarting();
        this.gameID = gameId;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
        board = Utils.loadBoardFromFile("src/main/resources/configurations/BoardConfiguration.JSON");
        this.initializeDeck();
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

    /**
     * This method initializes the decks of the game,
     * and it is called only once at the beginning of the game
     * by the constructor
     *
     * @throws IOException
     * @throws NumberOfPlayersException
     */
    public void initializeDeck() throws IOException {

        (itemDeck = new ItemDeck()).initializeDeck();
        (commonGoalDeck = new CommonGoalDeck()).initializeDeck();
        (personalGoalDeck = new PersonalGoalDeck()).initializeDeck();

        commonGoalPointStacks = new CommonGoalPointStack[2];
        commonGoalPointStacks[0] = new CommonGoalPointStack(commonGoalDeck.draw(), maxPlayers); //TODO cast to be solved
        commonGoalPointStacks[1] = new CommonGoalPointStack(commonGoalDeck.draw(), maxPlayers); //TODO cast to be solved
    }

    public void startGame() throws IOException, NumberOfPlayersException {
        gameState.startGame(board, itemDeck, maxPlayers);
        this.gameState = new GameStateRunning();
    }

    public Deck getPersonalGoalDeck() {
        return personalGoalDeck;
    }

    public CommonGoalPointStack[] getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

}
