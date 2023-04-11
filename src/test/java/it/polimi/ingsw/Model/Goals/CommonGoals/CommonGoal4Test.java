package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.*;
import jdk.jshell.execution.Util;
import org.junit.Test;

import java.awt.print.Book;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * testing isAchieved() method for CommonGoal4
 *
 */
public class CommonGoal4Test {

    final CommonGoal goal = new CommonGoal4();

    /**
     * Testing the corner case where the pointer is null
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
    public void nullBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test.JSON", 0);
        assertEquals(false, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where there are 3 columns with exactly 3 different types in each column
     *
     * @throws IOException
     */
    @Test
    public void threeColumnsBookshelf() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test.JSON", 1);
        assertEquals(true, goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where there are only 2 columns with 4 different types in each column
     *
     * @throws IOException
     */
    @Test
    public void twoColumnsBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test.JSON", 2);
        assertEquals(false, goal.isAchieved(bookshelf));
    }
}
