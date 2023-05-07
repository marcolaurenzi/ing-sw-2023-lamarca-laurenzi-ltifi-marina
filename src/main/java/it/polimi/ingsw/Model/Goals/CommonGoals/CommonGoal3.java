package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Matrix;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal number 3
 * This goal is achieved if the board contains at least 2 of the following group of tiles.
 * Four tiles of the same Type representing a perfect square (boarding tiles MUST be of different Types)
 */
public class CommonGoal3 extends CommonGoal{

    /**
     * isAchieved() method checks whether the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true whether the Goals is Achieved and false otherwise
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
                if(bookshelf.get(i, j) != null && copy.get(i, j)) {
                    goodTypes.add(bookshelf.get(i, j).getType());
                    goodTypes.add(bookshelf.get(i+1, j).getType());
                    goodTypes.add(bookshelf.get(i, j+1).getType());
                    goodTypes.add(bookshelf.get(i+1, j+1).getType());

                    if(goodTypes.size() == 1) {
                        // if not in the right border
                        if(j < numberOfColumns - 2) {
                            badTypes.add(bookshelf.get(i, j+2).getType());
                            if(i < bookshelf.getColumnDimension() - 1) {
                                badTypes.add(bookshelf.get(i+1, j+2).getType());
                            }
                        }
                        // if not in the left border
                        if(j != 0) {
                            badTypes.add(bookshelf.get(i, j-1).getType());
                            if(i < numberOfRows - 1) {
                                badTypes.add(bookshelf.get(i+1, j-1).getType());
                            }
                        }
                        // if not in the down border
                        if(i < numberOfRows - 2) {
                            badTypes.add(bookshelf.get(i+2, j).getType());
                            if(j < numberOfColumns - 1)
                                badTypes.add(bookshelf.get(i+2, j+1).getType());
                        }
                        // if not in the upper border
                        if(i != 0) {
                            badTypes.add(bookshelf.get(i-1, j).getType());
                            if(j < numberOfColumns - 1) {
                                badTypes.add(bookshelf.get(i-1, j+1).getType());
                            }
                        }
                        if(!badTypes.containsAll(goodTypes)) {
                            count++;
                            copy.set(i+1, j+0, false);
                            copy.set(i+0, j+1, false);
                            copy.set(i+1, j+1, false);
                        }
                    }

                    copy.set(i+0, j+0, false);

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
    public void printGoalDescription() {
        System.out.println("Fill your Bookshelf with at least 2 perfect squares each composed by 4 tiles of the same color");
    }
}