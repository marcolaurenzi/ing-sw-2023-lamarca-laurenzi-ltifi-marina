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
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        Set<TypeEnum> goodTypes = new HashSet<>();
        Set<TypeEnum> badTypes = new HashSet<>();

        Matrix<Boolean> copy = new Matrix<>(6,5, true);

        int count = 0;

        for(int i = 0; i < bookshelf.getColumnDimension()-1; i++) {
            for(int j = 0; j < bookshelf.getRowDimension()-1; j++) {
                if(bookshelf.get(i, j) != null && copy.get(i, j)) {
                    goodTypes.add(bookshelf.get(i, j).getType());
                    goodTypes.add(bookshelf.get(i+1, j).getType());
                    goodTypes.add(bookshelf.get(i, j+1).getType());
                    goodTypes.add(bookshelf.get(i+1, j+1).getType());

                    if(goodTypes.size() == 1) {
                        if(!(bookshelf.getRowDimension() - j < 3)) {
                            badTypes.add(bookshelf.get(i, j+2).getType());
                            badTypes.add(bookshelf.get(i+1, j+2).getType());
                        }
                        if(!(bookshelf.getRowDimension() - j == bookshelf.getRowDimension())) {
                            badTypes.add(bookshelf.get(i, j-1).getType());
                            badTypes.add(bookshelf.get(i+1, j-1).getType());
                        }
                        if(!(bookshelf.getColumnDimension() - i < 3)) {
                            badTypes.add(bookshelf.get(i+1, j).getType());
                            badTypes.add(bookshelf.get(i+1, j+1).getType());
                        }
                        if(!(bookshelf.getColumnDimension() - i == bookshelf.getColumnDimension())) {
                            badTypes.add(bookshelf.get(i-1, j).getType());
                            badTypes.add(bookshelf.get(i-1, j+1).getType());
                        }
                        if(!badTypes.containsAll(goodTypes)) {
                            count++;
                        }
                    }

                    copy.set(i, j, false);
                    copy.set(i+1, j, false);
                    copy.set(i, j+1, false);
                    copy.set(i+1, j+1, false);

                    goodTypes.clear();
                    badTypes.clear();
                }
            }
        }
        return count >= 2;
    }

    /**
     * This method returns how many times the pattern is found.
     * This method is used in the testing phase
     *
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    public int numberOfTimes(Bookshelf bookshelf) {
        Set<TypeEnum> goodTypes = new HashSet<>();
        Set<TypeEnum> badTypes = new HashSet<>();

        Matrix<Boolean> copy = new Matrix<>(6,5, true);

        int count = 0;

        for(int i = 0; i < bookshelf.getColumnDimension()-1; i++) {
            for(int j = 0; j < bookshelf.getRowDimension()-1; j++) {
                if(bookshelf.get(i, j) != null && copy.get(i, j)) {
                    goodTypes.add(bookshelf.get(i, j).getType());
                    goodTypes.add(bookshelf.get(i+1, j).getType());
                    goodTypes.add(bookshelf.get(i, j+1).getType());
                    goodTypes.add(bookshelf.get(i+1, j+1).getType());

                    if(goodTypes.size() == 1) {
                        if(!(bookshelf.getRowDimension() - j < 3)) {
                            badTypes.add(bookshelf.get(i, j+2).getType());
                            badTypes.add(bookshelf.get(i+1, j+2).getType());
                        }
                        if(!(bookshelf.getRowDimension() - j == bookshelf.getRowDimension())) {
                            badTypes.add(bookshelf.get(i, j-1).getType());
                            badTypes.add(bookshelf.get(i+1, j-1).getType());
                        }
                        if(!(bookshelf.getColumnDimension() - i < 3)) {
                            badTypes.add(bookshelf.get(i+1, j).getType());
                            badTypes.add(bookshelf.get(i+1, j+1).getType());
                        }
                        if(!(bookshelf.getColumnDimension() - i == bookshelf.getColumnDimension())) {
                            badTypes.add(bookshelf.get(i-1, j).getType());
                            badTypes.add(bookshelf.get(i-1, j+1).getType());
                        }
                        if(!badTypes.containsAll(goodTypes)) {
                            count++;
                        }
                    }

                    copy.set(i, j, false);
                    copy.set(i+1, j, false);
                    copy.set(i, j+1, false);
                    copy.set(i+1, j+1, false);

                    goodTypes.clear();
                    badTypes.clear();
                }
            }
        }
        return count;
    }
}