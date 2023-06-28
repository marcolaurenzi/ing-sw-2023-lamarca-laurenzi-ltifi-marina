package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.CommonGoalDeck;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Decks.PersonalGoalDeck;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.GameState.*;
import it.polimi.ingsw.Model.Goals.CommonGoals.*;

import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;
import it.polimi.ingsw.Utils.GameStatusToFile;
import it.polimi.ingsw.Utils.PlayerStatusToFile;
import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class represents the game instance of the MyShelfie board game.
 * It manages the game state, players, board, decks, and the flow of the game.
 */
public class Game {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */

    /**
     * Represents the current state of the game.
     */
    private GameState gameState;
    /**
     * Represents the ID of the game.
     */
    final int gameID;
    /**
     * Represents the item deck of the game.
     */
    private ItemDeck itemDeck;
    /**
     * Represents the personal goal deck of the game.
     */
    private PersonalGoalDeck personalGoalDeck;
    /**
     * Represents the common goal point stacks of the game.
     */
    private CommonGoalPointStack[] commonGoalPointStacks;
    /**
     * Represents the maximum number of players in the game.
     */
    private final int maxPlayers;
    /**
     * Represents the current player in the game.
     */
    private int currentPlayer;
    /**
     * Represents the list of players in the game.
     */
    private final ArrayList<Player> players;
    /**
     * Represents the board of the game.
     */
    private final Board board;

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */

    /**
     * Constructs a new Game instance with the specified game ID and maximum number of players.
     *
     * @param gameId      the ID of the game
     * @param maxPlayers  the maximum number of players
     * @throws MaxNumberOfPlayersException if the maximum number of players is invalid
     * @throws IOException                if there is an error loading the board configuration file
     */
    public Game(int gameId, int maxPlayers) throws MaxNumberOfPlayersException, IOException {
        if (maxPlayers > 4 || maxPlayers < 2) {
            throw new MaxNumberOfPlayersException();
        }
        gameState = new GameStateStarting();
        this.gameID = gameId;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
        board = Utils.loadBoardFromFile("configurations/BoardConfiguration.json");
        itemDeck = new ItemDeck();
        board.setItemDeck(itemDeck);
        this.initializeGame();
    }

