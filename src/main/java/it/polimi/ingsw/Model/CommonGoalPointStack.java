package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;
import it.polimi.ingsw.Model.Exceptions.*;
import java.util.Stack;


public class CommonGoalPointStack{
    private final Stack<Integer> pointStack;
    private Integer top;

    public CommonGoal getCommonGoal() {
        return commonGoal;
    }

    private final CommonGoal commonGoal;

    public CommonGoalPointStack(CommonGoal commonGoal, int numberOfPlayers) {
        this.commonGoal = commonGoal;
        pointStack = new Stack<>();

        switch (numberOfPlayers) {
            case 2: {
                pointStack.push(4);
                pointStack.push(8);
            }
            case 3: {
                pointStack.push(4);
                pointStack.push(6);
                pointStack.push(8);
            }
            case 4: {
                pointStack.push(2);
                pointStack.push(4);
                pointStack.push(6);
                pointStack.push(8);
            }
        }
        top = 8;
    }

    public int draw() {
        if(pointStack.size() > 1) {
            top = pointStack.get(1);
        }
        else {
            top = null;
        }
        return pointStack.pop();
    }
}
