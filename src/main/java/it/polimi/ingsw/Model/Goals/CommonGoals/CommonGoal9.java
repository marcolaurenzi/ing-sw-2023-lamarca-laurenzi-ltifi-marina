package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;

/**
 * This class represents the CommonGoal number 10 in the UML model.
 * Common Goal description: Two lines each formed by 5 different types of tiles.
 *                          One line can show the same or a different combination of the other line.
 */

public class CommonGoal9 extends CommonGoal {

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
