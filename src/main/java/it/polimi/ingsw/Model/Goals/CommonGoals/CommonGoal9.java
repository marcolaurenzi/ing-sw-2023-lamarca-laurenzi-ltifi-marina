package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal number 9 in the UML model.
 * Common Goal description: Two lines each formed by 5 different types of tiles.
 * One line can show the same or a different combination of the other line.
 */
public class CommonGoal9 extends CommonGoal {
    private static final int NUM_TYPES_REQUIRED = 5;

    /**
     * isAchieved() method checks whether the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {
        // types stores how many types the algorithm already found
        Set<TypeEnum> typesFound = new HashSet<>();

        // number of valid lines found
        int validLines = 0;

        if (bookshelf.isEmpty()) {
            return false;
        }

        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if (bookshelf.get(i, j) == null) {
                    break; //no point in checking that line, a full line of different types needed
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
    public String getGoalName() {
        return "CommonGoal9";
    }

    @Override
    public void printGoalDescription() {
        System.out.println("Two rows each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.");
    }
}
