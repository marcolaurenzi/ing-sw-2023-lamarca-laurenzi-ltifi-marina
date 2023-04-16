package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoal11Test {
    CommonGoal11 commonGoal11 = new CommonGoal11();
    Bookshelf bookshelf;
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal11.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with one column with a tile placed in the wrong position prevent the goal from being achieved")
    public void oneColumnWrongTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test.json", 4);
        assertFalse("The bookshelf with one column that progressively have one tile more than the previous one should not achieve the goal", commonGoal11.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with one column in which the tile is missing prevent the goal from being achieved")
    public void oneTileMissingWrongTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test.json", 5);
        assertFalse("The bookshelf with one column that progressively have one tile more than the previous one should not achieve the goal", commonGoal11.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile less than the previous one is handled correctly get(1, 4) != null")
    public void bookshelfWith5ColumnsTest1() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test.json", 1);
        assertTrue("The bookshelf with 5 columns that progressively have one tile less than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(0, 4) == null")
    public void bookshelfWith5ColumnsTest2() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test.json", 0);
        assertTrue("The bookshelf with 5 columns that progressively have one tile more than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(1, 0) != null")
    public void bookshelfWith5ColumnsTest3() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test.json", 2);
        assertTrue("The bookshelf with 5 columns that progressively have one tile more than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 columns that progressively have one tile more than the previous one is handled correctly get(0, 0) != null")
    public void bookshelfWith5ColumnsTest4() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal11Test.json", 3);
        assertTrue("The bookshelf with 5 columns that progressively have one tile more than the previous one should achieve the goal", commonGoal11.isAchieved(bookshelf));
    }

}