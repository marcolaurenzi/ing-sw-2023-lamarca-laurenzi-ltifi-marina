package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the BoardTile class.
 */
public class BoardTileTest {

    /**
     * Test the isEmpty method.
     */
    @Test
    public void testIsEmpty() {
        BoardTile boardTile = new BoardTile(1);
        assertTrue(boardTile.isEmpty());
    }

    /**
     * Test for the placeItem method.
     */
    @Test
    public void testPlaceItem() {
        BoardTile boardTile = new BoardTile(1);
        Item item = new Item(TypeEnum.BOOKS);
        boardTile.placeItem(item);
        assert (boardTile.isEmpty() == false && boardTile.getPlacedItem() == item);
    }

    /**
     * Test for the getPlacedItem method.
     */
    @Test
    public void testGetPlacedItem() {
        BoardTile boardTile = new BoardTile(1);
        Item item = new Item(TypeEnum.BOOKS);
        boardTile.placeItem(item);
        assert (boardTile.getPlacedItem() == item);
    }

    /**
     * Test for the getNumberOfPlayersSign method.
     */
    @Test
    public void testGetNumberOfPlayersSign() {
        BoardTile boardTile = new BoardTile(1);
        assert (boardTile.getNumberOfPlayersSign() == 1);
    }

    /**
     * Test for the checkItem method.
     */
    @Test
    public void testCheckItem(){
        BoardTile boardTile = new BoardTile(1);
        Item item = new Item(TypeEnum.BOOKS);
        boardTile.placeItem(item);
        assert (boardTile.checkItem().equals(item));
    }

    /**
     * Test for the drawItem method.
     */
    @Test
    public void testDrawItem() {
        BoardTile boardTile = new BoardTile(1);
        Item item = new Item(TypeEnum.BOOKS);
        boardTile.placeItem(item);
        assert (boardTile.drawItem().equals(item));
        assert (boardTile.isEmpty());
    }

    /**
     * Test for the brutePlaceItem method.
     */
    @Test
    public void testbrutePlaceItem() {
        BoardTile boardTile = new BoardTile(1);
        Item item = new Item(TypeEnum.BOOKS);
        boardTile.brutePlaceItem(item);
        assert (boardTile.isEmpty() == false && boardTile.getPlacedItem() == item);
    }



}
