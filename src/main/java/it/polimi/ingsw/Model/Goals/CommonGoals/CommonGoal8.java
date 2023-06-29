package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents Common Goal number 8, which states that the goal is achieved when there are
 * two columns, each formed by 6 different types of tiles, in the Bookshelf.
 */
public class CommonGoal8 extends CommonGoal {
    /**
     * Constructs a CommonGoal8 object.
     */
    public CommonGoal8() {

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

        // typesFound stores how many types the algorithm already found
        Set<TypeEnum> typesFound = new HashSet<>();

        // number of valid columns found
        int validColumns = 0;

        if (bookshelf.isEmpty()) {
            return false;
        }

        for (int i = 0; i < bookshelf.getRowDimension(); i++) {
            for (int j = 0; j < bookshelf.getColumnDimension(); j++) {
                if (bookshelf.get(j, i) == null) {
                    break; // No point in checking that column, a full column of different types is needed
                } else {
                    typesFound.add(bookshelf.get(j, i).getType());
                }
            }
            if (typesFound.size() == 6) {
                validColumns++;
                typesFound.clear();
            }
        }

        return validColumns >= 2;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal8";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Two columns each having no duplicate types.\nThe two columns may have the same combination of tiles.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "9";
    }
}
