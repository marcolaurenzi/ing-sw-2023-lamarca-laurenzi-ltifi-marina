package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Matrix;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal 3
 * This goal is achieved if the board contains at least 2 of the following group of tiles.
 * Four tiles of the same Type representing a perfect square (boarding tiles MUST be of different Types)
 */
public class CommonGoal3 extends CommonGoal{

    /**
     /**
     * Checks whether the goal is achieved or not
     * @param bookshelf is the reference to the actual BookShelf Object on which Algorithm operates
     * @return returns true if the Goal is Achieved, false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        Set<TypeEnum> goodTypes = new HashSet<>();
        Set<TypeEnum> badTypes = new HashSet<>();

        Matrix<Boolean> copy = new Matrix<>(numberOfRows, numberOfColumns, true);

        int count = 0;

        for(int i = 0; i < numberOfRows-1; i++) {
            for(int j = 0; j < numberOfColumns-1; j++) {
                if(bookshelf.get(i, j) != null &&
                        bookshelf.get(i+1, j) != null &&
                        bookshelf.get(i, j+1) != null &&
                        bookshelf.get(i+1, j+1) != null &&
                        copy.get(i, j)) {

                    goodTypes.add(bookshelf.get(i, j).getType());
                    goodTypes.add(bookshelf.get(i+1, j).getType());
                    goodTypes.add(bookshelf.get(i, j+1).getType());
                    goodTypes.add(bookshelf.get(i+1, j+1).getType());

                    if(goodTypes.size() == 1) {
                        // if not in the right border
                        if(j < numberOfColumns - 2 && bookshelf.get(i, j+2) != null) {
                            badTypes.add(bookshelf.get(i, j+2).getType());
                            if(i < bookshelf.getColumnDimension() - 1 && bookshelf.get(i+1, j+2) != null) {
                                badTypes.add(bookshelf.get(i+1, j+2).getType());
                            }
                        }
                        // if not in the left border
                        if(j != 0 && bookshelf.get(i, j-1) != null) {
                            badTypes.add(bookshelf.get(i, j-1).getType());
                            if(i < numberOfRows - 1 && bookshelf.get(i+1, j-1) != null) {
                                badTypes.add(bookshelf.get(i+1, j-1).getType());
                            }
                        }
                        // if not in the down border
                        if(i < numberOfRows - 2 && bookshelf.get(i+2, j) != null) {
                            badTypes.add(bookshelf.get(i+2, j).getType());
                            if(j < numberOfColumns - 1 && bookshelf.get(i+2, j+1) != null)
                                badTypes.add(bookshelf.get(i+2, j+1).getType());
                        }
                        // if not in the upper border
                        if(i != 0 && bookshelf.get(i-1, j) != null) {
                            badTypes.add(bookshelf.get(i-1, j).getType());
                            if(j < numberOfColumns - 1 && bookshelf.get(i-1, j+1) != null) {
                                badTypes.add(bookshelf.get(i-1, j+1).getType());
                            }
                        }
                        if(!badTypes.containsAll(goodTypes)) {
                            count++;
                            copy.set(i+1, j, false);
                            copy.set(i, j+1, false);
                            copy.set(i+1, j+1, false);
                        }
                    }

                    copy.set(i, j, false);

                    goodTypes.clear();
                    badTypes.clear();
                }
            }
        }
        return count >= 2;
    }
    public String getGoalName() {
        return "CommonGoal3";
    }

    @Override
    public String getGoalDescription() {
        return "At least two 2x2 squares of tiles of the same type.\nDifferent squares may have different types.";
    }

    @Override
    public String getGoalFileNumber() { return "4";}
}