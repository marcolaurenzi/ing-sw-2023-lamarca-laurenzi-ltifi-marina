package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.ColorEnum;

import java.util.HashMap;
/**
 * This class represents the CommonGoal number 5
 * This goal is achieved when there are 8 elements of the same type in the whole Bookshelf
 */
public class CommonGoal5 extends CommonGoal{
    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {
        boolean result = false;
        HashMap<ColorEnum, Integer> found = new HashMap<ColorEnum, Integer>();
        found.put(ColorEnum.BLUE, 0);
        found.put(ColorEnum.GREEN, 0);
        found.put(ColorEnum.YELLOW, 0);
        found.put(ColorEnum.WHITE, 0);
        found.put(ColorEnum.PINK, 0);
        found.put(ColorEnum.LIGHTBLUE, 0);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (bookShelf.get(i, j) == null) continue;
                found.put(bookShelf.get(i, j).getColor(), found.get(bookShelf.get(i, j).getColor()) + 1);
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
}
