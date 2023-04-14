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
    private final String playerID;
    private int points;
    private ArrayList<Coordinates> tilesSelection;
    private ArrayList<Item> pickedItems;
    private PlayerState state;
    private final Bookshelf bookshelf;
    private final PersonalGoal personalGoal;
    private boolean[] isCommonGoalAlreadyAchieved;
    private final Game game;

    /**
     * This is the player's constructor. It initializes some variables and initialize the state
     * of the player to PlayerStateWaiting
     * @param playerID string indicating the ID of the player
     * @param game the game the player accessed
     */
    public Player(String playerID, Game game) {
        this.playerID = playerID;
        this.game = game;
        points = 0;
        bookshelf = new Bookshelf();
        personalGoal = null;
        game.getPersonalGoalDeck().draw();
        isCommonGoalAlreadyAchieved = new boolean[]{false, false};
        state = new PlayerStateWaiting();
    }

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
     * this method initialize a new clean selection. It is called by the controller when the player selects
     * the first tile
     */
    public void initializeSelection() {
        tilesSelection = new ArrayList<>(3);
    }

    /**
     * This method adds the coordinate i, j to the selection. It is called by the controller
     * when the player selects a tile
     * @param board the game board
     * @param i the x coordinate of the tile to select
     * @param j the y coordinate of the tile to select
     */
    public void select(Board board, int i, int j) throws SelectionIsFullException, PlayerIsWaitingException {
            state.select(this.tilesSelection , board, i, j);
    }

    /**
     * this method remove a coordinate from the tilesSelection. It is useful if the player changes is mind and want
     * to select another tile
     * @param i x coordinate of the tile to deselect
     * @param j y coordinate of the tile to deselect
     */
    public void deSelect(int i, int j) {
        for(int k = 0; k < 3; k++)
            if(this.tilesSelection.get(k).getX() == i && this.tilesSelection.get(k).getY() == j) {
                this.tilesSelection.remove(k);
                break;
            }
    }

    /**
     * Assign the selected tiles to the pickedItems array. Only if the selection is the pick conditions are met
     * @param board the board of the game
     *
     * @throws PlayerIsWaitingException if the player state is waiting
     * @throws SelectionNotValidException if the selection is not valid AKA not all the selected tiles have at least one
     * side free or the selected tiles are not adjacent
     * @throws SelectionIsEmptyException if the selection is empty
     */
    public void pick(Board board) throws PlayerIsWaitingException, SelectionNotValidException, SelectionIsEmptyException {
        this.pickedItems = state.pick(this.tilesSelection, board);
    }

    /**
     * this method changes the state of the player. It is called by the controller
     * @param state next state
     */
    public void changeState (PlayerState state) {
        this.state = state;
    }

    /**
     * insert the pickedItems in bookShelf
     * @param column the column where the player wants to put the items
     *
     * @throws ColumnNotValidException if the items don't fit the column
     */
    public void insertPickInBookShelf (int column, int[] order) throws ColumnNotValidException, PlayerIsWaitingException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        this.state.insertPickInBookShelf(this.pickedItems, this.bookshelf, column, order);
    }

    /**
     * This method is called at the end of every turn of a player and checks if one of the two common goal of the game
     * is achieved. If this is the case it updates the points and set isCommonGoalAlreadyAchieved to true so the player
     * can't achieve again the same commonGoal
     */
    public void getRewardCommonGoals() {
        if(!this.isCommonGoalAlreadyAchieved[0]) {
            if(game.getCommonGoalPointStacks()[0].getCommonGoal().isAchieved(this.bookshelf)) {
                points += game.getCommonGoalPointStacks()[0].draw();
                this.isCommonGoalAlreadyAchieved[0] = true;
            }
        }

        if(!this.isCommonGoalAlreadyAchieved[1]) {
            if(game.getCommonGoalPointStacks()[1].getCommonGoal().isAchieved(this.bookshelf)) {
                points += game.getCommonGoalPointStacks()[1].draw();
                this.isCommonGoalAlreadyAchieved[1] = true;
            }
        }
    }

    /**
     * This method is called at the end of the game. Updates the points counting the personalGoal points and the
     * generalGoal points
     */
    public void getRewardFinalGoals() throws WrongConfigurationException {
        points += getRewardPersonalGoal() + getRewardGeneralGoal();
    }

    public String getPlayerID() {
        return playerID;
    }
}
