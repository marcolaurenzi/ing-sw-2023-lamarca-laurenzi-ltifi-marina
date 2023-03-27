package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;


/**
 * This class represents the CommonGoal number 7
 * (based on the official Rulebook file starting with number 0 from the top left corner and finishing with number 11 in the bottom right corner)
 * saying that a player must have, to achieve the goal, 4 different rows
 * of 6 consecutive Tiles with no more than 3 different colours in it
 */


public class CommonGoal7 extends CommonGoal {

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */

    @Override
    public boolean isAchieved(BookShelf bookShelf) {

        // times stores how many colors the algorithm already found
        Set<TypeEnum> times = new HashSet<>();

        // counters counts how many times the pattern is found
        int counter = 0;

        if(bookShelf.isEmpty()) {
            return false;
        }

        for(int i = 0; i<bookShelf.getRowDimension(); i++) {
            for(int j = 0; j < bookShelf.getColumnDimension(); j++) {
                if(bookShelf.get(i,j) == null) {
                    j++;
                }
                else {
                    times.add(bookShelf.get(i,j).getType());
                }

            }
            if(times.size() == 1) {
                counter++;
                times.clear();
            }
        }

        return counter >= 4;
    }
}
