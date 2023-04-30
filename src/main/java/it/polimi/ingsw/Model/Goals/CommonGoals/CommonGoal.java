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

    boolean achieved;

    /**
     *
     * @param bookshelf
     * @return
     */
    public boolean isAchieved(Bookshelf bookshelf) {
        return false;
    }

    /**
     *
     * @return
     */
    public CommonGoal getCommonGoal(){
        return this;
    }
    public abstract String printGoal();
}
