package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

import java.io.Serializable;

/**
 * This abstract class represents all the Common Goals in the game and implements the Goal interface
 * with the isAchieved() method to check whether the goal is achieved or not.
 * (based on the official ENG Rulebook file starting with number 0 from the top left corner
 * and finishing with number 11 in the bottom right corner)
 */
public abstract class CommonGoal implements Serializable {
    /**
     * Checks whether the goal is achieved or not.
     *
     * @param bookshelf The reference to the actual Bookshelf object on which the algorithm operates.
     * @return True if the goal is achieved, false otherwise.
     */
    public abstract boolean isAchieved(Bookshelf bookshelf);

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public abstract String getGoalName();

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    public abstract String getGoalDescription();

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    public abstract String getGoalFileNumber();
}
