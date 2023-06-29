package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoal8Test {

    CommonGoal8 commonGoal8 = new CommonGoal8();

    /**
     * This method tests the isAchieved method in CommonGoal8 class.
     */
    @Test
    public void getGoalName() {
        assert (commonGoal8.getGoalName().equals("CommonGoal8"));
    }

    /**
     * This method tests the getGoalDescription method in CommonGoal8 class.
     */
    @Test
    public void getGoalDescriptionTest() {
        assert (commonGoal8.getGoalDescription().equals("Two columns each having no duplicate types.\nThe two columns may have the same combination of tiles."));
    }

    /**
     * This method tests the getGoalFileNumber method in CommonGoal8 class.
     */
    @Test
    public void getGoalFileNumberTest() {
        assert (commonGoal8.getGoalFileNumber() == "9");
    }

/**
     * This method tests the isAchieved method in CommonGoal8 class.
     */
    @Test
    @DisplayName("Test with valid columns but some identical combinations")
    public void bookshelfSomeIdenticalCombinations() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test1.json");
        assertTrue("The bookshelf with some identical combinations should be ok", commonGoal8.isAchieved(bookshelf));
    }

    /**
     * This method tests the isAchieved method in CommonGoal8 class.
     */
    @Test
    @DisplayName("Test with only one type of tile in the bookshelf")
    public void bookshelfOnlyOneType() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test2.json");
        assertFalse("The bookshelf with with only one type of tile in the bookshelf should not be ok", commonGoal8.isAchieved(bookshelf));
    }

    /**
     * This method tests the isAchieved method in CommonGoal8 class.
     */
    @Test
    @DisplayName("Test with different columns but no valid ones")
    public void bookshelfNoValidColumns() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test3.json");
        assertFalse("The bookshelf with no valid columns should not check the goal", commonGoal8.isAchieved(bookshelf));
    }

    /**
     * This method tests the isAchieved method in CommonGoal8 class.
     */
    @Test
    @DisplayName("Test with all valid columns")
    public void bookshelfAllValidColumns() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal8Test4.json");
        assertTrue("The bookshelf with all valid columns should be ok", commonGoal8.isAchieved(bookshelf));
    }

}
