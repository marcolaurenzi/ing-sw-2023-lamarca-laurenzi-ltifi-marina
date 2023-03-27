package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.Matrix;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the CommonGoal number 3
 * This goal is achieved if the board contains at least 2 of the following group of tiles.
 * Four tiles of the same Type representing a perfect square (boarding tiles MUST be of different Types)
 */
public class CommonGoal3 extends CommonGoal{

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {

        Set<TypeEnum> temp = new HashSet<>();
        Matrix<Boolean> copy = new Matrix<>(6,5, true);

        int count = 0;

        for(int i = 0; i < bookShelf.getRowDimension(); i++) {
            for(int j = 0; j < bookShelf.getColumnDimension(); j++) {
                if(copy.get(i, j)) {
                    temp.add(bookShelf.get(i, j).getType());
                    temp.add(bookShelf.get(i+1, j).getType());
                    temp.add(bookShelf.get(i, j+1).getType());
                    temp.add(bookShelf.get(i+1, j+1).getType());

                    if(temp.size() == 1) {
                        count++;
                    }
                    copy.set(i, j, false);
                    copy.set(i+1, j, false);
                    copy.set(i, j+1, false);
                    copy.set(i+1, j+1, false);
                    temp.clear();
                }
            }
        }
        return count >= 2;
    }
}