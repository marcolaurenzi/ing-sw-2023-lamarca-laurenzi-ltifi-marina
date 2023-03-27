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
        int count = 0;
        if (bookShelf.get(0, 4) != null) {
            for (int i = 0; i < 5; i++) {
                if ((4 - i - 1) >= 0 && bookShelf.get(i, 4 - i - 1) != null) {
                    break;
                }
                if (bookShelf.get(i, 4 - i) == null) {
                    break;
                } else if (bookShelf.get(i, 4 - i) != null) {
                    count++;
                }
            }
            System.out.println(count);
            if (count == 5) {
                return true;
            }
        } else if (bookShelf.get(1, 4) != null) {
            for (int i = 1; i < 6; i++) {
                if ((5 - i - 1) >= 0 && bookShelf.get(i, 5 - i - 1) != null) {
                    break;
                }
                if (bookShelf.get(i, 5 - i) == null) {
                    break;
                } else if (bookShelf.get(i, 5 - i) != null) {
                    count++;
                }
            }
            if (count == 5) {
                return true;
            }
        }
        if (bookShelf.get(0,0) != null){
            for (int i = 0; i < 5; i++) {
                if ((i + 1) < 5 && bookShelf.get(i, i + 1) != null) {
                    break;
                }
                if (bookShelf.get(i, i) == null) {
                    break;
                } else if (bookShelf.get(i, i) != null) {
                    count++;
                }
            }
            if (count == 5) {
                return true;
            }
        } else if (bookShelf.get(1,0) != null) {
            for (int i = 1; i < 6; i++) {
                if ((i) < 5 && bookShelf.get(i, i) != null) {
                    break;
                }
                if (bookShelf.get(i, i - 1) == null) {
                    break;
                } else if (bookShelf.get(i, i - 1) != null) {
                    count++;
                }
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }
}
