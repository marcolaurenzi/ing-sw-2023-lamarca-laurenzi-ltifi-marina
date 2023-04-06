package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CommonGoal1Test {
    final CommonGoal goal = new CommonGoal01(4, 4);

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
    public void testingCommonGoal1Test0Bookshelf () throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal1Test0Bookshelf.json");
        assertTrue(goal.isAchieved(bookshelf));
    }

    @Test
    public void testingCommonGoal1Test1Bookshelf () throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal1Test1Bookshelf.json");
        assertFalse(goal.isAchieved(bookshelf));
    }
}