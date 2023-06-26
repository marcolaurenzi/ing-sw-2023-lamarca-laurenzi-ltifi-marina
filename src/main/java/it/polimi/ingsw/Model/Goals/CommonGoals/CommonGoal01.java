package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

/**
 * This class represents the CommonGoals number 0 and 1
 * CommonGoal description: numberOfGroups groups each containing at least dim tiles of the same type.
 * the tiles of one group can be different from those of another group
 */
public class CommonGoal01 extends CommonGoal {
    /**
     * The number of tiles of the same type that must be present in a group.
     */
    private final int dim;
    /**
     * The number of groups that must be present in the bookshelf.
     */
    private final int numberOfGroups;

    public CommonGoal01(int dim, int numberOfGroups) {
        this.dim = dim;
        this.numberOfGroups = numberOfGroups;
    }

    /**
     * Counts the number of elements in a cluster starting from the specified position (i, j) in the flag matrix.
     * <p>
     * The method recursively traverses the cluster of connected elements in the flag matrix, represented by a 2D array.
     * Elements with a value of 1 are considered part of the cluster and will be marked as 2 to avoid recounting.
     * Elements with a value of 0 are considered positions to be skipped.
     *
     * @param flagMatrix The flag matrix represented as a 2D array of integers.
     * @param i          The row index of the current position in the flag matrix.
     * @param j          The column index of the current position in the flag matrix.
     * @return The number of elements in the cluster starting from the specified position.
     */
    private static int countElementsCluster(int[][] flagMatrix, int i, int j) {
        if (i < 0 || j < 0 || i >= flagMatrix.length || j >= flagMatrix[0].length || flagMatrix[i][j] == 0) {
            return 0;
        } else if (flagMatrix[i][j] == 1) {
            flagMatrix[i][j] = 2;
            return 1 + countElementsCluster(flagMatrix, i - 1, j)
                    + countElementsCluster(flagMatrix, i + 1, j)
                    + countElementsCluster(flagMatrix, i, j - 1)
                    + countElementsCluster(flagMatrix, i, j + 1);
        } else {
            return 0;
        }
    }


    /**
     * Deletes a cluster of elements in the flag matrix recursively starting from the specified position (i, j).
     * <p>
     * The method recursively traverses the cluster of connected elements in the flag matrix, represented by a 2D array.
     * Elements with a value of 2 are considered part of the cluster and will be deleted by setting their value to 0.
     * Elements with a value of 0 or 1 are considered positions to be skipped.
     *
     * @param flagMatrix The flag matrix represented as a 2D array of integers.
     * @param i          The row index of the current position in the flag matrix.
     * @param j          The column index of the current position in the flag matrix.
     */
    private static void deleteCluster(int[][] flagMatrix, int i, int j) {
        if (i < 0 || j < 0 || i >= flagMatrix.length || j >= flagMatrix[0].length || flagMatrix[i][j] == 0 || flagMatrix[i][j] == 1) {
            return;
        }
        if (flagMatrix[i][j] == 2) {
            flagMatrix[i][j] = 0;
            deleteCluster(flagMatrix, i - 1, j);
            deleteCluster(flagMatrix, i + 1, j);
            deleteCluster(flagMatrix, i, j - 1);
            deleteCluster(flagMatrix, i, j + 1);
        }
    }


    /**
     * Counts the number of valid groups of tiles flagged in a "Flag Matrix".
     * <p>
     * A valid group is a cluster of connected elements in the flag matrix
     * that has a size equal to or greater than the specified dimension "dim".
     *
     * @param flagMatrix The flag matrix represented as a 2D array of integers.
     *                   A value of 1 represents an element to be checked at a given iteration.
     *                   A value of 0 represents a position to be skipped.
     *                   A value of 2 represents an already counted tile.
     * @param dim        The minimum dimension required for a group to be considered valid.
     * @return The number of valid groups found in the flag matrix.
     */
    public static int countValidGroups(int[][] flagMatrix, int dim) {
        int counter = 0;

        for (int i = 0; i < flagMatrix.length; i++) {
            for (int j = 0; j < flagMatrix[0].length; j++) {
                if (flagMatrix[i][j] == 1) {
                    if (countElementsCluster(flagMatrix, i, j) >= dim) {
                        counter++;
                    }
                    deleteCluster(flagMatrix, i, j);
                }
            }
        }
        return counter;
    }


    /**
     * Checks if the given bookshelf meets the achievement criteria.
     * <p>
     * The method uses a flag matrix to identify and count valid groups of books based on their types.
     * Each book type is iterated over, and a separate flag matrix is created to represent the presence of that book type.
     * The flag matrix is populated based on the bookshelf's content, marking valid book positions with 1 and others with 0.
     * The number of valid groups is counted using the countValidGroups method, and if it meets the required number,
     * the achievement is considered achieved.
     *
     * @param bookshelf      The bookshelf object to be checked for achievement.
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

        for (TypeEnum type : TypeEnum.values()) {
            // Reset flagMatrix
            for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
                for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                    if (bookshelf.get(i, j) != null && bookshelf.get(i, j).getType() == type) {
                        flagMatrix[i][j] = 1;
                    } else {
                        flagMatrix[i][j] = 0;
                    }
                }
            }

            groupCounter += countValidGroups(flagMatrix, dim);
            if (groupCounter >= numberOfGroups) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the name of the goal based on the specified dimension.
     * <p>
     * If the dimension is 2, the method returns "CommonGoal0".
     * If the dimension is 4, the method returns "CommonGoal1".
     * For any other dimension, the method returns "Error".
     *
     * @return The name of the goal based on the dimension.
     */
    @Override
    public String getGoalName() {
        if (dim == 2) {
            return "CommonGoal0";
        } else if (dim == 4) {
            return "CommonGoal1";
        }
        return "Error";
    }



    /**
     * Returns the description of the goal.
     * <p>
     * The method generates a description based on the number of groups and tiles specified.
     * The description states that there should be a certain number of groups, each containing at least
     * the specified number of tiles of the same type.
     * It also mentions that different groups may have tiles of different types.
     *
     * @return The description of the goal.
     */
    @Override
    public String getGoalDescription() {
        return this.numberOfGroups + " groups each containing at least " + this.numberOfGroups + " tiles of the same type.\n" +
                "Different groups may have tiles of different types.";
    }


    /**
     * Returns the file number associated with the goal based on the specified dimension.
     * <p>
     * If the dimension is 4, the method returns "1".
     * For any other dimension, the method returns "2".
     *
     * @return The file number associated with the goal based on the dimension.
     */
    @Override
    public String getGoalFileNumber() {
        if (dim == 4) {
            return "1";
        } else {
            return "2";
        }
    }

}