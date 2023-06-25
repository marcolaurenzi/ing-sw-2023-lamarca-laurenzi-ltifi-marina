package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the status of the game to be sent to clients.
 * It contains information about the game state, player status, common goals, personal goals, and more.
 */
public class GameStatusToSend implements Serializable {
    private final int gameID;
    private final Integer[] commonGoalPointStacksTops;
    private final String[] commonGoalPointStacksNames;
    private final String[] commonGoalPointStacksDescriptions;
    private final boolean[] isCommonGoalAlreadyAchieved;
    private final PersonalGoal personalGoal;
    private final String currentPlayer;
    private final ArrayList<String> players;
    private final ArrayList<Integer> points;
    private final ArrayList<Bookshelf> bookshelves;
    private final Board board;
    private final boolean isLastTurn;

    /**
     * Constructs a GameStatusToSend object with the specified game state and player information.
     *
     * @param gameID                        the ID of the game
     * @param commonGoalPointStacksTops     the tops of the common goal point stacks
     * @param commonGoalPointStacksNames    the names of the common goal point stacks
     * @param commonGoalPointStacksDescriptions  the descriptions of the common goal point stacks
     * @param isCommonGoalAlreadyAchieved   an array indicating whether each common goal has been achieved
     * @param personalGoal                  the personal goal of the player
     * @param currentPlayer                 the current player's name
     * @param players                       the names of all players
     * @param points                        the points earned by each player
     * @param bookshelves                   the bookshelves of each player
     * @param board                         the game board
     * @param isLastTurn                    indicates whether it is the last turn of the game
     */
    public GameStatusToSend(int gameID, Integer[] commonGoalPointStacksTops, String[] commonGoalPointStacksNames, String[] commonGoalPointStacksDescriptions, boolean[] isCommonGoalAlreadyAchieved, PersonalGoal personalGoal,
                            String currentPlayer, ArrayList<String> players, ArrayList<Integer> points, ArrayList<Bookshelf> bookshelves, Board board, boolean isLastTurn) {
        this.gameID = gameID;
        this.commonGoalPointStacksTops = commonGoalPointStacksTops;
        this.commonGoalPointStacksNames = commonGoalPointStacksNames;
        this.commonGoalPointStacksDescriptions = commonGoalPointStacksDescriptions;
        this.isCommonGoalAlreadyAchieved = isCommonGoalAlreadyAchieved;
        this.personalGoal = personalGoal;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.points = points;
        this.bookshelves = bookshelves;
        this.board = board;
        this.isLastTurn = isLastTurn;
    }

    /**
     * Checks whether it is the last turn of the game.
     *
     * @return true if it is the last turn, false otherwise
     */
    public boolean isLastTurn() {
        return isLastTurn;
    }

    /**
     * Returns the tops of the common goal point stacks.
     *
     * @return the tops of the common goal point stacks
     */
    public Integer[] getCommonGoalPointStacksTops() {
        return commonGoalPointStacksTops;
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
     * Returns the descriptions of the common goal point stacks.
     *
     * @return the descriptions of the common goal point stacks
     */
    public String[] getCommonGoalPointStacksDescriptions() {
        return commonGoalPointStacksDescriptions;
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
     * Returns the personal goal of the player.
     *
     * @return the personal goal of the player
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * Returns the name of the current player.
     *
     * @return the name of the current player
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the names of all players.
     *
     * @return the names of all players
     */
    public ArrayList<String> getPlayers() {
        return players;
    }

    /**
     * Returns the points earned by each player.
     *
     * @return the points earned by each player
     */
    public ArrayList<Integer> getPoints() {
        return points;
    }

    /**
     * Returns the bookshelves of each player.
     *
     * @return the bookshelves of each player
     */
    public ArrayList<Bookshelf> getBookshelves() {
        return bookshelves;
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
     * Returns an array indicating whether each common goal has been achieved.
     *
     * @return an array indicating whether each common goal has been achieved
     */
    public boolean[] getIsCommonGoalAlreadyAchieved() {
        return isCommonGoalAlreadyAchieved;
    }

    /**
     * Returns the bookshelf at the specified index.
     *
     * @param index the index of the bookshelf
     * @return the bookshelf at the specified index
     */
    public Bookshelf getBookshelf(int index) {
        return bookshelves.get(index);
    }
}
