package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal number 9 in the UML model.
 * Common Goal description: Two lines each formed by 5 different types of tiles.
 * One line can show the same or a different combination of the other line.
 */
public class CommonGoal9 extends CommonGoal {

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {

        // times stores how many colors the algorithm already found
        Set<TypeEnum> colorsFound = new HashSet<>();

        // number of valid columns found
        int validRows = 0;

        if(bookShelf.isEmpty()) {
            return false;
        }

        for(int i = 0; i<bookShelf.getRowDimension(); i++) {
            for(int j = 0; j < bookShelf.getColumnDimension(); j++) {
                if(bookShelf.get(i,j) == null) {
                    break; //no point in checking that row, a full row of different colors needed
                }
                else {
                    colorsFound.add(bookShelf.get(i,j).getType());
                }

            }
            if(colorsFound.size() == 5) {
                validRows++;
                colorsFound.clear();
            }
        }

        return validRows >= 2;
    }
}
