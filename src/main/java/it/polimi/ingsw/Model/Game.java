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

public class Game {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */

    private GameState gameState;
    final int gameID;
    private ItemDeck itemDeck;
    private PersonalGoalDeck personalGoalDeck;
    private CommonGoalPointStack[] commonGoalPointStacks;
    private final int maxPlayers;
    private int currentPlayer;
    private final ArrayList<Player> players;
    private final Board board;

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */

    public Game(int gameId, int maxPlayers) throws MaxNumberOfPlayersException, IOException {
        if(maxPlayers > 4 || maxPlayers < 2) {
            throw new MaxNumberOfPlayersException();
        }
        gameState = new GameStateStarting();
        this.gameID = gameId;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
        board = Utils.loadBoardFromFile("src/main/resources/configurations/BoardConfiguration.JSON");
        board.setItemDeck(itemDeck);
        this.initializeGame();
    }

    //in case of retrieved status
    public Game(GameStatusToFile gameStatus, ArrayList<PlayerStatusToFile> playerStatus) {
        switch(gameStatus.getGameState()) {
            case 0 -> this.gameState = new GameStateFinished();
            case 1 -> this.gameState = new GameStateLastTurn();
            case 2 -> this.gameState = new GameStateRunning();
            case 3 -> this.gameState = new GameStateStarting();
        }

        this.gameID = gameStatus.getGameID();
        this.itemDeck = gameStatus.getItemDeck();
        this.personalGoalDeck = gameStatus.getPersonalGoalDeck();
        this.commonGoalPointStacks = new CommonGoalPointStack[2];

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

        this.maxPlayers = gameStatus.getMaxPlayers();
        this.currentPlayer = gameStatus.getCurrentPlayerIndex();
        this.board = gameStatus.getBoard();

        this.players = new ArrayList<>();
        for(PlayerStatusToFile p: playerStatus) {
            this.players.add(new Player(p, this));
        }
    }

    /* ************************************************************************************************************
     *                          END OF CONSTRUCTORS
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */

    /**
     * This method is called when a player joins the game and adds him to the list of players
     * after creating a new Player object
     * @param playerID the ID of the player to be added
     */
    public void addPlayer(String playerID) throws AlreadyStartedGameException {
        if(players.size() == maxPlayers) {
            throw new AlreadyStartedGameException();
        }
        players.add(new Player(playerID, this));
    }

    /**
     * This method changes the current player to the next one, is called at the end of each turn, and
     * it depends on the state of the game
     */
    public void nextPlayer() throws FinishedGameException, GameNotStartedException { gameState.nextPlayer(this, players); }

    /**
     * This method initializes the board of the game, it is called only once at the beginning of the game by the
     * initializeGame method
     */
    public void initializeBoard(){
        board.initializeBoard(this);
    }

    /**
     * This method initializes the decks of the game,
     * and it is called only once at the beginning of the game
     * by the initializeGame method
     *
     * @throws IOException if the file is not found
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
     * This method initializes the game, it is called only once at the beginning of the game by the constructor,
     * and it calls the methods to initialize the decks and the board
     * @throws IOException if the file is not found
     */
    public void initializeGame() throws IOException {
        this.initializeDeck();
        this.initializeBoard();
    }

    /**
     * This method is called when the game starts and sets the game state to running
     */
    public void startGame() {
        setGameState(new GameStateRunning());
    }

    /**
     * This method is called every round and checks if the board needs to be refreshed, if so it refreshes it
     * otherwise it does nothing
     */
    public void refreshBoard() {
        if(board.toRefresh()) {
            board.refreshBoard(this);
        }
    }

    public void nextTurn() throws FinishedGameException, GameNotStartedException {
        gameState.nextPlayer(this, players);
    }
    public void removePlayer(String playerID) throws MissingPlayerException {
        for(Player player : players) {
            if(player.getPlayerID().equals(playerID)) {
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

    public GameState getGameState() {
        return gameState;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    public PersonalGoalDeck getPersonalGoalDeck() {
        return personalGoalDeck;
    }

    public CommonGoalPointStack[] getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }
    public int getMaxPlayers(){
        return maxPlayers;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public Board getBoard() {
        return board;
    }
    public int getId() {
        return gameID;
    }

    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     *                          START OF SETTER METHODS
     ************************************************************************************************************ */

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public ArrayList<Bookshelf> getBookshelves(){
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        for (Player player : players) {
            bookshelves.add(player.getBookshelf());
        }
        return bookshelves;
    }
    public PersonalGoal getPersonalGoal(String playerId) throws MissingPlayerException {
        for(Player player : players) {
            if(player.getPlayerID().equals(playerId)) {
                return player.getPersonalGoal();
            }
        }
        throw new MissingPlayerException();
    }
    public boolean[] getIsCommonGoalAchieved(String playerId) throws MissingPlayerException {
        for(Player player : players) {
            if(player.getPlayerID().equals(playerId)) {
                return player.getIsCommonGoalAlreadyAchieved();
            }
        }
        throw new MissingPlayerException();
    }
    public ArrayList<String> getPlayersId() {
        ArrayList<String> playersIds = new ArrayList<>();
        for(Player player : players) {
            playersIds.add(player.getPlayerID());
        }
        return playersIds;
    }

    public ArrayList<Integer> getPoints() {
        ArrayList<Integer> playerPoints = new ArrayList<>();
        for(Player player : players) {
            playerPoints.add(player.getTotalPoints());
        }
        return playerPoints;
    }

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

    public void setCurrentPlayerIndex(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    /* ************************************************************************************************************
     *                          END OF SETTER METHODS
     ************************************************************************************************************ */
}
