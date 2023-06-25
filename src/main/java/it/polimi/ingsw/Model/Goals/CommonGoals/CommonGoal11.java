package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

/**
 * This class represents Common Goal 11.
 * The goal is achieved when there are 5 columns that progressively have one tile more than the previous one.
 */
public class CommonGoal11 extends CommonGoal {

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

        int count = 0;
        if (bookshelf.get(0, 4) != null) {
            for (int i = 0; i < bookshelf.getColumnDimension() - 1; i++) {
                if ((4 - i - 1) >= 0 && bookshelf.get(i, 4 - i - 1) != null) {
                    break;
                }
                if (bookshelf.get(i, 4 - i) == null) {
                    break;
                } else if (bookshelf.get(i, 4 - i) != null) {
                    count++;
                }
            }

            return count == 5;

        } else if (bookshelf.get(1, 4) != null) {
            for (int i = 1; i < bookshelf.getColumnDimension(); i++) {
                if ((5 - i - 1) >= 0 && bookshelf.get(i, 5 - i - 1) != null) {
                    break;
                }
                if (bookshelf.get(i, 5 - i) == null) {
                    break;
                } else if (bookshelf.get(i, 5 - i) != null) {
                    count++;
                }
            }
            return count == 5;
        }

        if (bookshelf.get(0,0) != null){
            for (int i = 0; i < bookshelf.getColumnDimension()-1; i++) {
                if ((i + 1) < 5 && bookshelf.get(i, i + 1) != null) {
                    break;
                }
                if (bookshelf.get(i, i) == null) {
                    break;
                } else if (bookshelf.get(i, i) != null) {
                    count++;
                }
            }
            return count == 5;

        } else if (bookshelf.get(1,0) != null) {
            for (int i = 1; i < bookshelf.getColumnDimension(); i++) {
                if ((i) < 5 && bookshelf.get(i, i) != null) {
                    break;
                }
                if (bookshelf.get(i, i - 1) == null) {
                    break;
                } else if (bookshelf.get(i, i - 1) != null) {
                    count++;
                }
            }
            return count == 5;
        }

        return false;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal11";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return """
                Five columns of increasing or decreasing height.
                Starting from the shortest column and going up, each next column must have exactly one more tile.
                The tiles may be of any type.""";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "12";
    }
}
