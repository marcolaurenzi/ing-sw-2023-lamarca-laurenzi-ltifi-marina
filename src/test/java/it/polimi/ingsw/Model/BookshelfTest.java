package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.PickDoesntFitColumnException;
import it.polimi.ingsw.Model.Exceptions.PickedColumnOutOfBoundsException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class BookshelfTest {
    @Test
    void insertOutOfBounds() {
        Bookshelf bookshelf = new Bookshelf();
        ArrayList<Item> toInsert = new ArrayList<>();

        toInsert.add(new Item(TypeEnum.CATS));
        toInsert.add(new Item(TypeEnum.BOOKS));
        toInsert.add(new Item(TypeEnum.TROPHIES));

        assertThrows(PickedColumnOutOfBoundsException.class, () -> bookshelf.insert(-1, toInsert));
    }
    @Test
    void insertWhenEmptyThreeElements() throws PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        Bookshelf bookshelf = new Bookshelf();
        ArrayList<Item> toInsert = new ArrayList<>();

        toInsert.add(new Item(TypeEnum.CATS));
        toInsert.add(new Item(TypeEnum.BOOKS));
        toInsert.add(new Item(TypeEnum.TROPHIES));

        bookshelf.insert(0, toInsert);

        assertEquals(TypeEnum.CATS, bookshelf.get(5, 0).getType());
        assertEquals(TypeEnum.BOOKS, bookshelf.get(4, 0).getType());
        assertEquals(TypeEnum.TROPHIES, bookshelf.get(3, 0).getType());

        for(int i = 0; i < bookshelf.getColumnDimension(); i++)
            for(int j = 0; j < bookshelf.getRowDimension(); j++)
                if(i != 5 && i != 4 && i != 3)
                    assertNull(bookshelf.get(i, j));

    }
    @Test
    void insertWhenNotEmpty() throws PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        Bookshelf bookshelf = new Bookshelf();
        ArrayList<Item> toInsert = new ArrayList<>();

        bookshelf.set(5, 0, new Item(TypeEnum.CATS));

        toInsert.add(new Item(TypeEnum.CATS));
        toInsert.add(new Item(TypeEnum.BOOKS));
        toInsert.add(new Item(TypeEnum.TROPHIES));

        bookshelf.insert(0, toInsert);

        assertEquals(TypeEnum.CATS, bookshelf.get(5, 0).getType());
        assertEquals(TypeEnum.CATS, bookshelf.get(4, 0).getType());
        assertEquals(TypeEnum.BOOKS, bookshelf.get(3, 0).getType());
        assertEquals(TypeEnum.TROPHIES, bookshelf.get(2, 0).getType());

        for(int i = 0; i < bookshelf.getColumnDimension(); i++)
            for(int j = 0; j < bookshelf.getRowDimension(); j++)
                if(i != 5 && i != 4 && i != 3 && i != 2)
                    assertNull(bookshelf.get(i, j));

    }

    @Test
    void insertWhenEmptyTwoElements() throws PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        Bookshelf bookshelf = new Bookshelf();
        ArrayList<Item> toInsert = new ArrayList<>();

        toInsert.add(new Item(TypeEnum.CATS));
        toInsert.add(new Item(TypeEnum.BOOKS));

        bookshelf.insert(0, toInsert);

        assertEquals(TypeEnum.CATS, bookshelf.get(5, 0).getType());
        assertEquals(TypeEnum.BOOKS, bookshelf.get(4, 0).getType());

        for(int i = 0; i < bookshelf.getColumnDimension(); i++)
            for(int j = 0; j < bookshelf.getRowDimension(); j++)
                if(i != 5 && i != 4)
                    assertNull(bookshelf.get(i, j));

    }
    @Test
    void insertWhenColumnIsFull() {
        Bookshelf bookshelf = new Bookshelf();
        ArrayList<Item> toInsert = new ArrayList<>();

        bookshelf.set(5, 0, new Item(TypeEnum.CATS));
        bookshelf.set(4, 0, new Item(TypeEnum.CATS));
        bookshelf.set(3, 0, new Item(TypeEnum.CATS));
        bookshelf.set(2, 0, new Item(TypeEnum.CATS));
        bookshelf.set(1, 0, new Item(TypeEnum.CATS));
        bookshelf.set(0, 0, new Item(TypeEnum.CATS));

        toInsert.add(new Item(TypeEnum.CATS));
        toInsert.add(new Item(TypeEnum.BOOKS));

        assertThrows(PickDoesntFitColumnException.class, () -> bookshelf.insert(0, toInsert));
    }
}
