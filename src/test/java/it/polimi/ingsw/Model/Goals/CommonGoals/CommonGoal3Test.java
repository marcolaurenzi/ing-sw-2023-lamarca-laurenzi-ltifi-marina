package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit tests for the CommonGoal3 class.
 */
public class CommonGoal3Test {
    /**
     * Class constructor.
     */
    public CommonGoal3Test() {

    }
    /**
     * CommonGoal3 instance to test.
     */
    final CommonGoal goal = new CommonGoal3();

    /**
     * Tests the isAchieved() method for the corner case where the pointer is null.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Testing the corner case where the pointer is null")
    public void nullPointerTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookShelfTest.JSON");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    /**
     * Tests the isAchieved() method for the corner case where the whole matrix is void.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Testing corner case where the whole matrix is void")
    public void voidBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 0);
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Tests the isAchieved() method for the corner case where there are exactly 2 groups.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Testing the corner case where there are exactly 2 groups")
    public void enoughGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 1);
        assertTrue(goal.isAchieved(bookshelf));
    }

    /**
     * Tests the isAchieved() method for the corner case where there is only one group.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Testing the corner case where there is only one group")
    public void lessGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 2);
        assertFalse(goal.isAchieved(bookshelf));
    }

    /**
     * Tests the isAchieved() method for the corner case where there are enough groups, but they are not isolated.
     *
     * @throws IOException if an I/O error occurs while loading the bookshelf.
     */
    @Test
    @DisplayName("Testing the corner case where there are enough groups, but they are not isolated")
    public void invalidGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 3);
        assertFalse(goal.isAchieved(bookshelf));
    }
}
