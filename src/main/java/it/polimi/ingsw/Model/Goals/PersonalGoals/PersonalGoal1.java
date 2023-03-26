package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.ColorEnum;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;

public class PersonalGoal1 extends PersonalGoal {

    @Override
    public Matrix<Item> createpersonalGoal() {
        Matrix<Item> personalGoal = new Matrix<>(6, 6);
        //dobbiamo accordarci sulla creazione di item, confusionale e ripetitiva
        //o solo tipo o solo colore per enum e picture stringa univoca
        personalGoal.set(1,1,new Item(ColorEnum.PINK, "one"));
        personalGoal.set(2,0,new Item(ColorEnum.GREEN, "one"));
        personalGoal.set(2,2,new Item(ColorEnum.YELLOW, "one"));
        personalGoal.set(3,4,new Item(ColorEnum.WHITE, "one"));
        personalGoal.set(4,3,new Item(ColorEnum.LIGHTBLUE, "one"));
        personalGoal.set(5,4,new Item(ColorEnum.BLUE, "one"));

        return personalGoal;
    }

}
