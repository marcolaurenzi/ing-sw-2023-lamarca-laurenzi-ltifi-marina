package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.CommonGoalDeck;
import it.polimi.ingsw.Model.Decks.Deck;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Decks.PersonalGoalDeck;
import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.NumberOfPlayersException;
import it.polimi.ingsw.Model.GameState.GameState;
import it.polimi.ingsw.Model.GameState.GameStateRunning;
import it.polimi.ingsw.Model.GameState.GameStateStarting;
import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */

    private GameState gameState;
    final int gameID;
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

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */

    /**
     *
     * @param gameId
     * @param maxPlayers
     * @throws MaxNumberOfPlayersException
     * @throws IOException
     */
    public Game(int gameId, int maxPlayers) throws MaxNumberOfPlayersException, IOException {
        if(maxPlayers > 4 || maxPlayers < 2) {
            throw new MaxNumberOfPlayersException();
        }
        gameState = new GameStateStarting();
        this.gameID = gameId;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
        board = Utils.loadBoardFromFile("src/main/resources/configurations/BoardConfiguration.JSON");
    }

    /* ************************************************************************************************************
     *                          END OF CONSTRUCTORS
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */

    /**
     * This method is called when a player joins the game and adds him to the list of players
     * but does not create a new Player object
     * @param player
     */
    public void addPlayer(Player player) throws IOException, NumberOfPlayersException, AlreadyStartedGameException {
        players.add(player);
    }

    /**
     * This method is called when a player joins the game and adds him to the list of players
     * after creating a new Player object
     * @param playerID
     */
    public void addPlayer(String playerID) throws AlreadyStartedGameException {
        if(players.size() == maxPlayers) {
            throw new AlreadyStartedGameException();
        }
        players.add(new Player(playerID, this));
    }

    /**
     *
     */
    public void nextPlayer() throws FinishedGameException { currentPlayer = gameState.nextPlayer(this, currentPlayer, players); }

    /**
     *
     */
    public void initializeBoard(){
        board.initializeBoard(this);
    }

    /**
     * This method initializes the decks of the game,
     * and it is called only once at the beginning of the game
     * by the constructor
     *
     * @throws IOException
     */
    public void initializeDeck() throws IOException {

        (itemDeck = new ItemDeck()).initializeDeck();
        (commonGoalDeck = new CommonGoalDeck()).initializeDeck();
        (personalGoalDeck = new PersonalGoalDeck()).initializeDeck();

        commonGoalPointStacks = new CommonGoalPointStack[2];
        commonGoalPointStacks[0] = new CommonGoalPointStack(commonGoalDeck.draw(), maxPlayers);
        commonGoalPointStacks[1] = new CommonGoalPointStack(commonGoalDeck.draw(), maxPlayers);
    }

    /**
     *
     * @throws AlreadyStartedGameException
     * @throws IOException
     */
    public void initializeGame() throws IOException {
        this.initializeDeck();
        this.initializeBoard();
    }

    /**
     *
     * @throws AlreadyStartedGameException
     * @throws IOException
     */
    public void startGame() {
        setGameState(new GameStateRunning());
    }

    /**
     *
     */
    public void refreshBoard() {
        if(board.toRefresh()) {
            board.refreshBoard(this);
        }
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    public GameState getGameState() {
        return gameState;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    public Deck getPersonalGoalDeck() {
        return personalGoalDeck;
    }

    public CommonGoalPointStack[] getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

    public Player getCurrentPlayer() {
        Player temp = players.get(currentPlayer);
        return temp;
    }
    public int getMaxPlayers(){
        return maxPlayers;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     *                          START OF SETTER METHODS
     ************************************************************************************************************ */

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getId() {
        return gameID;
    }

    /* ************************************************************************************************************
     *                          END OF SETTER METHODS
     ************************************************************************************************************ */
}
