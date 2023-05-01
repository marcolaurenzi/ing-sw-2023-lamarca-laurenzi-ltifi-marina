package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal2;
import jdk.jfr.Name;
import org.junit.Before;
import org.junit.Test;

public class CommonGoalPointStackTest {
    private CommonGoalPointStack commonGoalPointStack;
    @Before
    @Name("CommonGoalPointStack initialization")
    public void setUp() {
        commonGoalPointStack = new CommonGoalPointStack(new CommonGoal2(), 4);
    }

    @Test
    public void drawTest() {
        assert(commonGoalPointStack.draw() == 8);
        assert(commonGoalPointStack.draw() == 6);
        assert(commonGoalPointStack.draw() == 4);
        assert(commonGoalPointStack.draw() == 2);
        assert(commonGoalPointStack.draw() == null);
    }

    @Test
    public void getTopPointsTest() {
        assert(commonGoalPointStack.getTopPoints() == 8);
        commonGoalPointStack.draw();
        assert(commonGoalPointStack.getTopPoints() == 6);
        commonGoalPointStack.draw();
        assert(commonGoalPointStack.getTopPoints() == 4);
        commonGoalPointStack.draw();
        assert(commonGoalPointStack.getTopPoints() == 2);
        commonGoalPointStack.draw();
        assert(commonGoalPointStack.getTopPoints() == null);
    }






}
