package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

/**
 * This class represents the CommonGoal number 6
 * This goal is achieved when there are 5 diagonally tiles of the same ElementType
 */
public class CommonGoal6 extends CommonGoal{

    /**
     * isAchieved() method checks weather the CommonGoal is achieved or not in the given BookShelf
     * @param bookshelf is the reference to the actual BookShelf Object where the Algorithm works on
     *
     * @return the method returns true weather the Goals is Achieved and false otherwise
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {
        TypeEnum last;
        int count = 0;
        last = null;
        for(int i = 0; i < bookshelf.getColumnDimension() -1 ; i++){
            if(bookshelf.get(i,i) == null)
            {
                break;
            }
            if(last == null){
                last = bookshelf.get(i,i).getType();
            }
            if(bookshelf.get(i,i).getType().equals(last)){
                count++;
            }
        }
        if(count == 5){
            return true;
        }
        count = 0;
        last = null;
        for(int i = 1; i < bookshelf.getColumnDimension(); i++){
            if(bookshelf.get(i, i - 1) == null){
                break;
            }
            if(last == null){
                last = bookshelf.get(i, i - 1).getType();
            }
            if(bookshelf.get(i,i - 1).getType().equals(last)){
                count++;
            }
        }
        if(count == 5){
            return true;
        }
        count = 0;
        last = null;
        for(int i = 0; i < bookshelf.getColumnDimension() - 1; i++){
            if(bookshelf.get(i, bookshelf.getRowDimension() - i - 1) == null){
                break;
            }
            if(last == null){
                last = bookshelf.get(i, bookshelf.getRowDimension() - i - 1).getType();
            }
            if(bookshelf.get(i, bookshelf.getRowDimension() - i - 1).getType().equals(last)){
                count++;
            }
        }
        if(count == 5){
            return true;
        }
        count = 0;
        last = null;
        for(int i = 1; i < bookshelf.getColumnDimension(); i++){
            if(bookshelf.get(i, bookshelf.getRowDimension() - i) == null){
                break;
            }
            if(last == null){
                last = bookshelf.get(i, bookshelf.getRowDimension() - i).getType();
            }
            if(bookshelf.get(i, bookshelf.getRowDimension() - i).getType().equals(last)){
                count++;
            }
        }

        return count == 5;
    }
    public String printGoal() {
        return "CommonGoal6";
    }
}
