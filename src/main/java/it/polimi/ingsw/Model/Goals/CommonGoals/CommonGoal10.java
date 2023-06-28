package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents Common Goal number 10, which states that the goal is achieved when there are
 * five tiles of the same color forming an X in the Bookshelf.
 */
public class CommonGoal10 extends CommonGoal {
    /**
     * Constructs a CommonGoal10 object.
     */
    public CommonGoal10() {

    }

    /**
     * Checks whether the goal is achieved or not.
     *
     * @param bookshelf The reference to the actual Bookshelf object on which the algorithm operates.
     * @return True if the goal is achieved, false otherwise.
     * @throws NullPointerException If the bookshelf reference is null.
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        if (bookshelf == null) {
            throw new NullPointerException();
        }

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        List<TypeEnum> temp = new ArrayList<>();
        int count = 0;
        TypeEnum currTypeEnum;

        for(int i = 0; i < numberOfRows-2; i++) {
            for(int j = 0; j < numberOfColumns-2; j++) {

                // check if an X is possible from the current tile
                if(bookshelf.get(i,j) != null && bookshelf.get(i+2,j) != null &&
                        bookshelf.get(i+1,j+1) != null && bookshelf.get(i,j+2) != null &&
                        bookshelf.get(i+2,j+2) != null) {
                    currTypeEnum = bookshelf.get(i,j).getType();

                    // if the X is possible, check if it is formed
                    if(bookshelf.get(i,j).getType() == currTypeEnum &&
                            bookshelf.get(i+2,j).getType() == currTypeEnum &&
                            bookshelf.get(i+2,j+2).getType() == currTypeEnum &&
                            bookshelf.get(i,j+2).getType() == currTypeEnum &&
                            bookshelf.get(i+1,j+1).getType() == currTypeEnum){

                        if(bookshelf.get(i+1,j) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i+1,j).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i+1,j).getType());
                            }
                        }
                        if(bookshelf.get(i,j+1) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i,j+1).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i,j+1).getType());
                            }
                        }
                        if(bookshelf.get(i+2,j+1) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i+2,j+1).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i+2,j+1).getType());
                            }
                        }
                        if(bookshelf.get(i+1,j+2) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i+1,j+2).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i+1,j+2).getType());
                            }
                        }

                        if(count + temp.size() == 4) {
                            return true;
                        }
                        count=0;
                        temp.clear();
                    }
                }

            }
        }
        return false;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal10";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Five tiles of the same color forming an X.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "11";
    }
}
