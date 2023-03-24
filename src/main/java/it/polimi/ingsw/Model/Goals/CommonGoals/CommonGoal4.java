package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.ColorEnum;
import java.util.*;

/**
 * This class represents the CommonGoal number 4
 * (based on the official Rulebook file starting with number 0 from the top left corner and finishing with number 11 in the bottom right corner)
 * saying that a player must have, to achieve the goal, 3 different columns
 * of 6 consecutive Tiles with no more than 3 different colours in it
 */


public class CommonGoal4 extends CommonGoal {

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */

    @Override
    public boolean isAchieved(BookShelf bookShelf) {

        // times stores how many colors the algorithm already found
        Set<ColorEnum> times = new HashSet<>();

        // counters counts how many times the pattern is found
        int counter = 0;

        for(int i = 0; i < bookShelf.getColumnDimension(); i++) {
            for(int j = 0; j < bookShelf.getRowDimension(); j++) {
                // if the first tile is null, no 6 tiles columns can be there
                if(bookShelf.get(i,j) == null) {
                    i = 0;
                    j ++;
                }
                else{
                    times.add(bookShelf.get(i,j).getColor());
                }

            }
            if(times.size() <= 3) {
                counter++;
                times.clear();
            }
        }
        return counter >= 3;
    }
}
