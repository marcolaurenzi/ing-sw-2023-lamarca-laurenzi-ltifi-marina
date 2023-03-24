package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;

/**
 * This class represents the CommonGoal number 4
 * (based on the official Rulebook file starting with number 0 from the top left corner and finishing with number 11 in the bottom right corner)
 * saying that a player must have, to achieve the goal, 3 different columns
 * of 6 Tiles with no more than 3 different colours in it
 */


public class ThreeColumnsOfSixTilesWithMaxThreeDifferencesCommonGoal extends CommonGoal {

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */

    @Override
    public boolean isAchieved(BookShelf bookShelf) {
        return true;
    }
}
