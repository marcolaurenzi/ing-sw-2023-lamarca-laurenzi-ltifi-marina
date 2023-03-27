package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;

public class PersonalGoal8 extends PersonalGoal{

    public PersonalGoal8(Matrix<Item> personalGoal) {
        super(personalGoal);
    }

    @Override
    public Matrix<Item> PersonalGoal() {
        Matrix<Item> personalGoal = new Matrix<>(6, 6);

        personalGoal.set(0,2,new Item(TypeEnum.GAMES));
        personalGoal.set(2,2,new Item(TypeEnum.CATS));
        personalGoal.set(3,4,new Item(TypeEnum.BOOKS));
        personalGoal.set(4,1,new Item(TypeEnum.TROPHIES));
        personalGoal.set(4,4,new Item(TypeEnum.PLANTS));
        personalGoal.set(5,0,new Item(TypeEnum.FRAMES));

        return personalGoal;
    }
}
