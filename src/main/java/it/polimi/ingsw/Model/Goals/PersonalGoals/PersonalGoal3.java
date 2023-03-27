package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;

/**
 *
 */
public class PersonalGoal3 extends PersonalGoal{

    public PersonalGoal3(Matrix<Item> personalGoal) {
        super(personalGoal);
    }

    @Override
    public Matrix<Item> PersonalGoal() {
        Matrix<Item> personalGoal = new Matrix<>(6, 6);

        personalGoal.set(0,4,new Item(TypeEnum.GAMES));
        personalGoal.set(2,0,new Item(TypeEnum.TROPHIES));
        personalGoal.set(2,2,new Item(TypeEnum.FRAMES));
        personalGoal.set(3,3,new Item(TypeEnum.PLANTS));
        personalGoal.set(4,1,new Item(TypeEnum.BOOKS));
        personalGoal.set(4,2,new Item(TypeEnum.CATS));

        return personalGoal;
    }
}
