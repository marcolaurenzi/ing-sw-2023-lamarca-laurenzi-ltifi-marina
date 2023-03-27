package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;

/**
 * This class is an abstract class representing all the
 * CommonGoals the game has, and implements the Goal interface
 * that gives it the isAchieved() method to check whether the
 * goal is achieved or not
 */
public abstract class CommonGoal {

    /**
     *
     * @param bookShelf
     * @return
     */
    public boolean isAchieved(BookShelf bookShelf) {
        return false;
    }

    /**
     *
     * @return
     */
    public CommonGoal getCommonGoal(){
        return this;
    }
}
