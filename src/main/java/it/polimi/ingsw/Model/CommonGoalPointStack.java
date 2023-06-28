package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class represents the stack of points associated with a common goal.
 * It tracks the points earned for completing the goal by the players.
 */
public class CommonGoalPointStack implements Serializable {
    /**
     * Represents the stack of Common Goal Points.
     */
    private final Stack<Integer> pointStack;
    /**
     * Represents the number of points at the top of the stack.
     */
    private Integer top;
    /**
     * Represents the common goal associated with the stack.
     */
    private final CommonGoal commonGoal;

    /**
     * Constructs a CommonGoalPointStack with the retrieved status.
     *
     * @param commonGoal the common goal associated with the stack
     * @param pointStack the stack of points
     */
    public CommonGoalPointStack(CommonGoal commonGoal, Stack<Integer> pointStack) {
        this.pointStack = pointStack;
        this.commonGoal = commonGoal;
        try {
            this.top = pointStack.peek();
        } catch (EmptyStackException e) {
            this.top = null;
        }
    }

    /**
     * Constructs a CommonGoalPointStack for the specified number of players.
     *
     * @param commonGoal     the common goal associated with the stack
     * @param numberOfPlayers the number of players in the game
     */
    public CommonGoalPointStack(CommonGoal commonGoal, int numberOfPlayers) {
        this.commonGoal = commonGoal;
        pointStack = new Stack<>();

        switch (numberOfPlayers) {
            case 2 -> {
                pointStack.push(4);
                pointStack.push(8);
            }
            case 3 -> {
                pointStack.push(4);
                pointStack.push(6);
                pointStack.push(8);
            }
            case 4 -> {
                pointStack.push(2);
                pointStack.push(4);
                pointStack.push(6);
                pointStack.push(8);
            }
        }
        top = 8;
    }

    /**
     * Draws a point from the stack.
     *
     * @return the drawn point, or null if the stack is empty
     */
    public Integer draw() {
        if (pointStack.isEmpty()) {
            return null;
        } else {
            if (pointStack.size() > 1) {
                top = pointStack.get(pointStack.size() - 2);
            } else {
                top = null;
            }
        }
        return pointStack.pop();
    }

    /**
     * Returns the top points on the stack.
     *
     * @return the top points
     */
    public Integer getTopPoints() {
        return top;
    }

    /**
     * Returns the common goal associated with the stack.
     *
     * @return the common goal
     */
    public CommonGoal getCommonGoal() {
        return commonGoal;
    }

    /**
     * Returns the point stack.
     *
     * @return the point stack
     */
    public Stack<Integer> getPointStack() {
        return pointStack;
    }
}
