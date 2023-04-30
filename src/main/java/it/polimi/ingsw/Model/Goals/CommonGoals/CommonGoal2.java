package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

/**
 * This class represents the CommonGoal number 2
 * This goal is achieved when there are 4 tiles of the same ElementType placed in the 4 corners of the player's Bookshelf
 */
public class CommonGoal2 extends CommonGoal{

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {
        TypeEnum last;
        last = null;
        for(int i = 0; i < bookshelf.getColumnDimension(); i += 5){
            for(int j = 0; j < bookshelf.getRowDimension(); j += 4){
                if(bookshelf.get(i,j) == null){
                    return false;
                }
                if(last == null) {
                    last = bookshelf.get(i, j).getType();
                }
                if(!bookshelf.get(i,j).getType().equals(last)){
                    return false;
                }
            }
        }
        return true;
    }
    public String printGoal() {
        return "CommonGoal2";
    }
}
