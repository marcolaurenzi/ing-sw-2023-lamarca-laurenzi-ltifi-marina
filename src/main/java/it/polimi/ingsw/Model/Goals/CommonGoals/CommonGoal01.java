package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Matrix;

/**
 * This class represents the CommonGoals number 0 and 1
 * CommonGoal description: numberOfGroups groups each containing at least dim tiles of the same type.
 * the tiles of one group can be different from those of another group
 */
public class CommonGoal01 extends CommonGoal {
    private final int dim;
    private final int numberOfGroups;

    public CommonGoal01(int dim, int numberOfGroups) {
        this.dim = dim;
        this.numberOfGroups = numberOfGroups;
    }

    public static int countElementsCluster(int[][] flagMatrix, int i, int j){
        if(i<0 || j<0 || i>=flagMatrix.length || j>=flagMatrix[0].length || flagMatrix[i][j]==0){
            return 0;
        }else if (flagMatrix[i][j]==1){
            flagMatrix[i][j]=2;
            return 1+countElementsCluster(flagMatrix, i-1, j)
                    +countElementsCluster(flagMatrix, i+1, j)
                    +countElementsCluster(flagMatrix, i, j-1)
                    +countElementsCluster(flagMatrix, i, j+1);
        }else{
            return 0;
        }
    }

    public static void deleteCluster(int[][] flagMatrix, int i, int j){
        if(i<0 || j<0 || i>=flagMatrix.length || j>=flagMatrix[0].length || flagMatrix[i][j]==0||flagMatrix[i][j]==1){
            return;
        }
        if(flagMatrix[i][j]==2) {
            flagMatrix[i][j] = 0;
            deleteCluster(flagMatrix, i-1, j);
            deleteCluster(flagMatrix, i+1, j);
            deleteCluster(flagMatrix, i, j-1);
            deleteCluster(flagMatrix, i, j+1);
        }
    }

    public static int countValidGroups(int[][] flagMatrix, int dim) {
        int counter = 0;

        for(int i = 0; i < flagMatrix.length; i++){
            for(int j = 0; j < flagMatrix[0].length; j++){
                if(flagMatrix[i][j] == 1){
                    if (countElementsCluster(flagMatrix, i, j)>=dim){
                        counter++;
                    }
                    deleteCluster(flagMatrix, i, j);
                }
            }
        }
       return counter;
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

            groupCounter += countValidGroups(flagMatrix, dim);
            if(groupCounter >= numberOfGroups){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return name of the goal
     */
    @Override
    public String getGoalName() {
        if(dim == 2){
            return "CommonGoal0";
        }
        return "CommonGoal1";
    }


    @Override
    public String getGoalDescription() {
        return this.numberOfGroups + " groups each containing at least" + this.numberOfGroups+ "tiles of the same type.\n " +
                "Different groups may have tiles of different types.";
    }

    @Override
    public  String getGoalFileNumber() {
        if(dim == 4 )  {
            return "1";
        } else {
            return "2";
        }
    }
}