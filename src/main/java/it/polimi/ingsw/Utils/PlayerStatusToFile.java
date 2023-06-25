package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

/**
 * This class represents the status of a player to be saved to a file.
 * It contains information such as player ID, total points, common goal points, player state,
 * bookshelf, personal goal, and the status of already achieved common goals.
 */
public class PlayerStatusToFile {
    /**
     * The ID of the player.
     */
    private final String playerID;
    /**
     * The total points earned by the player.
     */
    private final int totalPoints;
    /**
     * The points earned from common goals by the player.
     */
    private final int commonGoalPoints;
    /**
     * The state of the player.
     */
    private final int state;
    /**
     * The bookshelf of the player.
     */
    private final Bookshelf bookshelf;
    /**
     * The personal goal of the player.
     */
    private final PersonalGoal personalGoal;
    /**
     * An array indicating whether each common goal has been achieved.
     */
    private final boolean[] isCommonGoalAlreadyAchieved;

    /**
     * Constructs a PlayerStatusToFile object with the specified parameters.
     *
     * @param playerID                   the ID of the player
     * @param totalPoints                the total points earned by the player
     * @param commonGoalPoints           the points earned from common goals by the player
     * @param state                      the state of the player
     * @param bookshelf                  the bookshelf of the player
     * @param personalGoal               the personal goal of the player
     * @param isCommonGoalAlreadyAchieved the status of already achieved common goals
     */
    public PlayerStatusToFile(String playerID, int totalPoints, int commonGoalPoints, int state,
                              Bookshelf bookshelf, PersonalGoal personalGoal,
                              boolean[] isCommonGoalAlreadyAchieved) {
        this.playerID = playerID;
        this.totalPoints = totalPoints;
        this.commonGoalPoints = commonGoalPoints;
        this.state = state;
        this.bookshelf = bookshelf;
        this.personalGoal = personalGoal;
        this.isCommonGoalAlreadyAchieved = isCommonGoalAlreadyAchieved;
    }

    /**
     * Returns the ID of the player.
     *
     * @return the player ID
     */
    public String getPlayerID() {
        return playerID;
    }

    /**
     * Returns the total points earned by the player.
     *
     * @return the total points
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Returns the points earned from common goals by the player.
     *
     * @return the common goal points
     */
    public int getCommonGoalPoints() {
        return commonGoalPoints;
    }

    /**
     * Returns the state of the player.
     *
     * @return the player state
     */
    public int getState() {
        return state;
    }

    /**
     * Returns the bookshelf of the player.
     *
     * @return the bookshelf
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * Returns the personal goal of the player.
     *
     * @return the personal goal
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * Returns the status of already achieved common goals.
     *
     * @return the array indicating the status of already achieved common goals
     */
    public boolean[] getIsCommonGoalAlreadyAchieved() {
        return isCommonGoalAlreadyAchieved;
    }
}
