package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the CommonGoal9 class.
 */
public class CommonGoal9Test {
    CommonGoal9 commonGoal9 = new CommonGoal9();
    Bookshelf bookshelf;

    /**
     * Test to check if the empty Bookshelf is handled correctly.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal9.isAchieved(bookshelf));
    }

    /**
     * Test with all valid different rows.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test with all valid different rows")
    public void bookshelfWithAllValidDifferentRows() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal9Test.json", 0);
        assertTrue("The bookshelf with all valid different rows should be ok", commonGoal9.isAchieved(bookshelf));
    }

    /**
     * Test with all valid same rows.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test with all valid same rows")
    public void bookshelfAllValidRowsSameCombination() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal9Test.json", 1);
        assertTrue("The bookshelf with all valid same rows should be ok", commonGoal9.isAchieved(bookshelf));
    }

    /**
     * Test where every row has a different combination of items but there is a column with the same type of items.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test where every row has a different combination of items but there is a column with the same type of items")
    public void bookshelfDifferentCombinationRowsOneColumnSameType() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal9Test.json", 2);
        assertTrue("The bookshelf where every row has a different combination of items but there is a column with the same type of items", commonGoal9.isAchieved(bookshelf));
    }

    /**
     * Test with no valid rows.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    @DisplayName("Test with no valid rows")
    public void bookshelfNoValidRows() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal9Test.json", 3);
        assertFalse("The bookshelf with no valid rows should not check the goal", commonGoal9.isAchieved(bookshelf));
    }
}
