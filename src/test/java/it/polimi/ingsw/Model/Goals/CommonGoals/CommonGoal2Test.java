package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the CommonGoal2 class.
 */
public class CommonGoal2Test {
    /**
     * Class constructor.
     */
    public CommonGoal2Test() {

    }
    /**
     * CommonGoal2 instance to test.
     */
    CommonGoal2 commonGoal2 = new CommonGoal2();
    /**
     * Bookshelf instance to test.
     */
    Bookshelf bookshelf;

    /**
     * Test if the empty Bookshelf is handled correctly.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the pointer is null.
     */
    @Test
    public void getGoalNameTest() {
        assertEquals("CommonGoal2", commonGoal2.getGoalName());
    }

    /**
     * Testing the corner case where the pointer is null.
     */
    @Test
    public void getGoalDescriptionTest() {
        assertEquals("Four tiles of the same type at the four corners of the bookshelf.", commonGoal2.getGoalDescription());
    }

    /**
     * Testing the corner case where the pointer is null.
     */
    @Test
    public void getGoalFileNumberTest() {
        assertEquals("3", commonGoal2.getGoalFileNumber());
    }

    /**
     * Test if the bookshelf with 0 tiles of the same type in the corners achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Test if the bookshelf with 0 tiles of the same type in the corners achieves the goal")
    public void zeroBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal2Test0.json");
        assertFalse("The bookshelf with 0 tiles of the same type in the corners does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }

    /**
     * Test if the bookshelf with 2 tiles of the same type in the corners achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Test if the bookshelf with 2 tiles of the same type in the corners achieves the goal")
    public void twoBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal2Test1.json");
        assertFalse("The bookshelf with 2 tiles of the same type in the corners does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }

    /**
     * Test if the bookshelf with 3 tiles of the same type in the corners achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Test if the bookshelf with 3 tiles of the same type in the corners achieves the goal")
    public void threeBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal2Test2.json");
        assertFalse("The bookshelf with 3 tiles of the same type in the corners does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 4 tiles of the same type in the corners achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 4 tiles of the same type in the corners achieves the goal")
    public void correctBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal2Test3.json");
        assertTrue("The bookshelf with 4 tiles of the same type in the corners must achieve the goal", commonGoal2.isAchieved(bookshelf));
    }
}
