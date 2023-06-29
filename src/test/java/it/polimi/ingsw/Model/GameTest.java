package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.GameState.GameState;
import it.polimi.ingsw.Model.GameState.GameStateStarting;
import it.polimi.ingsw.Utils.GameStatusToFile;
import it.polimi.ingsw.Utils.PlayerStatusToFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unit for the Game class.
 */
class GameTest {

    /**
     * Tests the constructor of the Game class when it throws a MaxNumberOfPlayersException.
     */
    @Test
    void constructorThrowsNumberOfPlayerExceptionTest() {
        assertThrows(MaxNumberOfPlayersException.class, () -> new Game(3, 5));
    }

    /**
     * Tests the constructor of the Game class when it does not throw any exception.
     */
    @Test
    void constructorThrowsNoExceptionTest() {
        assertDoesNotThrow(() -> new Game(3, 3));
    }

    /**
     * Tests the addFirstPlayer() method of the Game class.
     *
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     * @throws IOException                 if an I/O error occurs.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    @Test
    void addFirstPlayerTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 4);

        String playerID = "54336";
        game.addPlayer(playerID);
        assertEquals(1, game.getPlayers().size());
        assertEquals(playerID, game.getPlayers().get(0).getPlayerID());
    }

    /**
     * Tests the addPlayer() method of the Game class.
     *
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     * @throws IOException                 if an I/O error occurs.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    @Test
    void addPlayerTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {

        assertThrows(MaxNumberOfPlayersException.class, () -> new Game(1, 5));

        Game game = new Game(1, 4);
        game.addPlayer("54336");
        assertEquals(1, game.getPlayers().size());
        game.addPlayer("54337");
        assertEquals(2, game.getPlayers().size());
        game.addPlayer("54338");
        assertEquals(3, game.getPlayers().size());
        game.addPlayer("54339");
        assertEquals(4, game.getPlayers().size());
        assertThrows(AlreadyStartedGameException.class, () -> game.addPlayer("54340"));
    }

    /**
     * Tests the startGame() method of the Game class.
     *
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     * @throws IOException                 if an I/O error occurs.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    @Test
    void nextTurnTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameNotStartedException, FinishedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        game.startGame();
        game.nextTurn();
        assertEquals("54337", game.getCurrentPlayer().getPlayerID());
        game.nextTurn();
        assertEquals("54338", game.getCurrentPlayer().getPlayerID());
        game.nextTurn();
        assertEquals("54336", game.getCurrentPlayer().getPlayerID());
    }

    /**
     * Tests the startGame() method of the Game class.
     *
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     * @throws IOException                 if an I/O error occurs.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    @Test
    void refreshBoardTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        game.startGame();
        game.refreshBoard();
        assert (game.getBoard().getGameBoard().get(3, 3) != null);
    }

    /**
     * Tests the startGame() method of the Game class.
     *
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     * @throws IOException                 if an I/O error occurs.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    @Test
    void returnGameIdTest() throws MaxNumberOfPlayersException, IOException {
        Game game = new Game(1, 3);
        assertEquals(1, game.getId());
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    void testGameStatusToFileTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        game.startGame();
        assert (game.getGameStatusToFile() != null);
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    void gameTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        ArrayList<PlayerStatusToFile> playerstat = new ArrayList<>();
        playerstat.add(game.getPlayers().get(0).getPlayerStatusToFile());
        playerstat.add(game.getPlayers().get(1).getPlayerStatusToFile());
        playerstat.add(game.getPlayers().get(2).getPlayerStatusToFile());
        GameStatusToFile gameStatusToFile = game.getGameStatusToFile();
        Game gam2 = new Game(gameStatusToFile, playerstat);
        assertEquals(game.getId(), gam2.getId());
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    void getBoardTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        game.startGame();
        assert (game.getBoard() != null);
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    void getPlayersTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        game.startGame();
        assert (game.getPlayers() != null);
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    void nextPlayerTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameNotStartedException, FinishedGameException {
        Game game = new Game(1, 3);
        assertThrows(GameNotStartedException.class, () -> game.nextPlayer());
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        game.startGame();
        assert (game.getCurrentPlayer().getPlayerID() == "54336");
        game.nextPlayer();
        assert (game.getCurrentPlayer().getPlayerID() == "54337");
    }

    /**
     * Test of getGameState method, of class Game.
     */
    @Test
    void getGameStateTest() throws MaxNumberOfPlayersException, IOException {
        Game game = new Game(1, 3);
        assert (game.getGameState() instanceof GameStateStarting);
    }

    /**
     * Test of getCommonGoal method, of class Game.
     */
    @Test
    void getCommonGoalPointStackTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        assert (game.getCommonGoalPointStacks() != null);
    }

    /**
     * Test of getCommonGoal method, of class Game.
     */
    @Test
    void getBookshelvesTest() throws AlreadyStartedGameException, MaxNumberOfPlayersException, IOException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        assert (game.getBookshelves() != null);
    }

    /**
     * Test of getPersonalGoal method, of class Game.
     */
    @Test
    void getPersonalGoalTest() throws AlreadyStartedGameException, MaxNumberOfPlayersException, IOException, MissingPlayerException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        assert (game.getPersonalGoal("54336") != null);
    }

    /**
     * Test of getPoints method, of class Game.
     */
    @Test
    void getIsCommonGoalAlreadyAchievedTest() throws AlreadyStartedGameException, MaxNumberOfPlayersException, IOException, MissingPlayerException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        boolean[] isCommonGoalAlreadyAchieved = game.getIsCommonGoalAchieved("54336");

        assert (isCommonGoalAlreadyAchieved[0] == false);
    }

    /**
     * Test of getPoints method, of class Game.
     */
    @Test
    void getPlayersIdTest() throws AlreadyStartedGameException, MaxNumberOfPlayersException, IOException, MissingPlayerException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        ArrayList<String> playersId = game.getPlayersId();
        assert (playersId.size() == 3);
        assert (playersId.get(0).equals("54336"));
        assert (playersId.get(1).equals("54337"));
        assert (playersId.get(2).equals("54338"));
    }

    /** Test for the method getPoints() */
    @Test
    void getPointsTest() throws AlreadyStartedGameException, MaxNumberOfPlayersException, IOException, MissingPlayerException {
        Game game = new Game(1, 3);
        game.addPlayer("54336");
        game.addPlayer("54337");
        game.addPlayer("54338");
        ArrayList<Integer> points = game.getPoints();
        assert (points.size() == 3);
        assert (points.get(0) == 0);
        assert (points.get(1) == 0);
        assert (points.get(2) == 0);
    }
}
