package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
/**
 * Unit tests for the CommonGoal01 class for goal 1.
 */
public class CommonGoal1Test {
    final CommonGoal goal = new CommonGoal01(4, 4);

    /**
     * Test case to verify that a NullPointerException is thrown when a null Bookshelf is passed.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    public void throwsNullPointerException() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookshelfTest.json");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Test case to verify that isAchieved() method returns false when an empty Bookshelf is passed.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    public void returnFalseWhenEmptyBookshelfPassed() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Test case to verify that isAchieved() method returns true for a specific Bookshelf.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    public void testingCommonGoal1Test0Bookshelf() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal1Test0Bookshelf.json");
        assertTrue(goal.isAchieved(bookshelf));
    }

    /**
     * Test case to verify that isAchieved() method returns false for a specific Bookshelf.
     *
     * @throws IOException if there is an I/O error.
     */
    @Test
    public void testingCommonGoal1Test1Bookshelf() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal1Test1Bookshelf.json");
        assertFalse(goal.isAchieved(bookshelf));
    }
}