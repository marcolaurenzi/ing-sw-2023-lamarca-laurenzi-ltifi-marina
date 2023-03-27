package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.BookShelf;
import it.polimi.ingsw.Model.TypeEnum;

/**
 * This class represents the CommonGoal number 1
 * This goal is achieved when there are 4 tiles of the same ElementType placed in the 4 corners of the player's Bookshelf
 */
public class CommonGoal1 extends CommonGoal{
    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookShelf is the reference to the actual BookShelf Object where the Algorithm works on
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(BookShelf bookShelf) {
        TypeEnum last;
        last = null;
        for(int i = 0; i < 6;i += 5){
            for(int j = 0; j < 5; j += 4){
                if(bookShelf.get(i,j) == null){
                    return false;
                }
                if(last == null) {
                    last = bookShelf.get(i, j).getType();
                }
                if(!bookShelf.get(i,j).getType().equals(last)){
                    return false;
                }
            }
        }
        return true;
    }
}
