package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoal5Test {
    CommonGoal5 commonGoal5 = new CommonGoal5();
    Bookshelf bookshelf;
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal5.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with less than 8 elements of one kind is handled correctly")
    public void bookshelfWithLessThan8ElementsOfOneKindTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal5Test.json", 0);
        assertFalse("The bookshelf with less than 8 elements of one kind should not achieve the goal", commonGoal5.isAchieved(bookshelf));
    }

    @Test
    @DisplayName("Test if the Bookshelf with exactly 8 elements of one kind achieves the goal")
    public void bookshelfWith8ElementsOfOneKindTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal5Test.json", 1);
        assertTrue("The bookshelf with 8 elements of one kind should achieve the goal", commonGoal5.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with more than 8 elements of one kind achieves the goal")
    public void bookshelfWithMoreThan8ElementsOfOneKindTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal5Test.json", 2);
        assertTrue("The bookshelf with more than 8 elements of one kind should achieve the goal", commonGoal5.isAchieved(bookshelf));
    }

}
