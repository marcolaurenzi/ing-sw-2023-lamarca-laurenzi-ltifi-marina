package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Matrix;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal 3
 * This goal is achieved if the board contains at least 2 of the following group of tiles.
 * Four tiles of the same Type representing a perfect square (boarding tiles MUST be of different Types)
 */
public class CommonGoal3 extends CommonGoal{

    public static int countGroups(int[][] flagMatrix) {
        int groupCounter = 0;
        //caso base bordo
        for (int i = 0; i < flagMatrix.length-1; i++) {
            for (int j = 0; j < flagMatrix[0].length-1; j++) {
                if (flagMatrix[i][j] == 1 && flagMatrix[i+1][j] == 1 && flagMatrix[i][j+1] == 1 && flagMatrix[i+1][j+1] == 1) {
                    groupCounter++;
                    flagMatrix[i][j] = 0;
                    flagMatrix[i+1][j] = 0;
                    flagMatrix[i][j+1] = 0;
                    flagMatrix[i+1][j+1] = 0;
                }
            }
        }
        return groupCounter;
    }

    /**
     * This method checks if the goal is achieved
     * @param bookshelf bookShelf
     * @return true if the goal is achieved, false otherwise
     */
    //private static int[][] recursionSupportMatrix = new int[6][5];
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        int[][] flagMatrix = new int[bookshelf.getColumnDimension()][bookshelf.getRowDimension()];
        int groupCounter = 0;
        //initialize supportMatrix:
        for (TypeEnum type : TypeEnum.values()) {
            //reset flagMatrix
            for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
                for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                    if (bookshelf.get(i,j) != null && bookshelf.get(i,j).getType() == type) {
                        flagMatrix[i][j] = 1;
                    }
                    else {
                        flagMatrix[i][j] = 0;
                    }
                }
            }
            groupCounter += CommonGoal3.countGroups(flagMatrix);
            if(groupCounter >= 2){
                return true;
            }
        }
        return false;
    }

    public String getGoalName() {
        return "CommonGoal3";
    }

    @Override
    public String getGoalDescription() {
        return "At least two 2x2 squares of tiles of the same type.\nDifferent squares may have different types.";
    }

    @Override
    public String getGoalFileNumber() { return "4";}
}