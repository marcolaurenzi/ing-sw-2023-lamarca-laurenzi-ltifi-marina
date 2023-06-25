package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;


/**
 * This class represents the CommonGoal 3
 * This goal is achieved if the board contains at least 2 of the following group of tiles.
 * Four tiles of the same Type representing a perfect square (boarding tiles MUST be of different Types)
 */
public class CommonGoal3 extends CommonGoal{

    /**
     * Counts the number of groups of four adjacent elements of the same type forming a 2x2 square.
     * <p>
     * The method iterates over the flag matrix and checks for groups of four adjacent elements.
     * A group is identified when the current element and its three adjacent elements are all equal to 1.
     * If a group is found, the group counter is incremented, and the elements in the group are reset to 0.
     *
     * @param flagMatrix The flag matrix represented as a 2D array of integers.
     * @return The number of groups of four adjacent elements in the flag matrix.
     */
    private static int countGroups(int[][] flagMatrix) {
        int groupCounter = 0;
        for (int i = 0; i < flagMatrix.length - 1; i++) {
            for (int j = 0; j < flagMatrix[0].length - 1; j++) {
                if (flagMatrix[i][j] == 1 && flagMatrix[i + 1][j] == 1 && flagMatrix[i][j + 1] == 1 && flagMatrix[i + 1][j + 1] == 1) {
                    groupCounter++;
                    flagMatrix[i][j] = 0;
                    flagMatrix[i + 1][j] = 0;
                    flagMatrix[i][j + 1] = 0;
                    flagMatrix[i + 1][j + 1] = 0;
                }
            }
        }
        return groupCounter;
    }


    /**
     * Checks if the given bookshelf has two 2x2 squares of tiles of the same type within each square.
     * <p>
     * The method creates a flag matrix to iterate over each type separately.
     * For each type, the flag matrix is initialized by marking positions with the corresponding book type as 1,
     * and other positions as 0. The countGroups method is then used to count the number of groups of four adjacent elements
     * in the flag matrix.
     * If the total count of groups is at least 2, the achievement is considered achieved.
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

        int[][] flagMatrix = new int[bookshelf.getColumnDimension()][bookshelf.getRowDimension()];
        int groupCounter = 0;

        // Initialize flagMatrix
        for (TypeEnum type : TypeEnum.values()) {
            for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
                for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                    if (bookshelf.get(i, j) != null && bookshelf.get(i, j).getType() == type) {
                        flagMatrix[i][j] = 1;
                    } else {
                        flagMatrix[i][j] = 0;
                    }
                }
            }
            groupCounter += CommonGoal3.countGroups(flagMatrix);
            if (groupCounter >= 2) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the name of the goal.
     * <p>
     * The method always returns "CommonGoal3".
     *
     * @return The name of the goal.
     */
    public String getGoalName() {
        return "CommonGoal3";
    }


    /**
     * Returns the description of the goal.
     * <p>
     * The method returns a description stating that the goal is to have at least two 2x2 squares
     * of tiles of the same type in the bookshelf.
     * It also mentions that different squares may have different types.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return "At least two 2x2 squares of tiles of the same type.\nDifferent squares may have different types.";
    }


    /**
     * Returns the file number associated with the goal.
     * <p>
     * The method always returns "4".
     *
     * @return The file number associated with the goal.
     */
    @Override
    public String getGoalFileNumber() {
        return "4";
    }

}