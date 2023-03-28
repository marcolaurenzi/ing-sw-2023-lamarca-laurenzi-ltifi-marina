package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * testing isAchieved() method
 */
public class CommonGoal4Test {


    @Test
    public void voidMatrixCheck() {

        Bookshelf bookshelf = new Bookshelf();
        CommonGoal commonGoal4 = new CommonGoal4();

        assertEquals(false, commonGoal4.isAchieved(bookshelf));
    }
}
