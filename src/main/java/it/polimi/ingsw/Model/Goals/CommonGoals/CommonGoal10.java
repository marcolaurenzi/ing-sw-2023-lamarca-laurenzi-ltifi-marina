package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

/**
 *
 */
public class CommonGoal10 extends CommonGoal{

    /**
     *
     * @param bookshelf
     *
     * @return
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        for (int i = 0; i < bookshelf.getColumnDimension() - 2; i++) {
            for (int j = 0; j < bookshelf.getColumnDimension() - 2; j++) {
                if (bookshelf.get(i, j) == null ||
                        bookshelf.get(i + 2, j) == null ||
                        bookshelf.get(i + 1, j + 1) == null ||
                        bookshelf.get(i + 2, j) == null ||
                        bookshelf.get(i + 2, j + 2) == null) {
                    break; // if there is a null, there is no need to check the other cells
                } else if (bookshelf.get(i, j).getType() == bookshelf.get(i + 2, j).getType() &&
                        bookshelf.get(i, j).getType() == bookshelf.get(i + 1, j + 1).getType() &&
                        bookshelf.get(i, j).getType() == bookshelf.get(i + 2, j).getType() &&
                        bookshelf.get(i, j).getType() == bookshelf.get(i + 2, j + 2).getType()) {
                    return true;
                } // if all the cells have the same type, the goal is achieved
            }
        }
        return false;
    }
}
