package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the CommonGoal5 class.
 */
public class CommonGoal5Test {
    /**
     * Class constructor.
     */
    public CommonGoal5Test() {

    }
    /**
     * CommonGoal5 instance to test.
     */
    CommonGoal5 commonGoal5 = new CommonGoal5();
    /**
     * Bookshelf instance to test.
     */
    Bookshelf bookshelf;

    /**
     * Test if the empty Bookshelf is handled correctly.
     *
     * @throws IOException if there is an error loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal5.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with less than 8 elements of one kind is handled correctly.
     *
     * @throws IOException if there is an error loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with less than 8 elements of one kind is handled correctly")
    public void bookshelfWithLessThan8ElementsOfOneKindTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal5Test0.json");
        assertFalse("The bookshelf with less than 8 elements of one kind should not achieve the goal", commonGoal5.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with exactly 8 elements of one kind achieves the goal.
     *
     * @throws IOException if there is an error loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with exactly 8 elements of one kind achieves the goal")
    public void bookshelfWith8ElementsOfOneKindTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal5Test1.json");
        assertTrue("The bookshelf with 8 elements of one kind should achieve the goal", commonGoal5.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with more than 8 elements of one kind achieves the goal.
     *
     * @throws IOException if there is an error loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with more than 8 elements of one kind achieves the goal")
    public void bookshelfWithMoreThan8ElementsOfOneKindTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal5Test2.json");
        assertTrue("The bookshelf with more than 8 elements of one kind should achieve the goal", commonGoal5.isAchieved(bookshelf));
    }

    /**
     * Test if the name of the goal is correct.
     */
    @Test
    public void getGoalNameTest() {
        assertTrue("The name should be Common Goal 5", commonGoal5.getGoalName().equals("CommonGoal5"));
    }

    /**
     * Test if the description of the goal is correct.
     */
    @Test
    public void getGoalDescription() {
        assertTrue("The description should be \"Have 8 elements of the same type in the whole Bookshelf\"", commonGoal5.getGoalDescription().equals("Eight tiles of the same type in the whole bookshelf.\nNo restrictions on the position of the tiles."));
    }

    /**
     * Test if the file number of the goal is correct.
     */
    @Test
    public void getGoalFileNumberTest() {
        assertTrue("The file number should be 6", commonGoal5.getGoalFileNumber() == "6");
    }

}
