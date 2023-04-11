package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
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
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        if(bookshelf.isEmpty()) {
            return false;
        }

        // times stores how many types the algorithm already found
        Set<TypeEnum> times = new HashSet<>();

        // counters counts how many times the pattern is found
        int counter = 0;

        for(int i = 0; i < numberOfRows; i++) {
            for(int j = 0; j < numberOfColumns; j++) {
                if(bookshelf.get(i,j) == null) {
                    j = numberOfColumns + 1;
                    times.clear();
                }
                else {
                    times.add(bookshelf.get(i,j).getType());
                }

            }
            if(times.size() <= 3 && times.size() > 0) {
                counter++;
            }
            times.clear();
        }

        return counter >= 4;
    }
}
