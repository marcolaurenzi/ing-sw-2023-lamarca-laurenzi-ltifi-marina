package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the CommonGoal6 class.
 */
public class CommonGoal6Test {
    /**
     * Class constructor.
     */
    public CommonGoal6Test() {

    }
    /**
     * CommonGoal6 instance to test.
     */
    CommonGoal6 commonGoal6 = new CommonGoal6();
    /**
     * Bookshelf instance to test.
     */
    Bookshelf bookshelf;

    /**
     * Test if the empty Bookshelf is handled correctly.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 4 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest1() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test0.json");
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 4 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest2() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test1.json");
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 4 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest3() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test2.json");
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 4 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest4() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test3.json");
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest1() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test4.json");
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest2() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test5.json");
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest3() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test6.json");
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf with 5 elements on a diagonal achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest4() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test7.json");
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }

    /**
     * Test if the Bookshelf filled all with the same element achieves the goal.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file.
     */
    @Test
    @DisplayName("Test if the Bookshelf filled all with the same element achieves the goal")
    public void bookshelfFilledAllWithTheSameElementTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal6Test8.json");
        assertTrue("The bookshelf filled all with the same element should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
}
