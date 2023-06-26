package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal2;
import jdk.jfr.Name;
import org.junit.Before;
import org.junit.Test;

/**
 * Test unit for the CommonGoalPointStack class.
 */
public class CommonGoalPointStackTest {
    /**
     * CommonGoalPointStack instance to test.
     */
    public CommonGoalPointStackTest() {

    }

    /**
     * CommonGoalPointStack instance to test.
     */

    private CommonGoalPointStack commonGoalPointStack;

    /**
     * Initializes the CommonGoalPointStack for testing.
     */
    @Before
    @Name("CommonGoalPointStack initialization")
    public void setUp() {
        commonGoalPointStack = new CommonGoalPointStack(new CommonGoal2(), 4);
    }

    /**
     * Tests the draw() method of CommonGoalPointStack.
     * The method asserts that the points drawn from the stack are in descending order.
     */
    @Test
    public void drawTest() {
        assert(commonGoalPointStack.draw() == 8);
        assert(commonGoalPointStack.draw() == 6);
        assert(commonGoalPointStack.draw() == 4);
        assert(commonGoalPointStack.draw() == 2);
        assert(commonGoalPointStack.draw() == null);
    }

    /**
     * Tests the getTopPoints() method of CommonGoalPointStack.
     * The method asserts that the top points of the stack are in descending order without actually drawing them.
     */
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
