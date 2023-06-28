package it.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    Item item = new Item(TypeEnum.BOOKS);

    @Test
    public void testGetType() {
        assertEquals(TypeEnum.BOOKS, item.getType());
    }

    @Test
    public void testGetNum() {
        assertEquals(1, item.getNum());
    }

    @Test
    public void testToString() {
        assertEquals("\u001B[37m#####\u001B[0m", item.toString());
    }

    @Test
    public void testToString2() {
        Item item2 = new Item(TypeEnum.BOOKS);
        assertEquals("\u001B[37m#####\u001B[0m", item2.toString());
    }
}
