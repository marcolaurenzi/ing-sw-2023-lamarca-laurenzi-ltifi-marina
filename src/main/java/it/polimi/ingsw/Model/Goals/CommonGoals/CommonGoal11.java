package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.ColorEnum;

/**
 * This class represents the CommonGoal number 11
 * This goal is achieved when there are 5 columns that progressively have one tile more than the previous one
 */
public class CommonGoal11 extends CommonGoal{
    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {
        boolean result = false;
        ColorEnum last;
        last = null;
        int count = 0;
        for (int a = 0; a < 2; a++) {
            count = 0;
            for (int i = 0; i < 5; i++) {
                if (last == null) {
                    last = bookShelf.get(i, i + a).getColor();
                }
                if (bookShelf.get(i, i + a).getColor() == null) {
                    break;
                }
                if (bookShelf.get(i, i + a) != null) {
                    count++;
                }
            }
            if (count == 5) {
                result = true;
                break;
            }
            count = 0;
            for (int i = 4; i >= 0; i--) {
                if (last == null) {
                    last = bookShelf.get(i, (4 - i) + a).getColor();
                }
                if (bookShelf.get(i, 4 - i + a).getColor() == null) {
                    break;
                }
                if (bookShelf.get(i, i + a).getColor() != null) {
                    count++;
                }
            }
            if (count == 5) {
                result = true;
                break;
            }
        }
        if (!result) {
            result = (count == 5);
        }
        return result;
    }
}
