package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;

/**
 *
 */
public class CommonGoal10 extends CommonGoal{

    /**
     *
     * @param bookShelf
     *
     * @return
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {

        for (int i = 0; i < bookShelf.getColumnDimension() - 2; i++) {
            for (int j = 0; j < bookShelf.getColumnDimension() - 2; j++) {
                if (bookShelf.get(i, j) == null ||
                        bookShelf.get(i + 2, j) == null ||
                        bookShelf.get(i + 1, j + 1) == null ||
                        bookShelf.get(i + 2, j) == null ||
                        bookShelf.get(i + 2, j + 2) == null) {
                    break; // if there is a null, there is no need to check the other cells
                } else if (bookShelf.get(i, j).getType() == bookShelf.get(i + 2, j).getType() &&
                        bookShelf.get(i, j).getType() == bookShelf.get(i + 1, j + 1).getType() &&
                        bookShelf.get(i, j).getType() == bookShelf.get(i + 2, j).getType() &&
                        bookShelf.get(i, j).getType() == bookShelf.get(i + 2, j + 2).getType()) {
                    return true;
                } // if all the cells have the same type, the goal is achieved
            }
        }
        return false;
    }
}
