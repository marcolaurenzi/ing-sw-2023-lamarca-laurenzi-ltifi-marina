package it.polimi.ingsw.Model.Goals.CommonGoals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonGoal3Test {

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


}
