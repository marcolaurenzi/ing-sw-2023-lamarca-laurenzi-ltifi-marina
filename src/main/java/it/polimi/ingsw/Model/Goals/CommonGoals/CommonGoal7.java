package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents Common Goal number 7, which states that a player must have four different rows
 * of five consecutive tiles with no more than three different colors in each row.
 */
public class CommonGoal7 extends CommonGoal {
    /**
     * Constructs a CommonGoal7 object.
     */
    public CommonGoal7() {

    }
    /**
     * Checks whether the goal is achieved or not.
     *
     * @param bookshelf The reference to the actual Bookshelf object on which the algorithm operates.
     * @return True if the goal is achieved, false otherwise.
     * @throws NullPointerException If the bookshelf reference is null.
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        if (bookshelf == null) {
            throw new NullPointerException();
        }

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        if (bookshelf.isEmpty()) {
            return false;
        }

        // times stores how many types the algorithm already found
        Set<TypeEnum> times = new HashSet<>();

        // counter counts how many times the pattern is found
        int counter = 0;

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (bookshelf.get(i, j) == null) {
                    j = numberOfColumns + 1;
                    times.clear();
                } else {
                    times.add(bookshelf.get(i, j).getType());
                }
            }
            if (times.size() <= 3 && times.size() > 0) {
                counter++;
            }
            times.clear();
        }

        return counter >= 4;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal7";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Four full lines each containing tiles of three types at most.\nDifferent lines may have the same combination of tiles.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "8";
    }
}
