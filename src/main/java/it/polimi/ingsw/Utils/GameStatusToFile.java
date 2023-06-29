package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Decks.PersonalGoalDeck;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class represents the game status to be saved to a file.
 * It contains information about the current state of the game.
 */
public class GameStatusToFile {
    /**
     * The ID of the game.
     */
    private final int gameID;
    /**
     * The stacks containing the points for each common goal.
     */
    private final ArrayList<Stack<Integer>> commonGoalPointStacks;
    /**
     * The names of the common goal point stacks.
     */
    private final String[] commonGoalPointStacksNames;
    /**
     * The index of the current player.
     */
    private final int currentPlayerIndex;
    /**
     * The game board.
     */
    private final Board board;
    /**
     * The state of the game.
     */
    private final int gameState;
    /**
     * The item deck.
     */
    private final ItemDeck itemDeck;
    /**
     * The personal goal deck.
     */
    private final PersonalGoalDeck personalGoalDeck;
    /**
     * The maximum number of players.
     */
    private final int maxPlayers;

    /**
     * Constructs a GameStatusToFile object with the specified parameters.
     *
     * @param gameID                   the ID of the game
     * @param commonGoalPointStacks    the stacks containing the points for each common goal
     * @param commonGoalPointStacksNames the names of the common goal point stacks
     * @param currentPlayerIndex      the index of the current player
     * @param board                   the game board
     * @param gameState               the state of the game
     * @param itemDeck                the item deck
     * @param personalGoalDeck        the personal goal deck
     * @param maxPlayers              the maximum number of players
     */
    public GameStatusToFile(int gameID, ArrayList<Stack<Integer>> commonGoalPointStacks, String[] commonGoalPointStacksNames, int currentPlayerIndex, Board board, int gameState, ItemDeck itemDeck, PersonalGoalDeck personalGoalDeck, int maxPlayers) {
        this.gameID = gameID;
        this.commonGoalPointStacks = commonGoalPointStacks;
        this.commonGoalPointStacksNames = commonGoalPointStacksNames;
        this.currentPlayerIndex = currentPlayerIndex;
        this.board = board;
        this.gameState = gameState;
        this.itemDeck = itemDeck;
        this.personalGoalDeck = personalGoalDeck;
        this.maxPlayers = maxPlayers;
    }

    /**
     * Returns the stacks containing the points for each common goal.
     *
     * @return the stacks containing the points for each common goal
     */
    public ArrayList<Stack<Integer>> getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

    /**
     * Returns the names of the common goal point stacks.
     *
     * @return the names of the common goal point stacks
     */
    public String[] getCommonGoalPointStacksNames() {
        return commonGoalPointStacksNames;
    }

    /**
     * Returns the ID of the game.
     *
     * @return the ID of the game
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Returns the index of the current player.
     *
     * @return the index of the current player
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
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
     * Returns the state of the game.
     *
     * @return the state of the game
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * Returns the item deck.
     *
     * @return the item deck
     */
    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    /**
     * Returns the personal goal deck.
     *
     * @return the personal goal deck
     */
    public PersonalGoalDeck getPersonalGoalDeck() {
        return personalGoalDeck;
    }

    /**
     * Returns the maximum number of players.
     *
     * @return the maximum number of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }
}
