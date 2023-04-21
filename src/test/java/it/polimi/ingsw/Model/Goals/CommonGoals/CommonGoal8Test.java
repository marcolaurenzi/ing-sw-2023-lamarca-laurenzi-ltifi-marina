package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoal8Test {
    CommonGoal8 commonGoal8 = new CommonGoal8();
    Bookshelf bookshelf;

    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal8.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test with two valid columns formed by six different types of tiles")
    public void bookshelfTwoValidColumns() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test.json", 0);
        assertTrue("The bookshelf with two valid columns should be ok", commonGoal8.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test with valid columns but some identical combinations")
    public void bookshelfSomeIdenticalCombinations() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test.json", 1);
        assertTrue("The bookshelf with some identical combinations should be ok", commonGoal8.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test with only one type of tile in the bookshelf")
    public void bookshelfOnlyOneType() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test.json", 2);
        assertFalse("The bookshelf with with only one type of tile in the bookshelf should not be ok", commonGoal8.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test with different columns but no valid ones")
    public void bookshelfNoValidColumns() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test.json", 3);
        assertFalse("The bookshelf with no valid columns should not check the goal", commonGoal8.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test with all valid columns")
    public void bookshelfAllValidColumns() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test.json", 4);
        assertTrue("The bookshelf with all valid columns should be ok", commonGoal8.isAchieved(bookshelf));
    }
}