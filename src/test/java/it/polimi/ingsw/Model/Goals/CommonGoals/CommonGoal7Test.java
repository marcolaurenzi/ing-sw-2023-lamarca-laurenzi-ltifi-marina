package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CommonGoal7Test {
    final CommonGoal goal = new CommonGoal7();

    /**
     * Testing the corner case where the pointer is null
     *
     * @throws IOException
     */
    @Test
    public void nullPointerTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookShelfTest.JSON");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Testing corner case where the whole matrix is void
     *
     * @throws IOException
     */
    @Test
    public void voidBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 0);
        assertEquals(false, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has exactly 4 rows with exactly 4 different types for each row
     *
     * @throws IOException
     */
    @Test
    public void enoughRowsEnoughTypesTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 1);
        assertEquals(true, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has 4 rows with more than 4 different types
     *
     * @throws IOException
     */
    @Test
    public void moreTypesTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 2);
        assertEquals(false, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has only 3 rows with exactly 4 different types
     *
     * @throws IOException
     */
    @Test
    public void lessRowsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal7Test.JSON", 3);
        assertEquals(false, goal.isAchieved(bookshelf));
    }
}
