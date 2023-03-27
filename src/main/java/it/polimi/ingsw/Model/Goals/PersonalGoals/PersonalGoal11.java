package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;

public class PersonalGoal11 extends PersonalGoal{

    public PersonalGoal11(Matrix<Item> personalGoal) {
        super(personalGoal);
    }

    @Override
    public Matrix<Item> PersonalGoal() {
        Matrix<Item> personalGoal = new Matrix<>(6, 6);

        personalGoal.set(0,2,new Item(TypeEnum.BOOKS));
        personalGoal.set(1,1,new Item(TypeEnum.PLANTS));
        personalGoal.set(2,2,new Item(TypeEnum.FRAMES));
        personalGoal.set(3,3,new Item(TypeEnum.TROPHIES));
        personalGoal.set(4,4,new Item(TypeEnum.GAMES));
        personalGoal.set(5,0,new Item(TypeEnum.CATS));

        return personalGoal;
    }
}
