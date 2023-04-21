package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;
import it.polimi.ingsw.Model.PlayerStates.PlayerState;
import it.polimi.ingsw.Model.PlayerStates.PlayerStateWaiting;

import java.util.ArrayList;

/**
 *
 */
public class Player {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */
    private final String playerID;
    private int totalPoints;
    private int commonGoalPoints;
    private ArrayList<Coordinates> tilesSelection;
    private ArrayList<Item> pickedItems;
    private PlayerState state;
    private final Bookshelf bookshelf;
    private final PersonalGoal personalGoal;
    private final boolean[] isCommonGoalAlreadyAchieved;
    private final Game game;
    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */

    /**
     * This is the player's constructor. It initializes some variables and initialize the state
     * of the player to PlayerStateWaiting
     * @param playerID string indicating the ID of the player
     * @param game the game the player accessed
     */
    public Player(String playerID, Game game) {
        this.playerID = playerID;
        this.game = game;
        totalPoints = 0;
        bookshelf = new Bookshelf();
        personalGoal = null;
        game.getPersonalGoalDeck().draw();
        isCommonGoalAlreadyAchieved = new boolean[]{false, false};
        state = new PlayerStateWaiting();
    }

    /* ************************************************************************************************************
     *                          END OF CONSTRUCTORS
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */

    /**
     * This method is called at the end of the game
     *
     * @return  the points earned by the player from the personal goal
     */
    private int getRewardPersonalGoal() throws WrongConfigurationException {
        return personalGoal.getPoints(this.bookshelf);
    }

    /**
     * this is a util method that is called on a pair of coordinates of a bookshelf. it returns the dimension of the
     * chunk of adjacent items of the same type. It also updates the supportMatrix
     *
     * @param i x coordinate
     * @param j y coordinate
     * @param bookshelf the bookShelf
     * @param supportMatrix the supportMatrix
     * @param type the type of the Item in booKShelf[i][j]
     *
     * @return 1 + the number of adjacent elements that are the same color of the current element and are set to true
     *         in the supportMatrix
     */
    private int numberOfAdjacentSameTypeAndSpreadFalse(int i, int j, Bookshelf bookshelf, Matrix<Boolean> supportMatrix, TypeEnum type) {
        int ret = 1;
        supportMatrix.set(i, j, false);

        if(i + 1 < supportMatrix.getColumnDimension() && bookshelf.get(i + 1, j) != null)
            if(bookshelf.get(i + 1, j).getType() == type && supportMatrix.get(i + 1, j))
                ret += numberOfAdjacentSameTypeAndSpreadFalse(i +1, j, bookshelf, supportMatrix, type);

        if(i - 1 >= 0 && bookshelf.get(i - 1, j) != null)
            if(bookshelf.get(i - 1, j).getType() == type && supportMatrix.get(i - 1, j))
                ret += numberOfAdjacentSameTypeAndSpreadFalse(i - 1, j, bookshelf, supportMatrix, type);

        if(j + 1 < supportMatrix.getRowDimension() && bookshelf.get(i, j + 1)!= null)
            if(bookshelf.get(i, j + 1).getType() == type && supportMatrix.get(i, j + 1))
                ret += numberOfAdjacentSameTypeAndSpreadFalse(i, j + 1, bookshelf, supportMatrix, type);

        if(j - 1 >= 0 && bookshelf.get(i, j - 1) != null)
            if(bookshelf.get(i, j - 1).getType() == type && supportMatrix.get(i, j - 1))
                ret += numberOfAdjacentSameTypeAndSpreadFalse(i, j - 1, bookshelf, supportMatrix, type);


        return ret;
    }

    /**
     * This method calculates the points earned by the player referring to the general goals and returns them
     *
     * @return the points that the player earned by the general goal
     */
    private int getRewardGeneralGoal() {
        int ret = 0;
        Matrix<Boolean> supportMatrix = new Matrix<>(6, 5);
        //initialize supportMatrix
        for(int i = 0; i < supportMatrix.getColumnDimension(); i++)
            for(int j = 0; j < supportMatrix.getRowDimension(); j++)
                supportMatrix.set(i, j, true);

        for(int i = 0; i < supportMatrix.getColumnDimension(); i++)
            for(int j = 0; j < supportMatrix.getRowDimension(); j++) {
                if (supportMatrix.get(i, j)) {
                    supportMatrix.set(i, j, false);
                    if (this.bookshelf.get(i, j) != null) {
                        int numberOfAdjacentSameType = numberOfAdjacentSameTypeAndSpreadFalse(i, j, this.bookshelf, supportMatrix, this.bookshelf.get(i, j).getType());

                        if (numberOfAdjacentSameType == 3)
                            ret += 2;
                        else if (numberOfAdjacentSameType == 4)
                            ret += 3;
                        else if (numberOfAdjacentSameType == 5)
                            ret += 5;
                        else if (numberOfAdjacentSameType >= 6)
                            ret += 8;
                    }
                }
            }

        return ret;
    }

    /**
     * This method take in input an ArrayList of coordinates from the controller indicating the tiles that the player
     * want to pick and inserts the items in those tiles in the bookshelf in the order specified from the player. It
     * also controls if it is possible to pick the selection according to the game's rules
     *
     *
     * @throws PlayerIsWaitingException if the player state is waiting
     * @throws SelectionNotValidException if the selection is not valid AKA not all the selected tiles have at least one
     * side free or the selected tiles are not adjacent
     * @throws SelectionIsEmptyException if the selection is empty
     */
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order) throws PlayerIsWaitingException, SelectionNotValidException, SelectionIsEmptyException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, WrongConfigurationException {
        state.pickAndInsertInBookshelf(tilesSelection, this.game.getBoard(), this.bookshelf, column, order);
    }

    /**
     * this method changes the state of the player. It is called by the controller
     * @param state next state
     */
    public void changeState (PlayerState state) {
        this.state = state;
    }

    /**
     * This method is called at the end of every turn of a player and checks if one of the two common goal of the game
     * and checks if any other CommonGoal is achieved, if this is the ase updates the commonGoalsPoints attribute, if it's not, it just sums 0
     * @return common goals points already achieved plus those just achieved
     */
    private int getRewardCommonGoals() {
        int temp = 0;
        if(!this.isCommonGoalAlreadyAchieved[0]) {
            if(game.getCommonGoalPointStacks()[0].getCommonGoal().isAchieved(this.bookshelf)) {
                this.isCommonGoalAlreadyAchieved[0] = true;
                temp += game.getCommonGoalPointStacks()[0].draw();

            }
        }

        if(!this.isCommonGoalAlreadyAchieved[1]) {
            if(game.getCommonGoalPointStacks()[1].getCommonGoal().isAchieved(this.bookshelf)) {
                this.isCommonGoalAlreadyAchieved[1] = true;
                temp += game.getCommonGoalPointStacks()[1].draw();
            }
        }
        return commonGoalPoints += temp;
    }

    /**
     * This method is called at the end of every turn. Updates the points counting personalGoal points, commonGoal ponts  and the
     * generalGoal points
     */
    public void getRewardGoals() throws WrongConfigurationException {
        totalPoints = getRewardPersonalGoal() + getRewardGeneralGoal() + getRewardCommonGoals();
    }

    /**
     *
     * @param comando
     */
    public void applyCommand(String comando) {
        switch (comando) {
        }
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    public String getPlayerID() {
        return playerID;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void endTurn() throws WrongConfigurationException {
        getRewardGoals();
    }
    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     ************************************************************************************************************ */
}
