package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.PlayerState.*;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import org.junit.jupiter.api.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

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


    @Test
    public void numberOfAdjacentSameTypeAndSpreadFalseTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create an instance of the class
        Bookshelf bookshelf = new Bookshelf();
        Matrix<Boolean> matrix = new Matrix<>(6,5);
        matrix.setAll(true);

        Method privateMethod = Player.class.getDeclaredMethod("numberOfAdjacentSameTypeAndSpreadFalse", int.class, int.class, Bookshelf.class, Matrix.class, TypeEnum.class);
        privateMethod.setAccessible(true);
        Objenesis objenesis = new ObjenesisStd();
        Object instance = objenesis.newInstance(Player.class);

        // Testing void bookshelf
        Object result = privateMethod.invoke(instance, 0, 0, bookshelf, matrix, TypeEnum.CATS);
        assertEquals(1, result);

        // Testing full bookshelf
        bookshelf = new Bookshelf();
        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                bookshelf.set(i, j, new Item(TypeEnum.CATS));
            }
        }
        matrix = new Matrix<>(6,5);
        matrix.setAll(true);
        result = privateMethod.invoke(instance, 0, 0, bookshelf, matrix, TypeEnum.CATS);
        assertEquals(30, result);

        // Testing bookshelf with some items from different coordinates
        matrix = new Matrix<>(6,5);
        matrix.setAll(true);
        bookshelf = new Bookshelf();
        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                bookshelf.set(i, j, new Item(TypeEnum.CATS));
            }
        }
        result = privateMethod.invoke(instance, 0, 0, bookshelf, matrix, TypeEnum.CATS);
        assertEquals(30, result);

    }

}