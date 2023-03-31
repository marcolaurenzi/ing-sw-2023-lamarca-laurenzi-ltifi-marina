package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Utils;

import java.util.*;

/**
 * This class represents the CommonGoal number 4
 * saying that a player must have, to achieve the goal, 3 different columns
 * of 6 consecutive Tiles with no more than 3 different colours in it
 */
public class CommonGoal4 extends CommonGoal {

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        // times stores how many types the algorithm already found
        Set<TypeEnum> times = new HashSet<>();

        // counters counts how many times the pattern is found
        int counter = 0;

        for(int i = 0; i < bookshelf.getRowDimension(); i++) {
            for(int j = 0; j < bookshelf.getColumnDimension(); j++) {
                // if the first tile is null, no 6 tiles columns can be there
                if(bookshelf.get(i,j) == null) {
                    break;
                }
                else{
                    times.add(bookshelf.get(i,j).getType());
                }

            }
            if(times.size() <= 3 && times.size() > 0) {
                counter++;
                times.clear();
            }
        }
        return counter >= 3;
    }
}
