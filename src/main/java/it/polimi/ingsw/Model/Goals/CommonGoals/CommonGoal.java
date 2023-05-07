package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

import java.io.Serializable;

/**
 * This class is an abstract class representing all the
 * CommonGoals the game has, and implements the Goal interface
 * that gives it the isAchieved() method to check whether the
 * goal is achieved or not.
 * (based on the official ENG Rulebook file starting with number 0 from the top left corner
 * and finishing with number 11 in the bottom right corner)
 */
public abstract class CommonGoal implements Serializable {

    /**
     * This method is used to check whether the goal is achieved or not
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     * @return the method returns true whether the Goal is Achieved and false otherwise
     */
    public abstract boolean isAchieved(Bookshelf bookshelf);

    /**
     * This method is used to print the name of the goal
     * @return
     */
    public abstract String getGoalName();
    public abstract void printGoalDescription();
}
