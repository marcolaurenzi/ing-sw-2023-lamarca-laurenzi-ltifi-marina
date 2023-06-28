package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * This class is used to test the isAchieved() method for CommonGoal4.
 */
public class CommonGoal4Test {
    /**
     * Constructor for the CommonGoal4Test class.
     */
    public CommonGoal4Test() {

    }
    /**
     * CommonGoal4 instance to test.
     */
    final CommonGoal goal = new CommonGoal4();

    /**
     * Testing the corner case where the pointer is null.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    public void nullPointerTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookshelfTest.json");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Testing corner case where the whole matrix is void.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    public void voidBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test0.json");
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where there are 3 columns with exactly 3 different types in each column.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    public void enoughColumnsEnoughTypesTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test1.json");
        assertTrue(goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where there are 3 columns with 4 different types in each column.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    public void moreTypesTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test2.json");
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Testing the corner case where there are only 2 columns with 3 different types in each column.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    public void lessColumnsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal4Test3.json");
        assertFalse(goal.isAchieved(bookshelf));
    }
}
