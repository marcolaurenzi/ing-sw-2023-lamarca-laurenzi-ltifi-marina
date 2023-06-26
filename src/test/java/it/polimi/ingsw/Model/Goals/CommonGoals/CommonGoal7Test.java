package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit tests for CommonGoal7 class.
 */
public class CommonGoal7Test {
    final CommonGoal goal = new CommonGoal7();

    /**
     * Testing the corner case where the pointer is null.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void nullPointerTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookShelfTest.JSON");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the whole matrix is void.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void voidBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 0);
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has exactly 4 rows with exactly 4 different types for each row.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void enoughRowsEnoughTypesTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 1);
        assertTrue(goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has 4 rows with more than 4 different types.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void moreTypesTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 2);
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has only 3 rows with exactly 4 different types.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void lessRowsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 3);
        assertFalse(goal.isAchieved(bookshelf));
    }
}
