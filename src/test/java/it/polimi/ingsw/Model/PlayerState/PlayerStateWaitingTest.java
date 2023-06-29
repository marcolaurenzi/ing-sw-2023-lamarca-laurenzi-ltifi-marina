package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.PlayerIsWaitingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStateWaitingTest {
    private PlayerStateWaiting playerStateWaiting;
    private Board board;
    private Bookshelf bookshelf;

    /**
     * Initializes the attributes for the test class.
     */
    @BeforeEach
    void setUp() {
        playerStateWaiting = new PlayerStateWaiting();
        board = new Board();
        bookshelf = new Bookshelf();
    }

    /**
     * Tests the method pickAndInsertInBookshelf.
     *
     * @throws PlayerIsWaitingException if the player is waiting.
     */
    @Test
    void pickAndInsertInBookshelf_throwsException() {
        // Arrange
        ArrayList<Coordinates> tilesSelection = new ArrayList<>();
        tilesSelection.add(new Coordinates(0, 0));
        tilesSelection.add(new Coordinates(0, 1));
        tilesSelection.add(new Coordinates(0, 2));
        int[] order = {1, 2, 0};

        // Act & Assert
        assertThrows(PlayerIsWaitingException.class, () -> playerStateWaiting.pickAndInsertInBookshelf(tilesSelection, board, bookshelf, 0, order));
    }

    /**
     * Tests the method insertPickInBookShelf.
     *
     * @throws PlayerIsWaitingException if the player is waiting.
     */
    @Test
    void insertPickInBookShelf_throwsException() {
        // Arrange
        ArrayList<Item> pickedItems = new ArrayList<>();
        Item item1 = new Item(TypeEnum.CATS);
        Item item2 = new Item(TypeEnum.TROPHIES);
        Item item3 = new Item(TypeEnum.PLANTS);
        pickedItems.add(item1);
        pickedItems.add(item2);
        pickedItems.add(item3);
        int[] order = {1, 2, 0};

        // Act & Assert
        assertThrows(PlayerIsWaitingException.class, () -> playerStateWaiting.insertPickInBookShelf(pickedItems, bookshelf, 0, order));
    }

    /**
     * Tests the method pickAndInsertInWarehouse.
     *
     * @throws PlayerIsWaitingException if the player is waiting.
     */
    @Test
    void getStateNumber_returnsOne() {
        // Act & Assert
        assertEquals(1, playerStateWaiting.getStateNumber());
    }
}