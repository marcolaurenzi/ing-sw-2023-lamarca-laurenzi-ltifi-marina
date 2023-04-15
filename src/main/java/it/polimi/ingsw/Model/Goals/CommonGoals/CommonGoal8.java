package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal number 8 in the UML model.
 * Common Goal description: Two columns each formed by 6 different types of tiles
 */
public class CommonGoal8 extends CommonGoal {

    /**
     * isAchieved() method checks whether the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true if the Goal is achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        // typesFound stores how many types the algorithm already found
        Set<TypeEnum> typesFound = new HashSet<>();

        // number of valid columns found
        int validColumns = 0;

        if(bookshelf.isEmpty()) {
            return false;
        }

        for(int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for(int j = 0; j < bookshelf.getRowDimension(); j++) {
                if(bookshelf.get(i, j) == null) {
                    break; //no point in checking that column, a full column of different types needed
                } else {
                    typesFound.add(bookshelf.get(i, j).getType());
                }
            }
            if(typesFound.size() == 6) {
                validColumns++;
                typesFound.clear();
            }
        }

        return validColumns >= 2;
    }
}
