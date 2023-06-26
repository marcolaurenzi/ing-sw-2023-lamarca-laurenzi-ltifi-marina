package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.*;

/**
 * This class represents Common Goal number 4, which states that a player must have three different columns
 * of six consecutive tiles with no more than three different colors in each column.
 */
public class CommonGoal4 extends CommonGoal {
    /**
     * Constructs a CommonGoal4 object.
     */
    public CommonGoal4() {

    }

    /**
     * Checks whether there are at least three completely filled columns each containing tiles of three types at most.
     *
     * @param bookshelf The reference to the actual Bookshelf object on which the algorithm operates.
     * @return True if the goal is achieved, false otherwise.
     * @throws NullPointerException If the bookshelf reference is null.
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) throws NullPointerException {

        if (bookshelf == null) {
            throw new NullPointerException();
        }

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        // times stores how many types the algorithm already found
        Set<TypeEnum> times = new HashSet<>();

        // counter counts how many times the pattern is found
        int counter = 0;

        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                // If the first tile is null, no 6-tile columns can exist
                if (bookshelf.get(j, i) == null) {
                    break;
                } else {
                    times.add(bookshelf.get(j, i).getType());
                }
            }
            if (times.size() <= 3 && times.size() > 0) {
                counter++;
            }
            times.clear();
        }
        return counter >= 3;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal4";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Three full columns each containing tiles of three types at most.\nDifferent columns may have the same combination of tiles.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "5";
    }
}
