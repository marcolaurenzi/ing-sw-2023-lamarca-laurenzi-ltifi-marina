package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

/**
 * This class represents Common Goal number 6, which is achieved when there are 5 diagonally placed tiles
 * of the same ElementType in the Bookshelf.
 */
public class CommonGoal6 extends CommonGoal {
    /**
     * Constructs a CommonGoal6 object.
     */
    public CommonGoal6() {

    }
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

        TypeEnum last;
        int count = 0;
        last = null;
        for (int i = 0; i < bookshelf.getColumnDimension() - 1; i++) {
            if (bookshelf.get(i, i) == null) {
                break;
            }
            if (last == null) {
                last = bookshelf.get(i, i).getType();
            }
            if (bookshelf.get(i, i).getType().equals(last)) {
                count++;
            }
        }
        if (count == 5) {
            return true;
        }
        count = 0;
        last = null;
        for (int i = 1; i < bookshelf.getColumnDimension(); i++) {
            if (bookshelf.get(i, i - 1) == null) {
                break;
            }
            if (last == null) {
                last = bookshelf.get(i, i - 1).getType();
            }
            if (bookshelf.get(i, i - 1).getType().equals(last)) {
                count++;
            }
        }
        if (count == 5) {
            return true;
        }
        count = 0;
        last = null;
        for (int i = 0; i < bookshelf.getColumnDimension() - 1; i++) {
            if (bookshelf.get(i, bookshelf.getRowDimension() - i - 1) == null) {
                break;
            }
            if (last == null) {
                last = bookshelf.get(i, bookshelf.getRowDimension() - i - 1).getType();
            }
            if (bookshelf.get(i, bookshelf.getRowDimension() - i - 1).getType().equals(last)) {
                count++;
            }
        }
        if (count == 5) {
            return true;
        }
        count = 0;
        last = null;
        for (int i = 1; i < bookshelf.getColumnDimension(); i++) {
            if (bookshelf.get(i, bookshelf.getRowDimension() - i) == null) {
                break;
            }
            if (last == null) {
                last = bookshelf.get(i, bookshelf.getRowDimension() - i).getType();
            }
            if (bookshelf.get(i, bookshelf.getRowDimension() - i).getType().equals(last)) {
                count++;
            }
        }

        return count == 5;
    }

    /**
     * Returns the name of the goal.
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal6";
    }

    /**
     * Returns the description of the goal.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "Five tiles of the same type forming a diagonal.\nThe diagonal formed may be in any of the two directions.";
    }

    /**
     * Returns the file number associated with the goal.
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "7";
    }
}
