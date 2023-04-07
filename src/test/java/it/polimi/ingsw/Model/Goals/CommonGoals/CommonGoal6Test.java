package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoal6Test {
    CommonGoal6 commonGoal6 = new CommonGoal6();
    Bookshelf bookshelf;
    @Test
    @DisplayName("Test if the empty Bookshelf is handled correctly")
    public void emptyBookshelfTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/bookshelfOfNullTest.json");
        assertFalse("The empty bookshelf should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest1() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 0);
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest2() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 1);
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest3() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 2);
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 4 elements on a diagonal achieves the goal")
    public void bookshelfWith4ElementsOnADiagonalTest4() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 3);
        assertFalse("The bookshelf with 4 elements on a diagonal should not achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest1() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 4);
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest2() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 5);
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest3() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 6);
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf with 5 elements on a diagonal achieves the goal")
    public void bookshelfWith5ElementsOnADiagonalTest4() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 7);
        assertTrue("The bookshelf with 5 elements on a diagonal should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
    @Test
    @DisplayName("Test if the Bookshelf filled all with the same element achieves the goal")
    public void bookshelfFilledAllWithTheSameElementTest() throws IOException {
        bookshelf = Utils.loadBookshelfFromFile("src/test/testFiles/commonGoal6Test.json", 8);
        assertTrue("The bookshelf filled all with the same element should achieve the goal", commonGoal6.isAchieved(bookshelf));
    }
}
