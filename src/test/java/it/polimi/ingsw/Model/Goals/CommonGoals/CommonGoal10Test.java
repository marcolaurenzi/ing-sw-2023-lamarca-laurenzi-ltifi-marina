package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit tests for the CommonGoal10 class.
 */
public class CommonGoal10Test {
    /**
     * Constructs a CommonGoal10Test object.
     */
    public CommonGoal10Test() {
    }
    final CommonGoal goal = new CommonGoal10();

    /**
     * Tests the scenario where the pointer is null.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void nullPointerTest() throws IOException {
        // Load the bookshelf from a json file containing null values
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookshelfTest.json");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Tests the scenario where the bookshelf is completely empty.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void voidBookshelfTest() throws IOException {
        // Load the bookshelf from a json file with an empty matrix
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal10Test0.json");
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Tests the scenario where the bookshelf contains only one 'X', which is the minimum allowed value.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void oneXBookshelfTest() throws IOException {
        // Load the bookshelf from a json file with a matrix containing one 'X'
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal10Test1.json");
        assertTrue(goal.isAchieved(bookshelf));
    }
}
