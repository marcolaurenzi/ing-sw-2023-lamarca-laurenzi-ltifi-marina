package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStatus implements Serializable {
    private final int gameID;
    private final int[] commonGoalPointStacksTops;

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
    public GameStatus(int gameID, int[] commonGoalPointStacksTops, String[] commonGoalPointStacksNames, String[] commonGoalPointStacksDescriptions, boolean[] isCommonGoalAlreadyAchieved,PersonalGoal personalGoal,
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
    public boolean isLastTurn() {
        return isLastTurn;
    }
    public int[] getCommonGoalPointStacksTops() {
        return commonGoalPointStacksTops;
    }

    public String[] getCommonGoalPointStacksNames() {
        return commonGoalPointStacksNames;
    }

    public String[] getCommonGoalPointStacksDescriptions() {
        return commonGoalPointStacksDescriptions;
    }


    public int getGameID() {
        return gameID;
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

}
