package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;
import it.polimi.ingsw.Model.PlayerState.PlayerState;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import it.polimi.ingsw.Model.PlayerState.PlayerStateWaiting;
import it.polimi.ingsw.Utils.PlayerStatusToFile;
import java.util.ArrayList;

import static it.polimi.ingsw.Model.TypeEnum.BOOKS;

/**
 * This class represents a player. It contains all the information about the player, such as the bookshelf, the
 * personal goal, the points, the state and so on.
 */
public class Player {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */
    /**
     * Represents the player's ID.
     */
    private final String playerID;
    /**
     * Represents the player's total points.
     */
    private int totalPoints;
    /**
     * Represents the player's points for the common goal.
     */
    private int commonGoalPoints;

    /**
     *getter for the player's state
     * @return the player's state
     */
    public PlayerState getState() {
        return state;
    }

    /**
     * Represents the player's state.
     */
    private PlayerState state;
    /**
     * Represents the player's bookshelf.
     */
    private final Bookshelf bookshelf;
    /**
     * Represents the player's personal goal.
     */
    private final PersonalGoal personalGoal;
    /**
     * Represents the player's common goal points.
     */
    private final boolean[] isCommonGoalAlreadyAchieved;
    /**
     * Represents the game the player is playing.
     */
    private final Game game;
    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */

    /**
     * This is the player's constructor. It initializes some variables and initialize the state
     * of the player to PlayerStateWaiting
     *
     * @param playerStatus the player's status
     * @param game the game the player accessed
     */
    public Player(PlayerStatusToFile playerStatus, Game game) {
        this.playerID = playerStatus.getPlayerID();
        this.totalPoints = playerStatus.getTotalPoints();
        this.commonGoalPoints = playerStatus.getCommonGoalPoints();
        switch(playerStatus.getState()) {
            case 0 -> this.state = new PlayerStateSelecting();
            case 1 -> this.state = new PlayerStateWaiting();
        }

        this.bookshelf = playerStatus.getBookshelf();
        this.personalGoal = playerStatus.getPersonalGoal();
        this.isCommonGoalAlreadyAchieved = playerStatus.getIsCommonGoalAlreadyAchieved();
        this.game = game;
    }

    /**
     * This is the player's constructor. It initializes some variables and initialize the state
     * of the player to PlayerStateWaiting
     *
     * @param playerID string indicating the ID of the player
     * @param game the game the player accessed
     */
    public Player(String playerID, Game game) {
        this.playerID = playerID;
        this.game = game;
        totalPoints = 0;
        bookshelf = new Bookshelf();
        personalGoal = game.getPersonalGoalDeck().draw();
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
     * @return the points earned by the player from the personal goal
     */
    private int getRewardPersonalGoal() throws WrongConfigurationException {
        return personalGoal.getPoints(this.bookshelf);
    }

    /**
     * This is a utility method that is called on a pair of coordinates of a bookshelf. It returns the dimension of the
     * chunk of adjacent items of the same type. It also updates the supportMatrix.
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
     * This method calculates the points earned by the player referring to the general goals and returns them.
     *
     * @return the points that the player earned by the general goal
     */
    private int getRewardGeneralGoal() {
        int ret = 0;
        Matrix<Boolean> supportMatrix = new Matrix<>(6, 5);
        // Initialize supportMatrix
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
     * This method takes in input an ArrayList of coordinates from the controller indicating the tiles that the player
     * wants to pick and inserts the items in those tiles in the bookshelf in the order specified by the player. It
     * also controls if it is possible to pick the selection according to the game's rules.
     *
     * @param tilesSelection the ArrayList of coordinates indicating the tiles to pick
     * @param column the column where the player wants to place the picked tiles
     * @param order the order in which the tiles should be placed in the bookshelf
     *
     * @throws PlayerIsWaitingException if the player state is waiting
     * @throws SelectionNotValidException if the selection is not valid (e.g., not all the selected tiles have at least one
     * side free or the selected tiles are not adjacent)
     * @throws SelectionIsEmptyException if the selection is empty
     * @throws ColumnNotValidException if the selected column is not valid
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds
     * @throws PickDoesntFitColumnException if the pick doesn't fit in the selected column
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tiles selection is different from the length of the order array
     * @throws WrongConfigurationException if the configuration is incorrect
     * @throws VoidBoardTileException if the board tile is void
     */
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order) throws PlayerIsWaitingException, SelectionNotValidException, SelectionIsEmptyException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, WrongConfigurationException, VoidBoardTileException {
        state.pickAndInsertInBookshelf(tilesSelection, this.game.getBoard(), this.bookshelf, column, order);
        endTurn();
    }

    /**
     * This method changes the state of the player. It is called by the controller.
     *
     * @param state the next state to set
     */
    public void changeState (PlayerState state) {
        this.state = state;
    }

    /**
     * This method is called at the end of every turn of a player and checks if one of the two common goals of the game
     * is achieved. It updates the commonGoalsPoints attribute accordingly.
     *
     * @return the common goals points already achieved plus those just achieved
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
        commonGoalPoints += temp;
        return commonGoalPoints;
    }

    /**
     * This method is called at the end of every turn.
     * It updates the points by counting personal goal points, common goal points, and general goal points.
     *
     * @throws WrongConfigurationException if the configuration is incorrect
     */
    public void computeRewardGoals() throws WrongConfigurationException {
        totalPoints = getRewardPersonalGoal() + getRewardGeneralGoal() + getRewardCommonGoals();
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    /**
     * Get the player ID.
     *
     * @return the player ID
     */
    public String getPlayerID() {
        return playerID;
    }

    /**
     * Get the player's bookshelf.
     *
     * @return the bookshelf
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * End the player's turn and compute the rewards.
     *
     * @throws WrongConfigurationException if the configuration is incorrect
     */
    public void endTurn() throws WrongConfigurationException {
        computeRewardGoals();
    }

    /**
     * Get the player's personal goal.
     *
     * @return the personal goal
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * Get the array indicating whether the common goals have already been achieved.
     *
     * @return the array indicating whether the common goals have already been achieved
     */
    public boolean[] getIsCommonGoalAlreadyAchieved() {
        return isCommonGoalAlreadyAchieved;
    }

    /**
     * Get the player's total points.
     *
     * @return the total points
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     ************************************************************************************************************ */

    /**
     * Set the bookshelf of the player.
     *
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds
     * @throws PickDoesntFitColumnException if the pick doesn't fit in the selected column
     */
    public void setBookshelf() throws PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        ArrayList<Item> fill = new ArrayList<>();
        for(int j = 0; j < 6; j++){
            fill.add(new Item(BOOKS));
        }
        for(int i = 0; i < 5; i++){
            bookshelf.insert(i, fill);
        }
    }

    /**
     * Get the player's status as an object to be saved to a file.
     *
     * @return the player's status as an object to be saved to a file
     */
    public PlayerStatusToFile getPlayerStatusToFile() {
        return new PlayerStatusToFile(
                this.playerID,
                this.totalPoints,
                this.commonGoalPoints,
                state.getStateNumber(),
                getBookshelf(),
                getPersonalGoal(),
                getIsCommonGoalAlreadyAchieved()
        );
    }
}