    /**
     * Constructs a new Game instance based on a saved game status and player statuses.
     *
     * @param gameStatus   the saved game status
     * @param playerStatus the list of saved player statuses
     */
    public Game(GameStatusToFile gameStatus, ArrayList<PlayerStatusToFile> playerStatus) {
        // Initialize game state based on saved game state
        switch (gameStatus.getGameState()) {
            case 0 -> this.gameState = new GameStateFinished();
            case 1 -> this.gameState = new GameStateLastTurn();
            case 2 -> this.gameState = new GameStateRunning();
            case 3 -> this.gameState = new GameStateStarting();
        }

        // Set game attributes based on saved game status
        this.gameID = gameStatus.getGameID();
        this.itemDeck = gameStatus.getItemDeck();
        this.personalGoalDeck = gameStatus.getPersonalGoalDeck();
        this.commonGoalPointStacks = new CommonGoalPointStack[2];

        // Set common goal point stacks based on saved game status
        switch (gameStatus.getCommonGoalPointStacksNames()[0]) {
            case "CommonGoal0" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal01(2, 6), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal1" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal01(4, 4), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal2" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal2(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal4" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal4(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal5" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal5(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal6" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal6(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal7" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal7(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal8" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal8(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal9" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal9(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal10" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal10(), gameStatus.getCommonGoalPointStacks().get(0));
            case "CommonGoal11" -> this.commonGoalPointStacks[0] = new CommonGoalPointStack(new CommonGoal11(), gameStatus.getCommonGoalPointStacks().get(0));
        }

        switch (gameStatus.getCommonGoalPointStacksNames()[1]) {
            case "CommonGoal0" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal01(2, 6), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal1" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal01(4, 4), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal2" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal2(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal4" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal4(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal5" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal5(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal6" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal6(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal7" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal7(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal8" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal8(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal9" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal9(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal10" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal10(), gameStatus.getCommonGoalPointStacks().get(1));
            case "CommonGoal11" -> this.commonGoalPointStacks[1] = new CommonGoalPointStack(new CommonGoal11(), gameStatus.getCommonGoalPointStacks().get(1));
        }

        // Set other game attributes based on saved game status
        this.maxPlayers = gameStatus.getMaxPlayers();
        this.currentPlayer = gameStatus.getCurrentPlayerIndex();
        this.board = gameStatus.getBoard();
        this.itemDeck = new ItemDeck();

        // Initialize players based on saved player statuses
        this.players = new ArrayList<>();
        for (PlayerStatusToFile p : playerStatus) {
            this.players.add(new Player(p, this));
        }
    }

    /* ************************************************************************************************************
     *                          END OF CONSTRUCTORS
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */

    /**
     * Adds a player to the game by creating a new Player object and adding it to the list of players.
     *
     * @param playerID the ID of the player to be added
     * @throws AlreadyStartedGameException if the game has already started
     */
    public void addPlayer(String playerID) throws AlreadyStartedGameException {
        if (players.size() == maxPlayers) {
            throw new AlreadyStartedGameException();
        }
        players.add(new Player(playerID, this));
    }

    /**
     * Advances the current player to the next player in the turn order.
     *
     * @throws FinishedGameException    if the game has already finished
     * @throws GameNotStartedException if the game has not yet started
     */
    public void nextPlayer() throws FinishedGameException, GameNotStartedException {
        gameState.nextPlayer(this, players);
    }

    /**
     * Initializes the game board by placing items on the board tiles.
     * This method is called only once at the beginning of the game.
     */
    public void initializeBoard() {
        board.initializeBoard(this);
    }

    /**
     * Initializes the game decks (item deck, personal goal deck, and common goal deck).
     * This method is called only once at the beginning of the game.
     *
     * @throws IOException if there is an error loading the deck configuration files
     */
    public void initializeDeck() throws IOException {
        (itemDeck = new ItemDeck()).initializeDeck();
        CommonGoalDeck commonGoalDeck;
        (commonGoalDeck = new CommonGoalDeck()).initializeDeck();
        (personalGoalDeck = new PersonalGoalDeck()).initializeDeck();

        commonGoalPointStacks = new CommonGoalPointStack[2];
        commonGoalPointStacks[0] = new CommonGoalPointStack(commonGoalDeck.draw(), maxPlayers);
        commonGoalPointStacks[1] = new CommonGoalPointStack(commonGoalDeck.draw(), maxPlayers);
    }

    /**
     * Initializes the game by calling the methods to initialize the decks and the board.
     *
     * @throws IOException if there is an error loading the deck configuration files
     */
    public void initializeGame() throws IOException {
        this.initializeDeck();
        this.initializeBoard();
    }

    /**
     * Starts the game by setting the game state to running.
     */
    public void startGame() {
        setGameState(new GameStateRunning());
    }

    /**
     * Checks if the board needs to be refreshed and refreshes it if necessary.
     * This method is called every round.
     */
    public void refreshBoard() {
        if (board.toRefresh()) {
            board.refreshBoard(this);
        }
    }

    /**
     * Advances the game to the next turn by calling the nextPlayer method.
     *
     * @throws FinishedGameException    if the game has already finished
     * @throws GameNotStartedException if the game has not yet started
     */
    public void nextTurn() throws FinishedGameException, GameNotStartedException {
        gameState.nextPlayer(this, players);
    }

    /**
     * Removes a player from the game.
     *
     * @param playerID the ID of the player to be removed
     * @throws MissingPlayerException if the player is not found in the game
     */
    public void removePlayer(String playerID) throws MissingPlayerException {
        for (Player player : players) {
            if (player.getPlayerID().equals(playerID)) {
                players.remove(player);
                return;
            }
        }
        throw new MissingPlayerException();
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    /**
     * Returns the current game state.
     *
     * @return the current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Returns the item deck of the game.
     *
     * @return the item deck
     */
    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    /**
     * Returns the personal goal deck of the game.
     *
     * @return the personal goal deck
     */
    public PersonalGoalDeck getPersonalGoalDeck() {
        return personalGoalDeck;
    }

    /**
     * Returns the common goal point stacks of the game.
     *
     * @return the common goal point stacks
     */
    public CommonGoalPointStack[] getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

    /**
     * Returns the current player in the game.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Returns the index of the current player in the game.
     *
     * @return the index of the current player
     */
    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    /**
     * Returns the maximum number of players allowed in the game.
     *
     * @return the maximum number of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Returns the list of players in the game.
     *
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the game board.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the ID of the game.
     *
     * @return the game ID
     */
    public int getId() {
        return gameID;
    }

    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     *                          START OF SETTER METHODS
     ************************************************************************************************************ */

    /**
     * Sets the game state to the specified game state.
     *
     * @param gameState the game state to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Returns the list of bookshelves owned by the players in the game.
     *
     * @return the list of bookshelves
     */
    public ArrayList<Bookshelf> getBookshelves() {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        for (Player player : players) {
            bookshelves.add(player.getBookshelf());
        }
        return bookshelves;
    }

    /**
     * Returns the personal goal of the player with the specified ID.
     *
     * @param playerId the ID of the player
     * @return the personal goal of the player
     * @throws MissingPlayerException if the player is not found in the game
     */
    public PersonalGoal getPersonalGoal(String playerId) throws MissingPlayerException {
        for (Player player : players) {
            if (player.getPlayerID().equals(playerId)) {
                return player.getPersonalGoal();
            }
        }
        throw new MissingPlayerException();
    }

    /**
     * Returns an array indicating whether each common goal has already been achieved by the player with the specified ID.
     *
     * @param playerId the ID of the player
     * @return an array indicating whether each common goal has already been achieved
     * @throws MissingPlayerException if the player is not found in the game
     */
    public boolean[] getIsCommonGoalAchieved(String playerId) throws MissingPlayerException {
        for (Player player : players) {
            if (player.getPlayerID().equals(playerId)) {
                return player.getIsCommonGoalAlreadyAchieved();
            }
        }
        throw new MissingPlayerException();
    }

    /**
     * Returns the list of player IDs in the game.
     *
     * @return the list of player IDs
     */
    public ArrayList<String> getPlayersId() {
        ArrayList<String> playersIds = new ArrayList<>();
        for (Player player : players) {
            playersIds.add(player.getPlayerID());
        }
        return playersIds;
    }

    /**
     * Returns the list of player points in the game.
     *
     * @return the list of player points
     */
    public ArrayList<Integer> getPoints() {
        ArrayList<Integer> playerPoints = new ArrayList<>();
        for (Player player : players) {
            playerPoints.add(player.getTotalPoints());
        }
        return playerPoints;
    }

    /**
     * Returns the game status as a GameStatusToFile object.
     *
     * @return the game status as a GameStatusToFile object
     */
    public GameStatusToFile getGameStatusToFile() {
        ArrayList<Stack<Integer>> pointStacks = new ArrayList<>();
        pointStacks.add(this.commonGoalPointStacks[0].getPointStack());
        pointStacks.add(this.commonGoalPointStacks[1].getPointStack());
        String[] commonGoalPointStackNames = new String[2];
        commonGoalPointStackNames[0] = this.commonGoalPointStacks[0].getCommonGoal().getGoalName();
        commonGoalPointStackNames[1] = this.commonGoalPointStacks[0].getCommonGoal().getGoalName();

        return new GameStatusToFile(
                this.gameID,
                pointStacks,
                commonGoalPointStackNames,
                currentPlayer,
                this.getBoard(),
                this.gameState.getStateNumber(),
                getItemDeck(),
                getPersonalGoalDeck(),
                getMaxPlayers()
        );
    }

    /**
     * Sets the index of the current player in the game.
     *
     * @param currentPlayer the index of the current player
     */
    public void setCurrentPlayerIndex(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /* ************************************************************************************************************
     *                          END OF SETTER METHODS
     ************************************************************************************************************ */
}
