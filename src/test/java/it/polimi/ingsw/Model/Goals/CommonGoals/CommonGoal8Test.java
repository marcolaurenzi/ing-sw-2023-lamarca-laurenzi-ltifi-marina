package it.polimi.ingsw.Model.Goals.CommonGoals;

import org.junit.jupiter.api.Test;

public class CommonGoal8Test {

    CommonGoal8 commonGoal8 = new CommonGoal8();

    /**
     * This method tests the isAchieved method in CommonGoal3 class.
     */
    @Test
    public void getGoalName() {
        assert (commonGoal8.getGoalName().equals("CommonGoal8"));
    }

    /**
     * This method tests the getGoalDescription method in CommonGoal3 class.
     */
    @Test
    public void getGoalDescriptionTest() {
        assert (commonGoal8.getGoalDescription().equals("Two columns each having no duplicate types.\nThe two columns may have the same combination of tiles."));
    }

    /**
     * This method tests the getGoalFileNumber method in CommonGoal3 class.
     */
    @Test
    public void getGoalFileNumberTest() {
        assert (commonGoal8.getGoalFileNumber() == "9");
    }

}
