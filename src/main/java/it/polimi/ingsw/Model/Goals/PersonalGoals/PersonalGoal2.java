package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;

public class PersonalGoal2 extends PersonalGoal{

    public PersonalGoal2(Matrix<Item> personalGoal) {
        super(personalGoal);
    }

    @Override
    public Matrix<Item> PersonalGoal() {
        Matrix<Item> personalGoal = new Matrix<>(6, 6);

        personalGoal.set(1,0,new Item(TypeEnum.FRAMES));
        personalGoal.set(1,3,new Item(TypeEnum.GAMES));
        personalGoal.set(2,2,new Item(TypeEnum.PLANTS));
        personalGoal.set(3,1,new Item(TypeEnum.CATS));
        personalGoal.set(3,4,new Item(TypeEnum.TROPHIES));
        personalGoal.set(5,0,new Item(TypeEnum.BOOKS));

        return personalGoal;
    }

}
