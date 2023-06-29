package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.PlayerState.*;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import org.junit.jupiter.api.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unit for the Player class.
 */
class PlayerTest {

    /**
     * Tests the method insertInOrderInBookshelf.
     *
     * @throws PlayerIsWaitingException    if the player is in a waiting state.
     * @throws ColumnNotValidException     if the column is not valid.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the picked items don't fit the column.
     */
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


    /**
     * Tests the method numberOfAdjacentSameTypeAndSpreadFalse.
     *
     * @throws NoSuchMethodException     if the specified method does not exist.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException    if the specified method is inaccessible.
     */
    @Test
    public void numberOfAdjacentSameTypeAndSpreadFalseTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create an instance of the class
        Bookshelf bookshelf = new Bookshelf();
        Matrix<Boolean> matrix = new Matrix<>(6, 5);
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
        matrix = new Matrix<>(6, 5);
        matrix.setAll(true);
        result = privateMethod.invoke(instance, 0, 0, bookshelf, matrix, TypeEnum.CATS);
        assertEquals(30, result);

        // Testing bookshelf with some items from different coordinates
        matrix = new Matrix<>(6, 5);
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

    /**
     * Tests the method getRewardGoals.
     *
     * @throws MaxNumberOfPlayersException   if the maximum number of players is reached.
     * @throws IOException                   if there is an I/O error.
     * @throws WrongConfigurationException  if the configuration is incorrect.
     */
    @Test
    public void getRewardGoalsTest() throws MaxNumberOfPlayersException, IOException, WrongConfigurationException {

        // just created player
        Player player = new Player("test", new Game(0, 2));
        player.computeRewardGoals();
        assertEquals(player.getTotalPoints(), 0);

        // player completes general goal tasks
        player.getBookshelf().set(5, 0, new Item(TypeEnum.CATS));
        player.getBookshelf().set(4, 0, new Item(TypeEnum.CATS));
        player.getBookshelf().set(3, 0, new Item(TypeEnum.CATS));
        player.computeRewardGoals();
        assert (player.getTotalPoints() >= 2);

        player.getBookshelf().set(2, 0, new Item(TypeEnum.CATS));
        player.computeRewardGoals();
        assert (player.getTotalPoints() >= 3);

        player.getBookshelf().set(1, 0, new Item(TypeEnum.CATS));
        player.computeRewardGoals();
        assert (player.getTotalPoints() >= 5);

        player.getBookshelf().set(0, 0, new Item(TypeEnum.CATS));
        player.computeRewardGoals();
        assert (player.getTotalPoints() >= 8);

        player.getBookshelf().set(5, 1, new Item(TypeEnum.CATS));
        player.computeRewardGoals();
        assert (player.getTotalPoints() >= 8);

        int k = 0;
    }

    /**
     * Tests the method setBookshelf.
     *
     * @throws MaxNumberOfPlayersException if the maximum number of players is reached.
     * @throws IOException if there is an I/O error.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the pick doesn't fit the column.
     */
    @Test
    void setBookshelfTest() throws MaxNumberOfPlayersException, IOException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        Player player = new Player("test", new Game(0, 2));
        player.setBookshelf();
        Bookshelf bookshelf = player.getBookshelf();
        assertEquals(bookshelf, player.getBookshelf());
    }

    /**
     * Tests the method endTurn.
     *
     * @throws WrongConfigurationException if the configuration is incorrect.
     * @throws MaxNumberOfPlayersException if the maximum number of players is reached.
     * @throws IOException if there is an I/O error.
     */
    @Test
    void endTurnTest() throws WrongConfigurationException, MaxNumberOfPlayersException, IOException {
        Player player = new Player("test", new Game(0, 2));
        player.endTurn();
        assert (player.getState() instanceof PlayerStateWaiting);
    }

    /**
     * Tests the method pickAndInsertInBookshelf.
     *
     * @throws MaxNumberOfPlayersException                  if the maximum number of players is reached.
     * @throws IOException                                  if there is an I/O error.
     * @throws AlreadyStartedGameException                  if the game has already started.
     * @throws SelectionNotValidException                   if the selection is not valid.
     * @throws PlayerIsWaitingException                     if the player is waiting.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tiles selection is different from the length of the order.
     * @throws ColumnNotValidException                      if the column is not valid.
     * @throws SelectionIsEmptyException                    if the selection is empty.
     * @throws WrongConfigurationException                 if the configuration is incorrect.
     * @throws PickedColumnOutOfBoundsException            if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException                 if the pick doesn't fit the column.
     * @throws VoidBoardTileException                       if the board tile is void.
     */
    @Test
    void pickAndInsertInBookshelfTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, VoidBoardTileException {
        Game game = new Game(0, 2);
        game.addPlayer("test");
        game.addPlayer("test2");
        Player player = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        game.startGame();
        game.refreshBoard();
        player.changeState(new PlayerStateSelecting());
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        coordinates.add(new Coordinates(3, 1));
        int[] order = {0};
        player.pickAndInsertInBookshelf(coordinates, 1, order);
        assert (player.getBookshelf().get(5, 1) != null);


    }

    /**
     * Calculates the points of the private goal.
     *
     * @param i the index of the private goal.
     * @return the points corresponding to the private goal.
     * @throws WrongConfigurationException if the configuration is incorrect.
     */
    private int getCurrentPoints(int i) throws WrongConfigurationException {
        int result;
        switch (i) {
            case 0 -> result = 0;
            case 1 -> result = 1;
            case 2 -> result = 2;
            case 3 -> result = 4;
            case 4 -> result = 6;
            case 5 -> result = 9;
            case 6 -> result = 12;
            default -> throw new WrongConfigurationException();
        }
        return result;
    }

}
