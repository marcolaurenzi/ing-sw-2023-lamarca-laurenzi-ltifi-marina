package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CommonGoal4Test {

    /**
     * testing isAchieved() method
     */
    @Test
    public void voidMatrixCheck() {

        BookShelf bookShelf = new BookShelf();
        CommonGoal commonGoal4 = new CommonGoal4();

        assertEquals(commonGoal4.isAchieved(bookShelf), false);
    }
}
