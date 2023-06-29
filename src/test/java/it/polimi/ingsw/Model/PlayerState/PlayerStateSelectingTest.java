package it.polimi.ingsw.Model.PlayerState;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStateSelectingTest {
    private PlayerStateSelecting playerStateSelecting;
    private Board board;
    private Bookshelf bookshelf;

    /**
     * Initializes the attributes for the test class.
     */
    @BeforeEach
    void setUp() {
        playerStateSelecting = new PlayerStateSelecting();
        board = new Board();
        bookshelf = new Bookshelf();
    }

    /**
     * Tests the method pickAndInsertInBookshelf.
     *
     * @throws ColumnNotValidException     if the column is not valid.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the picked items don't fit the column.
     */
    @Test
    void pickAndInsertInBookshelf_invalidSelection_throwsException() {
        // Arrange
        ArrayList<Coordinates> tilesSelection = new ArrayList<>();
        tilesSelection.add(new Coordinates(0, 0));
        tilesSelection.add(new Coordinates(1, 1));
        int[] order = {0, 1};

        // Act & Assert
        assertThrows(NullPointerException.class, () -> playerStateSelecting.pickAndInsertInBookshelf(tilesSelection, board, bookshelf, 0, order));
    }

    /**
     * Tests the method pickAndInsertInBookshelf.
     *
     * @throws ColumnNotValidException     if the column is not valid.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the picked items don't fit the column.
     */
    @Test
    void pickAndInsertInBookshelf_emptySelection_throwsException() {
        // Arrange
        ArrayList<Coordinates> tilesSelection = new ArrayList<>();
        tilesSelection.add(null);
        int[] order = {};

        // Act & Assert
        assertThrows(SelectionIsEmptyException.class, () -> playerStateSelecting.pickAndInsertInBookshelf(tilesSelection, board, bookshelf, 0, order));
    }

    /**
     * Tests the method pickAndInsertInBookshelf.
     *
     * @throws ColumnNotValidException     if the column is not valid.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the picked items don't fit the column.
     */
    @Test
    void pickAndInsertInBookshelf_wrongOrder_throwsException() {
        // Arrange
        ArrayList<Coordinates> tilesSelection = new ArrayList<>();
        tilesSelection.add(new Coordinates(0, 0));
        tilesSelection.add(new Coordinates(0, 1));
        tilesSelection.add(new Coordinates(0, 2));
        int[] order = {1, 2};

        // Act & Assert
        assertThrows(NullPointerException.class, () -> playerStateSelecting.pickAndInsertInBookshelf(tilesSelection, board, bookshelf, 0, order));
    }

    /**
     * Tests the method insertPickInBookshelf.
     *
     * @throws ColumnNotValidException     if the column is not valid.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the picked items don't fit the column.
     */
    @Test
    void insertPickInBookShelf_validInsertion_insertsItemsInBookshelf() throws ColumnNotValidException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        // Arrange
        ArrayList<Item> pickedItems = new ArrayList<>();
        Item item1 = new Item(TypeEnum.CATS);
        Item item2 = new Item(TypeEnum.TROPHIES);
        Item item3 = new Item(TypeEnum.PLANTS);
        pickedItems.add(item1);
        pickedItems.add(item2);
        pickedItems.add(item3);
        int[] order = {1, 2, 0};

        // Act
        playerStateSelecting.insertPickInBookShelf(pickedItems, bookshelf, 0, order);

        // Assert
        assertEquals(6, bookshelf.getColumnDimension());
        assert(bookshelf.get(5, 0) != null);
        assert(bookshelf.get(4, 0) !=  null);
        assert(bookshelf.get(3, 0) != null);
    }

    /**
     * This test checks if the method insertPickInBookShelf() throws an exception if the column is not valid
     */
    @Test
    void insertPickInBookShelf_invalidInsertion_throwsException() {
        // Arrange
        ArrayList<Item> pickedItems = new ArrayList<>();
        Item item1 = new Item(TypeEnum.CATS);
        Item item2 = new Item(TypeEnum.TROPHIES);
        Item item3 = new Item(TypeEnum.PLANTS);
        pickedItems.add(item1);
        pickedItems.add(item2);
        pickedItems.add(item3);

        // Act & Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> playerStateSelecting.insertPickInBookShelf(pickedItems, bookshelf, 77, new int[]{}));
    }

    /**
     *
     */
    @Test
    void areAllSameRowTest() {
        // Arrange
        ArrayList<Coordinates> tilesSelection = new ArrayList<>();
        tilesSelection.add(new Coordinates(0, 0));
        tilesSelection.add(new Coordinates(0, 1));
        tilesSelection.add(new Coordinates(0, 2));
        int[] order = {1, 2, 0};

        // Act
        boolean result = playerStateSelecting.areAllSameRow(tilesSelection);

        // Assert
        assertTrue(result);
    }

    /**
     * This test checks if the method areAllAdjacentNotOrdered() returns true if the tiles selected are not ordered
     */
    @Test
    void areAllAdjacentNotOrderedTest() {
        // Arrange
        ArrayList<Coordinates> tilesSelection = new ArrayList<>();
        tilesSelection.add(new Coordinates(0, 0));
        tilesSelection.add(new Coordinates(0, 2));
        tilesSelection.add(new Coordinates(0, 1));
        int[] order = {1, 2, 0};

        assertTrue (playerStateSelecting.areAllAjdacentsNotOrdered(tilesSelection));

    }

    /**
     * This test checks if the method insertPickInBookShelf() throws an exception if the column is not valid
     */
    @Test
    void insertPickInBookShelf_wrongOrder_insertsItemsInBookshelf() throws ColumnNotValidException, PickDoesntFitColumnException, PickedColumnOutOfBoundsException {
        // Arrange
        ArrayList<Item> pickedItems = new ArrayList<>();
        Item item1 = new Item(TypeEnum.CATS);
        Item item2 = new Item(TypeEnum.TROPHIES);
        Item item3 = new Item(TypeEnum.PLANTS);
        pickedItems.add(item1);
        pickedItems.add(item2);
        pickedItems.add(item3);
        int[] order = {1, 2};

        // Act
        playerStateSelecting.insertPickInBookShelf(pickedItems, bookshelf, 0, order);

        // Assert
        assertEquals(6, bookshelf.getColumnDimension());
        assert (bookshelf.get(5, 0) != null);
        assert (bookshelf.get(4, 0) != null);
        assert (bookshelf.get(3, 0) == null);
    }

    /**
     * This test checks if the method getStateNumber() returns the correct number of the state
     */
    @Test
    void getStateNumber_returnsZero() {
        // Act & Assert
        assertEquals(0, playerStateSelecting.getStateNumber());
    }
}