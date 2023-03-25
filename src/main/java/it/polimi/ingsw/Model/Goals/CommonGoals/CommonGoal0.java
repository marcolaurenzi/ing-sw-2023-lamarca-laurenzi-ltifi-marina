package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.ColorEnum;
import it.polimi.ingsw.Model.Matrix;

public class CommonGoal0 extends CommonGoal {
    private final int dim;
    private final int numberOfGroups;

    public CommonGoal0(int dim, int numberOfGroups) {
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
     * @param bookShelf bookShelf
     * @param supportMatrix supportMatrix
     * @param color color
     */
    private void spreadFalse(int i, int j, BookShelf bookShelf, Matrix<Boolean> supportMatrix, ColorEnum color) {
        supportMatrix.set(i, j, false);

        if(i + 1 < supportMatrix.getColumnDimension() && bookShelf.get(i + 1, j) != null)
            if(bookShelf.get(i + 1, j).getColor() == color && supportMatrix.get(i + 1, j))
                spreadFalse(i +1, j, bookShelf, supportMatrix, color);

        if(i - 1 >= 0 && bookShelf.get(i - 1, j) != null)
            if(bookShelf.get(i - 1, j).getColor() == color && supportMatrix.get(i - 1, j))
                spreadFalse(i - 1, j, bookShelf, supportMatrix, color);

        if(j + 1 < supportMatrix.getRowDimension() && bookShelf.get(i, j + 1)!= null)
            if(bookShelf.get(i, j + 1).getColor() == color && supportMatrix.get(i, j + 1))
                spreadFalse(i, j + 1, bookShelf, supportMatrix, color);

        if(j - 1 >= 0 && bookShelf.get(i, j - 1) != null)
            if(bookShelf.get(i, j - 1).getColor() == color && supportMatrix.get(i, j - 1))
                spreadFalse(i, j - 1, bookShelf, supportMatrix, color);
    }

    /**
     * @param bookShelf bookShelf
     * @return the return is true is the goal is achieved is false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {
        Matrix<Boolean> supportMatrix;

        //initialize supportMatrix:
        supportMatrix = new Matrix<>(6, 5);
        for (int i = 0; i < supportMatrix.getColumnDimension(); i++)
            for (int j = 0; j < supportMatrix.getRowDimension(); j++)
                supportMatrix.set(i, j, true);


        int groupCounter = 0;
        //algorithm
        for (int i = 0; i < bookShelf.getColumnDimension(); i++)
            for (int j = 0; j < bookShelf.getRowDimension(); j++) {

                if(bookShelf.get(i, j) != null) {
                    ColorEnum currentColor = bookShelf.get(i, j).getColor();


                    if (i < supportMatrix.getColumnDimension() - (dim - 1)) {
                        int tempCounter = 0;
                        for (int k = 0; k < dim; k++) {
                            if (bookShelf.get(i+ k, j) == null)
                                break;
                            else if (supportMatrix.get(i + k, j) && bookShelf.get(i + k, j).getColor() == currentColor) {
                                tempCounter++;
                            } else
                                break;
                        }

                        if (tempCounter == dim) {
                            groupCounter++;
                            spreadFalse(i, j, bookShelf, supportMatrix, currentColor);
                        }
                    }

                    if (j < supportMatrix.getRowDimension() - (dim - 1)) {
                        int tempCounter = 0;
                        for (int k = 0; k < dim; k++) {
                            if (bookShelf.get(i, j + k) == null)
                                break;
                            if (supportMatrix.get(i, j + k) && bookShelf.get(i, j + k).getColor() == currentColor) {
                                tempCounter++;
                            } else
                                break;
                        }
                        if (tempCounter == dim) {
                            groupCounter++;
                            spreadFalse(i, j, bookShelf, supportMatrix, currentColor);
                        }
                    }

                    supportMatrix.set(i, j, false);
                }
            }

        return groupCounter >= numberOfGroups;
    }
}