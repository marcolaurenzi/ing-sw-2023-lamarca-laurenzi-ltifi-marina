package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.*;

/**
 * This class represents the CommonGoal number 4
 * saying that a player must have, to achieve the goal, 3 different columns
 * of 6 consecutive Tiles with no more than 3 different colours in it
 */
public class CommonGoal4 extends CommonGoal {

    /**
     * Checks whether the goal is achieved or not
     * @param bookshelf is the reference to the actual BookShelf Object on which Algorithm operates
     * @return returns true if the Goal is Achieved, false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) throws NullPointerException{

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        if(bookshelf == null) {
            throw new NullPointerException();
        }

        // times stores how many types the algorithm already found
        Set<TypeEnum> times = new HashSet<>();

        // counters counts how many times the pattern is found
        int counter = 0;

        for(int i = 0; i < numberOfColumns; i++) {
            for(int j = 0; j < numberOfRows; j++) {
                // if the first tile is null, no 6 tiles columns can be there
                if(bookshelf.get(j,i) == null) {
                    break;
                }
                else{
                    times.add(bookshelf.get(j,i).getType());
                }

            }
            if(times.size() <= 3 && times.size() > 0) {
                counter++;
            }
            times.clear();
        }
        return counter >= 3;
    }
    public String getGoalName() {
        return "CommonGoal4";
    }

    @Override
    public String getGoalDescription() {
        return "Three full columns each containing tiles of three types at most.\nDifferent columns may have the same combination of tiles.";
    }

    @Override
    public String getGoalFileNumber() { return "5";}
}
