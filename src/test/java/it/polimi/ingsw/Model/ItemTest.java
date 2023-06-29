package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import it.polimi.ingsw.Utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Unit test for the Item class.
 */
public class ItemTest {
    /**
     * The item used for testing.
     */
    private Item item;
    /**
     * The type of the item used for testing.
     */
    private TypeEnum type;

    /**
     * Setup test.
     */
    @BeforeEach
    public void setup() {
        type = TypeEnum.CATS;
        item = new Item(type);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor() {
        assertEquals(type, item.getType(), "Item type should match the type set in constructor");
        assertTrue(item.getNum() >= 1 && item.getNum() <= 3, "Num should be between 1 and 3 (inclusive)");
    }

    /**
     * Test for the getType method.
     */
    @Test
    public void testGetType() {
        assertEquals(type, item.getType(), "Returned type should match the set type");
    }

    /**
     * Test for the getNum method.
     */
    @Test
    public void testGetNum() {
        assertTrue(item.getNum() >= 1 && item.getNum() <= 3, "Returned num should be between 1 and 3 (inclusive)");
    }

    /**
     * Test for the toString method.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void testToString() throws IOException {
        String expected = "#".repeat(Math.max(0, Utils.getItemDimension()));
        expected = Utils.setColor(expected, type.toString());
        assertEquals(expected, item.toString(), "Expected and actual string representations should match");
    }

    /**
     * Test for the voidToString method.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void testVoidToString() throws IOException {
        String expected = " ".repeat(Math.max(0, Utils.getItemDimension()));
        assertEquals(expected, Item.voidToString(), "Expected and actual string representations should match");
    }
}
