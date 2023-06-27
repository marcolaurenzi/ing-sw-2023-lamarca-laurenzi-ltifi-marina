package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import it.polimi.ingsw.Model.*;


import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Unit tests for the CommonGoal01 class for goal 0.
 */
public class CommonGoal0Test {
    /**
     * Constructor for the CommonGoal0Test class.
     */
    public CommonGoal0Test() {
    }
    final CommonGoal goal = new CommonGoal01(2, 6);

    /**
     * Test that verifies if a NullPointerException is thrown when a null Bookshelf is passed to the isAchieved method.
     *
     * @throws IOException if an I/O error occurs while loading the Bookshelf from file.
     */
    @Test
    public void throwsNullPointerException() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookshelfTest.json");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Test that verifies if the isAchieved method returns false when an empty Bookshelf is passed.
     *
     * @throws IOException if an I/O error occurs while loading the Bookshelf from file.
     */
    @Test
    public void returnFalseWhenEmptyBookshelfPassed() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Test that verifies if the isAchieved method returns true when a specific Bookshelf is passed.
     *
     * @throws IOException if an I/O error occurs while loading the Bookshelf from file.
     */
    @Test
    public void testingCommonGoal0Test0Bookshelf() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal0Test0Bookshelf.json");
        assertTrue(goal.isAchieved(bookshelf));
    }

    /**
     * Test that verifies if the isAchieved method returns false when a specific Bookshelf is passed.
     *
     * @throws IOException if an I/O error occurs while loading the Bookshelf from file.
     */
    @Test
    public void testingCommonGoal0Test1Bookshelf() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal0Test1Bookshelf.json");
        assertFalse(goal.isAchieved(bookshelf));
    }
}