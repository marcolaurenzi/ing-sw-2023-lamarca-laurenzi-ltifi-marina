package it.polimi.ingsw.Model.Goals.CommonGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.TypeEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommonGoal3Test {
    @Test
    public void voidMatrixCheck() {

        Bookshelf bookshelf = new Bookshelf();
        CommonGoal3 commonGoal3 = new CommonGoal3();

        bookshelf.set(0,0, new Item(TypeEnum.CATS));
        bookshelf.set(0,1, new Item(TypeEnum.CATS));
        bookshelf.set(0,2, new Item(TypeEnum.CATS));
        bookshelf.set(0,3, new Item(TypeEnum.CATS));
        bookshelf.set(0,4, new Item(TypeEnum.CATS));
        bookshelf.set(1,0, new Item(TypeEnum.CATS));
        bookshelf.set(1,1, new Item(TypeEnum.CATS));
        bookshelf.set(1,2, new Item(TypeEnum.CATS));
        bookshelf.set(1,3, new Item(TypeEnum.CATS));
        bookshelf.set(1,4, new Item(TypeEnum.CATS));
        bookshelf.set(2,0, new Item(TypeEnum.CATS));
        bookshelf.set(2,1, new Item(TypeEnum.CATS));
        bookshelf.set(2,2, new Item(TypeEnum.CATS));
        bookshelf.set(2,3, new Item(TypeEnum.CATS));
        bookshelf.set(2,4, new Item(TypeEnum.CATS));
        bookshelf.set(3,0, new Item(TypeEnum.CATS));
        bookshelf.set(3,1, new Item(TypeEnum.CATS));
        bookshelf.set(3,2, new Item(TypeEnum.CATS));
        bookshelf.set(3,3, new Item(TypeEnum.CATS));
        bookshelf.set(3,4, new Item(TypeEnum.CATS));
        bookshelf.set(5,0, new Item(TypeEnum.CATS));
        bookshelf.set(5,1, new Item(TypeEnum.CATS));
        bookshelf.set(5,2, new Item(TypeEnum.CATS));
        bookshelf.set(5,3, new Item(TypeEnum.CATS));
        bookshelf.set(5,4, new Item(TypeEnum.CATS));

        assertFalse(commonGoal3.isAchieved(bookshelf));
        assertEquals(0, commonGoal3.numberOfTimes(bookshelf));
    }
}
