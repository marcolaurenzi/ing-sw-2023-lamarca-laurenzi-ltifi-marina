package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;
import java.util.HashMap;

/**
 * This class represents Common Goal number 5, which is achieved when there are 8 elements of the same type
 * in the whole Bookshelf.
 */
public class CommonGoal5 extends CommonGoal {
    /**
     * Constructs a CommonGoal5 object.
     */
    public CommonGoal5() {

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

        boolean result = false;
        HashMap<TypeEnum, Integer> found = new HashMap<>();
        found.put(TypeEnum.FRAMES, 0);
        found.put(TypeEnum.CATS, 0);
        found.put(TypeEnum.GAMES, 0);
        found.put(TypeEnum.BOOKS, 0);
        found.put(TypeEnum.PLANTS, 0);
        found.put(TypeEnum.TROPHIES, 0);
        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if (bookshelf.get(i, j) == null) {
                    continue;
                }
                found.put(bookshelf.get(i, j).getType(), found.get(bookshelf.get(i, j).getType()) + 1);
            }
        }
        for (Integer value : found.values()) {
            if (value > 7) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal5";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Eight tiles of the same type in the whole bookshelf.\nNo restrictions on the position of the tiles.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "6";
    }
}
