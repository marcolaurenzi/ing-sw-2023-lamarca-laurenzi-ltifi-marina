package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.PlayerState.*;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import org.junit.jupiter.api.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void insertInOrderInBookshelf() throws PlayerIsWaitingException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        PlayerState playerState = new PlayerStateSelecting();
        Bookshelf bookshelf = new Bookshelf();

        ArrayList<Item> pickedItems = new ArrayList<>();
        pickedItems.add(new Item(TypeEnum.CATS));
        pickedItems.add(new Item(TypeEnum.TROPHIES));
        pickedItems.add(new Item(TypeEnum.PLANTS));
        int[] order = {1, 2, 0};

        playerState.insertPickInBookShelf(pickedItems, bookshelf, 0, order);

        assertEquals(TypeEnum.TROPHIES, bookshelf.get(5, 0).getType());
        assertEquals(TypeEnum.PLANTS, bookshelf.get(4, 0).getType());
        assertEquals(TypeEnum.CATS, bookshelf.get(3, 0).getType());
    }

    /*
    @Test
    public void testPrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, MaxNumberOfPlayersException, IOException {
        // Create an instance of the class
        Player player = new Player("test", new Game(1, 4));

        // Use reflection to access the private method
        Method privateMethod = Player.class.getDeclaredMethod("getRewardGeneralGoal");
        privateMethod.setAccessible(true);

        // Invoke the private method
        Objenesis objenesis = new ObjenesisStd();
        Object instance = objenesis.newInstance(Player.class);
        Object result = privateMethod.invoke(instance);

        // Verify the result
        assertEquals("Expected result", result);
    }
     */

}