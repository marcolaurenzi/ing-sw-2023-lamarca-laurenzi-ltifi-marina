package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the CommonGoal11 class.
 */
public class CommonGoal11Test {
    /**
     * Class constructor.
     */
    public CommonGoal11Test() {
    }
    /**
     * CommonGoal11 instance to test.
     */
    CommonGoal11 commonGoal11 = new CommonGoal11();
    /**
     * Bookshelf instance to test.
     */
    Bookshelf bookshelf;

    /**
     * Test if the empty Bookshelf is handled correctly.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with one column with a tile placed in the wrong position prevents the goal from being achieved.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the Bookshelf with one column with a tile placed in the wrong position prevent the goal from being achieved")
    public void oneColumnWrongTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test4.json");
        assertFalse("The bookshelf with one column that progressively has one tile more than the previous one should not achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with one column in which the tile is missing prevents the goal from being achieved.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the Bookshelf with one column in which the tile is missing prevent the goal from being achieved")
    public void oneTileMissingWrongTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test5.json");
        assertFalse("The bookshelf with one column that progressively has one tile more than the previous one should not achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 columns that progressively have one tile less than the previous one is handled correctly get(1, 4) != null.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile less than the previous one is handled correctly get(1, 4) != null")
    public void bookshelfWith5ColumnsTest1() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test1.json");
        assertTrue("The bookshelf with 5 columns that progressively has one tile less than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(0, 4) == null.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(0, 4) == null")
    public void bookshelfWith5ColumnsTest2() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test0.json");
        assertTrue("The bookshelf with 5 columns that progressively has one tile more than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(1, 0) != null.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(1, 0) != null")
    public void bookshelfWith5ColumnsTest3() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test2.json");
        assertTrue("The bookshelf with 5 columns that progressively has one tile more than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(0, 0) != null.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(0, 0) != null")
    public void bookshelfWith5ColumnsTest4() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test3.json");
        assertTrue("The bookshelf with 5 columns that progressively has one tile more than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf filled all with the same element achieves the goal.
     */
    @Test
    public void getGoalNameTest() {
        assertEquals("CommonGoal11", commonGoal11.getGoalName());
    }

    /**
     * Test if the Bookshelf filled all with the same element achieves the goal.
     */
    @Test
    public void getGoalDescriptionTest() {
        assert (commonGoal11.getGoalDescription().equals("""
                Five columns of increasing or decreasing height.
                Starting from the shortest column and going up, each next column must have exactly one more tile.
                The tiles may be of any type."""));
    }

    /**
     * Test if the Bookshelf filled all with the same element achieves the goal.
     */
    @Test
    public void getGoalFileNumberTest() {
        assert (commonGoal11.getGoalFileNumber() == "12");
    }

}
