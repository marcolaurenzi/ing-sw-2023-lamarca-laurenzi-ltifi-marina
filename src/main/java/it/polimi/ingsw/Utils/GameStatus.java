package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.CommonGoalPointStack;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.util.ArrayList;

public class GameStatus {
    final int gameID;
    private CommonGoalPointStack[] commonGoalPointStacks;
    private PersonalGoal personalGoal;
    private int currentPlayer;
    private ArrayList<String> players;
    private ArrayList<Integer> points;
    private ArrayList<Bookshelf> bookshelves;
    private final Board board;

    public GameStatus(int gameID, CommonGoalPointStack[] commonGoalPointStacks, PersonalGoal personalGoal,
                      int currentPlayer, ArrayList<String> players, ArrayList<Bookshelf> bookshelves, Board board) {
        this.gameID = gameID;
        this.commonGoalPointStacks = commonGoalPointStacks;
        this.personalGoal = personalGoal;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.bookshelves = bookshelves;
        this.board = board;
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

    public int getCurrentPlayer() {
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
}
