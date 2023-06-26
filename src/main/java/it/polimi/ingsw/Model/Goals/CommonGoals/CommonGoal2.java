package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;

/**
 * This class represents the CommonGoal 2
 * This goal is achieved when there are 4 tiles of the same ElementType placed in the 4 corners of the player's Bookshelf
 */
public class CommonGoal2 extends CommonGoal{
    /**
     * Constructs a CommonGoal2 object.
     */
    public CommonGoal2() {
    }

    /**
     * Checks if the given bookshelf meets the achievement criteria.
     * <p>
     * The method verifies if the four corner positions of the bookshelf are not null.
     * If any of the corner positions is null, the achievement is considered not achieved.
     * Otherwise, the method compares the types of books at the four corners.
     * The achievement is achieved if all four corner positions have the same book type.
     *
     * @param bookshelf The bookshelf object to be checked for achievement.
     * @return true if the achievement is achieved, false otherwise.
     * @throws NullPointerException If the bookshelf reference is null.
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        if (bookshelf == null) {
            throw new NullPointerException();
        }

        if (bookshelf.get(0, 0) == null ||
                bookshelf.get(bookshelf.getColumnDimension() - 1, 0) == null ||
                bookshelf.get(bookshelf.getColumnDimension() - 1, bookshelf.getRowDimension() - 1) == null ||
                bookshelf.get(0, bookshelf.getRowDimension() - 1) == null) {
            return false;
        } else {
            return bookshelf.get(0, 0).getType() == bookshelf.get(bookshelf.getColumnDimension() - 1, 0).getType() &&
                    bookshelf.get(0, 0).getType() == bookshelf.get(bookshelf.getColumnDimension() - 1, bookshelf.getRowDimension() - 1).getType() &&
                    bookshelf.get(0, 0).getType() == bookshelf.get(0, bookshelf.getRowDimension() - 1).getType();
        }
    }


    /**
     * Returns the name of the goal.
     * <p>
     * The method always returns "CommonGoal2".
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal2";
    }

    /**
     * Returns the description of the goal.
     * <p>
     * The method returns a fixed description stating that the goal is to have four tiles of the same type
     * positioned at the four corners of the bookshelf.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Four tiles of the same type at the four corners of the bookshelf.";
    }


    /**
     * Returns the file number associated with the goal.
     * <p>
     * The method always returns "3".
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "3";
    }

}
