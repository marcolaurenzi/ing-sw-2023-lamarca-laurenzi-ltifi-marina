package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoal2Test {
    CommonGoal2 commonGoal2 = new CommonGoal2();
    Bookshelf bookshelf;
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the bookshelf with 0 tiles of the same type in the corners achives the goal")
    public void zeroBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/ccommonGoal2Test.json", 0);
        assertFalse("The bookshelf with 0 tiles of the same type in the corners does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the bookshelf with 2 tiles of the same type in the corners achieves the goal")
    public void twoBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/ccommonGoal2Test.json", 1);
        assertFalse("The bookshelf with 2 tiles of the same type in the corners does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the bookshelf with 3 tiles of the same type in the corners achieves the goal")
    public void threeBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal2Test.json", 2);
        assertFalse("The bookshelf with 3 tiles of the same type in the corners does not achieve the goal", commonGoal2.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test if the Bookshelf with 4 tiles of the same type in the corners achieves the goal")

    public void correctBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal2Test.json", 3);
        assertTrue("The bookshelf with 4 tiles of the same type in the corners must the goal", commonGoal2.isAchieved(bookshelf));
    }
}
