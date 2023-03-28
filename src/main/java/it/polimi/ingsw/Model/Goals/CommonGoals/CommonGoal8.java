package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the CommonGoal number 8 in the UML model.
 * Common Goal description: Two columns each formed by 6 different types of tiles
 */
public class CommonGoal8 extends CommonGoal {

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {

        // times stores how many types the algorithm already found
        Set<TypeEnum> typesFound = new HashSet<>();

        // number of valid columns found
        int validColumns = 0;

        if(bookShelf.isEmpty()) {
            return false;
        }

        for(int i = 0; i<bookShelf.getColumnDimension(); i++) {
            for(int j = 0; j < bookShelf.getColumnDimension(); j++) {
                if(bookShelf.get(i,j) == null) {
                    break; //no point in checking that column, a full column of different types needed
                }
                else {
                    typesFound.add(bookShelf.get(i,j).getType());
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
