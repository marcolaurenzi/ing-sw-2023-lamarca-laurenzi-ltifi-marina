package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CommonGoal10Test {
    final CommonGoal goal = new CommonGoal10();

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
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal10Test.JSON", 0);
        assertEquals(false, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has only one X, the minimum allowed value
     *
     * @throws IOException
     */
    @Test
    public void oneXBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal10Test.JSON", 1);
        assertEquals(true, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where the matrix has an invalid X, an X that's not isolated
     *
     * @throws IOException
     */
    @Test
    public void zeroXBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal10Test.JSON", 2);
        assertEquals(false, goal.isAchieved(bookshelf));
    }
}
