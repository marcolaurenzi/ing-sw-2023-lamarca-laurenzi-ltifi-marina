package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

/**
 * This class represents the CommonGoal 11
 * This goal is achieved when there are 5 columns that progressively have one tile more than the previous one
 */
public class CommonGoal11 extends CommonGoal{

    /**
     * Checks whether the goal is achieved or not
     * @param bookshelf is the reference to the actual BookShelf Object on which Algorithm operates
     * @return returns true if the Goal is Achieved, false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {
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
    public String getGoalName(){
        return "CommonGoal11";
    }

    @Override
    public String getGoalDescription() {
        return "This goal is achieved when there are 5 columns that progressively have one tile more than the previous one";
    }
}
