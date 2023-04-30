package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void constructorThrowsNumberOfPlayerExceptionTest() {
        assertThrows(MaxNumberOfPlayersException.class, () -> new Game(3, 5));
    }

    @Test
    void constructorThrowsNoExceptionTest() {
        assertDoesNotThrow(() -> new Game(3, 3));
    }

    @Test
    void addFirstPlayerTest() throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException {
        Game game = new Game(1, 4);

        String playerID = "54336";
        game.addPlayer(playerID);
        assertEquals(1, game.getPlayers().size());
        assertEquals(playerID, game.getPlayers().get(0).getPlayerID());
    }

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
}