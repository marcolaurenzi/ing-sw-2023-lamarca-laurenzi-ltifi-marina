package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * testing isAchieved() method for CommonGoal4
 *
 */
public class CommonGoal3Test {

    final CommonGoal goal = new CommonGoal3();

    @Test
    @DisplayName("Testing the corner case where the pointer is null")
    public void nullPointerTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookShelfTest.JSON");
        assertThrows(NullPointerException.class, () -> goal.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Testing corner case where the whole matrix is void")
    public void voidBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 0);
        assertEquals(false, goal.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Testing the corner case where there are exactly 2 groups")
    public void enoughGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 1);
        assertEquals(true, goal.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Testing the corner case where there is only one group")
    public void lessGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 2);
        assertEquals(false, goal.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Testing the corner case where there are enough groups, but they are not isolated")
    public void invalidGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test.JSON", 3);
        assertEquals(false, goal.isAchieved(bookshelf));
    }
}
