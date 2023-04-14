package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.WrongConfigurationException;

import java.io.IOException;

/**
 * This class is an abstract class representing all the
 * Personal Goals the game has, and implements the Goal interface
 * that gives it the isAchieved() method to check whether the
 * goal is achieved or not
 */
public class PersonalGoal {

    private Bookshelf personalGoal;

    public PersonalGoal(int i) throws IOException {
        personalGoal = Utils.loadBookshelfFromFile(Utils.getConfigurationPath() + "PersonalGoalConfiguration.JSON", i);
    }

    public int getPoints(Bookshelf bookshelf) throws WrongConfigurationException, NullPointerException {
        if(bookshelf == null) {
            throw  new NullPointerException();
        }
        int numberOfRows = personalGoal.getColumnDimension();
        int numberOfColumns = personalGoal.getRowDimension();
        int counter = 0;
        for(int i = 0; i < numberOfRows; i++) {
            for(int j = 0; j < numberOfColumns; j++) {
                if(personalGoal.get(i,j) != null && bookshelf.get(i,j) != null && personalGoal.get(i,j).getType() == bookshelf.get(i,j).getType()) {
                    counter++;
                }
            }
        }
        return points(counter);
    }

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

    public Item get(int i, int j) {
        return personalGoal.get(i,j);
    }

    public void setPersonalGoal(Bookshelf personalGoal) {
        this.personalGoal = personalGoal;
    }
}