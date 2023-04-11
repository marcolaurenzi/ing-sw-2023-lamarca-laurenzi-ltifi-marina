package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.TypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class CommonGoal10 extends CommonGoal{

    /**
     *
     * @param bookshelf
     *
     * @return
     */
    @Override
    public boolean isAchieved(Bookshelf bookshelf) {

        int numberOfRows = bookshelf.getColumnDimension();
        int numberOfColumns = bookshelf.getRowDimension();

        Set<TypeEnum> temp = new HashSet<>();
        int count = 0;
        TypeEnum currTypeEnum;

        for(int i = 0; i < numberOfRows-2; i++) {
            for(int j = 0; j < numberOfColumns-2; j++) {
                if(bookshelf.get(i,j) != null && bookshelf.get(i+2,j) != null &&
                        bookshelf.get(i+1,j+1) != null && bookshelf.get(i,j+2) != null &&
                        bookshelf.get(i+2,j+2) != null) {
                    currTypeEnum = bookshelf.get(i,j).getType();
                    if(bookshelf.get(i,j).getType() == currTypeEnum &&
                            bookshelf.get(i+2,j).getType() == currTypeEnum &&
                            bookshelf.get(i+2,j+2).getType() == currTypeEnum &&
                            bookshelf.get(i,j+2).getType() == currTypeEnum &&
                            bookshelf.get(i+1,j+1).getType() == currTypeEnum){

                        if(bookshelf.get(i+1,j) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i+1,j).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i+1,j).getType());
                            }
                        }
                        if(bookshelf.get(i,j+1) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i,j+2).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i,j+1).getType());
                            }
                        }
                        if(bookshelf.get(i+2,j+1) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i+2,j+1).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i+2,j+1).getType());
                            }
                        }
                        if(bookshelf.get(i+1,j+2) == null) {
                            count++;
                        }
                        else {
                            if(bookshelf.get(i+1,j+2).getType() != currTypeEnum) {
                                temp.add(bookshelf.get(i+1,j+2).getType());
                            }
                        }

                        if(count + temp.size() == 4) {
                            return true;
                        }
                        count=0;
                        temp.clear();
                    }
                }

            }
        }
        return false;

    }
}
