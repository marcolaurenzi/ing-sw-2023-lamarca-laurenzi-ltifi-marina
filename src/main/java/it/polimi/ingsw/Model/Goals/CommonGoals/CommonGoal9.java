package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents Common Goal number 9, which states that the goal is achieved when there are
 * two lines, each formed by 5 different types of tiles, in the Bookshelf. One line can show the same
 * or a different combination of the other line.
 */
public class CommonGoal9 extends CommonGoal {
    /**
     * Constructs a CommonGoal9 object.
     */
    public CommonGoal9() {

    }
    /**
     * Number of different types required to achieve the goal.
     */
    private static final int NUM_TYPES_REQUIRED = 5;

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

        // typesFound stores how many types the algorithm already found
        Set<TypeEnum> typesFound = new HashSet<>();

        // number of valid lines found
        int validLines = 0;

        if (bookshelf.isEmpty()) {
            return false;
        }

        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if (bookshelf.get(i, j) == null) {
                    break; // No point in checking that line, a full line of different types is needed
                } else {
                    typesFound.add(bookshelf.get(i, j).getType());
                }
            }
            if (typesFound.size() == NUM_TYPES_REQUIRED) {
                validLines++;
                typesFound.clear();
            }
        }

        return validLines >= 2;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal9";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Two lines each having no duplicate types.\nThe two lines may have the same combination of tiles.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "10";
    }
}
