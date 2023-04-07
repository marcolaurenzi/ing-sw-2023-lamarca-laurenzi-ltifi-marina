package it.polimi.ingsw.Model.Goals.CommonGoals;

import org.junit.Test;
import it.polimi.ingsw.Model.*;


import java.io.IOException;

import static org.junit.Assert.*;

public class CommonGoal0Test {
    final CommonGoal goal = new CommonGoal01(2, 6);

    @Test
    public void throwsNullPointerException() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/nullBookshelfTest.json");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    @Test
    public void returnFalseWhenEmptyBookshelfPassed() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/bookshelfOfNullTest.json");
        assertFalse(goal.isAchieved(bookshelf));
    }

    @Test
    public void testingCommonGoal0Test0Bookshelf () throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal0Test0Bookshelf.json");
        assertTrue(goal.isAchieved(bookshelf));
    }

    @Test
    public void testingCommonGoal0Test1Bookshelf () throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal0Test1Bookshelf.json");
        assertFalse(goal.isAchieved(bookshelf));
    }
}