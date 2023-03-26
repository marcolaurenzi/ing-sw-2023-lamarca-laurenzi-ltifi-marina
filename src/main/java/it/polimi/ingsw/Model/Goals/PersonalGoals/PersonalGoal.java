package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.ColorEnum;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;

import java.util.ArrayList;

/**
 * This class is an abstract class representing all the
 * Personal Goals the game has, and implements the Goal interface
 * that gives it the isAchieved() method to check whether the
 * goal is achieved or not
 */

public abstract class PersonalGoal {
    // per la numerazione dei personalgoals seguo quella usata negli asset grafici forniti


    public abstract Matrix<Item> createpersonalGoal();
    private Matrix<Item> personalGoal = createpersonalGoal();

    /*
    protected PersonalGoal(BookShelf personalGoal) {
        this.personalGoal = personalGoal;
    }
    */

    public ColorEnum get(int i, int j) {
        return personalGoal.get(i,j).getColor();
    }

    //in each goal we'll need to set the matrix of the shelf

    //returns the num of matches for the personal goal
    private int numOfMatches(PersonalGoal playerBookShelf) {
        int matches = 0;

        for(int i = 0; i<personalGoal.getRowDimension(); i++) {
            for(int j = 0; j < personalGoal.getColumnDimension(); j++) {
                if(personalGoal.get(i,j) != null) {
                    if(personalGoal.get(i,j).getColor()==playerBookShelf.get(i,j)){
                        matches++;
                    }
                }
            }
        }

        return matches;
    }

    int personalGoalScore(PersonalGoal playerBookshelf){
        int tmp = numOfMatches(playerBookshelf);

        return switch (tmp) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            case 5 -> 9;
            case 6 -> 12;
            default -> 0;
        };
    }

}
