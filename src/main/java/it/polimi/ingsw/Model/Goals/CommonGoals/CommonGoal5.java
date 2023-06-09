package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashMap;

/**
 * This class represents the CommonGoal 5
 * This goal is achieved when there are 8 elements of the same type in the whole Bookshelf
 */
public class CommonGoal5 extends CommonGoal{

    /**
     * Checks whether the goal is achieved or not
     * @param bookshelf is the reference to the actual BookShelf Object on which Algorithm operates
     * @return returns true if the Goal is Achieved, false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {
        boolean result = false;
        HashMap<TypeEnum, Integer> found = new HashMap<TypeEnum, Integer>();
        found.put(TypeEnum.FRAMES, 0);
        found.put(TypeEnum.CATS, 0);
        found.put(TypeEnum.GAMES, 0);
        found.put(TypeEnum.BOOKS, 0);
        found.put(TypeEnum.PLANTS, 0);
        found.put(TypeEnum.TROPHIES, 0);
        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if (bookshelf.get(i, j) == null) continue;
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
    public String getGoalName() {
        return "CommonGoal5";
    }

    @Override
    public String getGoalDescription() {
        return "Fill your Bookshelf with 8 Tiles of the same color";
    }

    @Override
    public String getGoalFileNumber() { return "6";}
}
