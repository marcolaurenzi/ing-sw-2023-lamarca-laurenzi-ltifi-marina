package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Matrix;

/**
 * This class represents the CommonGoal number 0 and 1
 * Common Goal description: numberOfGroups groups each containing at least dim tiles of the same type.
 * the tiles of one group can be different from those of another group
 */
public class CommonGoal01 extends CommonGoal {
    private final int dim;
    private final int numberOfGroups;

    public CommonGoal01(int dim, int numberOfGroups) {
        this.dim = dim;
        this.numberOfGroups = numberOfGroups;
    }

    /**
     * This is a utility recursive method that spreads the false value in supportMatrix starting from [i][j]
     * the algorithm is trivial: the current cell is set to false and then the method is called to the adjacent
     * cell if and only if:
     * the adjacent cell is the same color of the current cell AND the adjacent cell is set to true
     * @param i x coordinate
     * @param j y coordinate
     * @param bookshelf bookShelf
     * @param supportMatrix supportMatrix
     * @param color color
     */
    private void spreadFalse(int i, int j, Bookshelf bookshelf, Matrix<Boolean> supportMatrix, TypeEnum color) {
        supportMatrix.set(i, j, false);

        if(i + 1 < supportMatrix.getColumnDimension() && bookshelf.get(i + 1, j) != null)
            if(bookshelf.get(i + 1, j).getType() == color && supportMatrix.get(i + 1, j))
                spreadFalse(i +1, j, bookshelf, supportMatrix, color);

        if(i - 1 >= 0 && bookshelf.get(i - 1, j) != null)
            if(bookshelf.get(i - 1, j).getType() == color && supportMatrix.get(i - 1, j))
                spreadFalse(i - 1, j, bookshelf, supportMatrix, color);

        if(j + 1 < supportMatrix.getRowDimension() && bookshelf.get(i, j + 1)!= null)
            if(bookshelf.get(i, j + 1).getType() == color && supportMatrix.get(i, j + 1))
                spreadFalse(i, j + 1, bookshelf, supportMatrix, color);

        if(j - 1 >= 0 && bookshelf.get(i, j - 1) != null)
            if(bookshelf.get(i, j - 1).getType() == color && supportMatrix.get(i, j - 1))
                spreadFalse(i, j - 1, bookshelf, supportMatrix, color);
    }

    /**
     * This method checks if the goal is achieved
     * @param bookshelf bookShelf
     * @return the return is true is the goal is achieved is false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {
        Matrix<Boolean> supportMatrix;

        //initialize supportMatrix:
        supportMatrix = new Matrix<>(6, 5);
        for (int i = 0; i < supportMatrix.getColumnDimension(); i++)
            for (int j = 0; j < supportMatrix.getRowDimension(); j++)
                supportMatrix.set(i, j, true);


        int groupCounter = 0;
        //algorithm
        for (int i = 0; i < bookshelf.getColumnDimension(); i++)
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {

                if(bookshelf.get(i, j) != null) {
                    TypeEnum currentColor = bookshelf.get(i, j).getType();


                    if (i < supportMatrix.getColumnDimension() - (dim - 1)) {
                        int tempCounter = 0;
                        for (int k = 0; k < dim; k++) {
                            if (bookshelf.get(i+ k, j) == null)
                                break;
                            else if (supportMatrix.get(i + k, j) && bookshelf.get(i + k, j).getType() == currentColor) {
                                tempCounter++;
                            } else
                                break;
                        }

                        if (tempCounter == dim) {
                            groupCounter++;
                            spreadFalse(i, j, bookshelf, supportMatrix, currentColor);
                        }
                    }

                    if (j < supportMatrix.getRowDimension() - (dim - 1)) {
                        int tempCounter = 0;
                        for (int k = 0; k < dim; k++) {
                            if (bookshelf.get(i, j + k) == null)
                                break;
                            if (supportMatrix.get(i, j + k) && bookshelf.get(i, j + k).getType() == currentColor) {
                                tempCounter++;
                            } else
                                break;
                        }
                        if (tempCounter == dim) {
                            groupCounter++;
                            spreadFalse(i, j, bookshelf, supportMatrix, currentColor);
                        }
                    }

                    supportMatrix.set(i, j, false);
                }
            }

        return groupCounter >= numberOfGroups;
    }

    /**
     * This method prints the goal
     * @return the string to be printed
     */
    @Override
    public String printGoal() {
        if(dim == 2){
            return "CommonGoal0";
        }
        return "CommonGoal1";
    }

}