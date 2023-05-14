package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.CommonGoalPointStack;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStatus implements Serializable {
    final int gameID;
    private CommonGoalPointStack[] commonGoalPointStacks;
    private boolean[] isCommonGoalAlreadyAchieved;
    private PersonalGoal personalGoal;
    private String currentPlayer;
    private ArrayList<String> players;
    private ArrayList<Integer> points;
    private ArrayList<Bookshelf> bookshelves;
    private final Board board;
    private boolean isLastTurn;
    public GameStatus(int gameID, CommonGoalPointStack[] commonGoalPointStacks, boolean[] isCommonGoalAlreadyAchieved,PersonalGoal personalGoal,
                      String currentPlayer, ArrayList<String> players, ArrayList<Integer> points, ArrayList<Bookshelf> bookshelves, Board board, boolean isLastTurn) {
        this.gameID = gameID;
        this.commonGoalPointStacks = commonGoalPointStacks;
        this.isCommonGoalAlreadyAchieved = isCommonGoalAlreadyAchieved;
        this.personalGoal = personalGoal;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.points = points;
        this.bookshelves = bookshelves;
        this.board = board;
        this.isLastTurn = isLastTurn;
    }
    public boolean isLastTurn() {
        return isLastTurn;
    }

    public int getGameID() {
        return gameID;
    }

    public CommonGoalPointStack[] getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    public ArrayList<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public Board getBoard() {
        return board;
    }
    public boolean[] getIsCommonGoalAlreadyAchieved() {
        return isCommonGoalAlreadyAchieved;
    }
    public Bookshelf getBookshelf(int index) {
        return bookshelves.get(index);
    }

}
