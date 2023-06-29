package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the CommonGoal3 class.
 */
public class CommonGoal3Test {

    /**
     * Constructor for the CommonGoal3Test class.
     */
    CommonGoal3 commonGoal3 = new CommonGoal3();


    /**
     * This method tests the isAchieved method in CommonGoal3 class.
     */
    @Test
    public void getGoalName() {
        assert (commonGoal3.getGoalName().equals("CommonGoal3"));
    }

    /**
     * This method tests the getGoalDescription method in CommonGoal3 class.
     */
    @Test
    public void getGoalDescriptionTest() {
        assert (commonGoal3.getGoalDescription().equals("At least two 2x2 squares of tiles of the same type.\nDifferent squares may have different types."));
    }

    /**
     * This method tests the getGoalFileNumber method in CommonGoal3 class.
     */
    @Test
    public void getGoalFileNumberTest() {
        assert (commonGoal3.getGoalFileNumber() == "4");
    }

    /**
     * This method tests the isAchieved method in CommonGoal3 class.
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    @DisplayName("Testing corner case where the whole matrix is void")
    public void voidBookshelfTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test0.json");
        assertEquals(false, commonGoal3.isAchieved(bookshelf));
    }

    /**
     * This method tests the isAchieved method in CommonGoal3 class.
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */
    @Test
    @DisplayName("Testing the corner case where there are exactly 2 groups")
    public void enoughGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test1.json");
        assertEquals(true, commonGoal3.isAchieved(bookshelf));
    }

    /**
     * This method tests the isAchieved method in CommonGoal3 class.
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */

    @Test
    @DisplayName("Testing the corner case where there is only one group")
    public void lessGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test2.json");
        assertEquals(false, commonGoal3.isAchieved(bookshelf));
    }

    /**
     * This method tests the isAchieved method in CommonGoal3 class.
     * @throws IOException if an I/O error occurs while loading the bookshelf from a file
     */

    @Test
    @DisplayName("Testing the corner case where there are enough groups, but they are not isolated")
    public void invalidGroupsTest() throws IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "commonGoal3Test3.json");
        assertEquals(true, commonGoal3.isAchieved(bookshelf));
    }

}





