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
    /**
     * Constructor for the CommonGoal1Test class.
     */
    public CommonGoal1Test() {
    }
    /**
     * CommonGoal01 instance to test.
     */
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

    /**
     * Test case to verify that isAchieved() method returns true for a specific Bookshelf.
     */
    @Test
    public void getGoalNameTest() {
        CommonGoal01 goal = new CommonGoal01(2, 2);
        assertTrue(goal.getGoalName().equals("CommonGoal0"));
        CommonGoal01 goal1 = new CommonGoal01(4, 4);
        assertTrue(goal1.getGoalName().equals("CommonGoal1"));
    }

    /**
     * Test case to verify that isAchieved() method returns true for a specific Bookshelf.
     */
    @Test
    public void getGoalDescriptionTest() {
        CommonGoal01 goal = new CommonGoal01(2, 2);
        assertTrue(goal.getGoalDescription().equals("2 groups each containing at least 2 tiles of the same type.\n" +
                "Different groups may have tiles of different types."));
        CommonGoal01 goal1 = new CommonGoal01(4, 4);
        assertTrue(goal1.getGoalDescription().equals("4 groups each containing at least 4 tiles of the same type.\n" +
                "Different groups may have tiles of different types."));
    }


    /**
     * Test case to verify the file number.
     */
    @Test
    public void getGoalFileNumberTest() {
        CommonGoal01 goal = new CommonGoal01(2, 2);
        assertTrue(goal.getGoalFileNumber() == "2");
        CommonGoal01 goal1 = new CommonGoal01(4, 4);
        assertTrue(goal1.getGoalFileNumber() == "1");
    }

}