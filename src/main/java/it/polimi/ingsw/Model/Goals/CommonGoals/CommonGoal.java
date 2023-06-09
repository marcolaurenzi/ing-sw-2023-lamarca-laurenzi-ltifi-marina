package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

import java.io.Serializable;

/**
 * This abstract class represents all the
 * CommonGoals in the game and implements the Goal interface
 * with the isAchieved() method to check whether the
 * goal is achieved or not.
 * (based on the official ENG Rulebook file starting with number 0 from the top left corner
 * and finishing with number 11 in the bottom right corner)
 */
public abstract class CommonGoal implements Serializable {
    private String goalName;
    private String goalDescription;

    /**
     * Checks whether the goal is achieved or not
     * @param bookshelf is the reference to the actual BookShelf Object on which Algorithm operates
     * @return returns true if the Goal is Achieved, false otherwise
     */
    public abstract boolean isAchieved(Bookshelf bookshelf);
    public abstract String getGoalName();
    public abstract String getGoalDescription();

    public abstract String getGoalFileNumber();
}
