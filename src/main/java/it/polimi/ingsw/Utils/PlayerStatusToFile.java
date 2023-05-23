package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;
import it.polimi.ingsw.Model.PlayerState.PlayerState;

public class PlayerStatusToFile {
    private final String playerID;
    private final int totalPoints;
    private final int commonGoalPoints;
    private final int state;
    private final Bookshelf bookshelf;
    private final PersonalGoal personalGoal;
    private final boolean[] isCommonGoalAlreadyAchieved;

    public PlayerStatusToFile(String playerID, int totalPoints, int commonGoalPoints, int state, Bookshelf bookshelf, PersonalGoal personalGoal, boolean[] isCommonGoalAlreadyAchieved) {
        this.playerID = playerID;
        this.totalPoints = totalPoints;
        this.commonGoalPoints = commonGoalPoints;
        this.state = state;
        this.bookshelf = bookshelf;
        this.personalGoal = personalGoal;
        this.isCommonGoalAlreadyAchieved = isCommonGoalAlreadyAchieved;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getCommonGoalPoints() {
        return commonGoalPoints;
    }

    public int getState() {
        return state;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public boolean[] getIsCommonGoalAlreadyAchieved() {
        return isCommonGoalAlreadyAchieved;
    }
}
