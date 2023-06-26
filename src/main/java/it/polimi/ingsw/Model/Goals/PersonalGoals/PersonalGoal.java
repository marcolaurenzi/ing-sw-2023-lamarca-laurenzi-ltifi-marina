package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.WrongConfigurationException;
import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class represents all the Personal Goals in the game and provides methods
 * to initialize and evaluate personal goals for a player.
 */
public class PersonalGoal implements Serializable {
    private Bookshelf personalGoal;
    /**
     * The number of the personal goal.
     */
    private int number;

    /**
     * Constructs a personal goal object with the specified number.
     *
     * @param i The number of the personal goal.
     * @throws IOException If there is an error loading the personal goal configuration file.
     */
    public PersonalGoal(int i) throws IOException {
        this.number = i + 1;
        personalGoal = Utils.loadBookshelfFromFile(Utils.getConfigurationPath() + "PersonalGoalConfiguration.JSON", i);
    }

    /**
     * Calculates the points earned for the given bookshelf based on the personal goal.
     *
     * @param bookshelf The bookshelf to evaluate.
     * @return The points earned.
     * @throws WrongConfigurationException If there is an invalid configuration.
     * @throws NullPointerException        If the bookshelf is null.
     */
    public int getPoints(Bookshelf bookshelf) throws WrongConfigurationException, NullPointerException {
        if (bookshelf == null) {
            throw new NullPointerException();
        }
        int numberOfRows = personalGoal.getColumnDimension();
        int numberOfColumns = personalGoal.getRowDimension();
        int counter = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (personalGoal.get(i, j) != null && bookshelf.get(i, j) != null && personalGoal.get(i, j).getType() == bookshelf.get(i, j).getType()) {
                    counter++;
                }
            }
        }
        return points(counter);
    }

    /**
     * Calculates the points based on the number of matching tiles.
     *
     * @param i The number of matching tiles.
     * @return The points earned.
     * @throws WrongConfigurationException If there is an invalid configuration.
     */
    private int points(int i) throws WrongConfigurationException {
        int result;
        switch (i) {
            case 0 -> result = 0;
            case 1 -> result = 1;
            case 2 -> result = 2;
            case 3 -> result = 4;
            case 4 -> result = 6;
            case 5 -> result = 9;
            case 6 -> result = 12;
            default -> throw new WrongConfigurationException();
        }
        return result;
    }

    /**
     * Gets the item at the specified position in the personal goal.
     *
     * @param i The row index.
     * @param j The column index.
     * @return The item at the specified position.
     */
    public Item get(int i, int j) {
        return personalGoal.get(i, j);
    }

    /**
     * Sets the personal goal bookshelf.
     *
     * @param personalGoal The personal goal bookshelf.
     */
    public void setPersonalGoal(Bookshelf personalGoal) {
        this.personalGoal = personalGoal;
    }

    /**
     * Returns the personal goal bookshelf.
     *
     * @return The personal goal bookshelf.
     */
    public Bookshelf getPersonalGoalBookshelf() {
        return personalGoal;
    }

    /**
     * Returns the name of the personal goal.
     *
     * @return The name of the personal goal.
     */
    public String getPersonalGoalName() {
        return "Personal_Goals" + number;
    }
}
