package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void constructorThrowsNumberOfPlayerException() {
        assertThrows(MaxNumberOfPlayersException.class, () -> new Game("3", 5));
    }

    @Test
    void constructorThrowsNoException() {
        assertDoesNotThrow(() -> new Game("3", 3));
    }

    @Test
    void addFirstPlayer() throws MaxNumberOfPlayersException, IOException, NumberOfPlayersException {
        Game game = new Game("1", 4);

        String playerID = "54336";
        game.addPlayer(playerID);
        assertEquals(1, game.getPlayers().size());
        assertEquals(playerID, game.getPlayers().get(0).getPlayerID());
    }
    @Test
    void testAddPlayer() {
    }


    @Test
    void initializeBoard() {
    }

    @Test
    void nextPlayer() {
    }

    @Test
    void getCurrentPlayer() {
    }

    @Test
    void getMaxPlayers() {
    }

    @Test
    void getGameID() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void initializeDeck() {
    }

    @Test
    void startGame() {
    }

    @Test
    void getPersonalGoalDeck() {
    }

    @Test
    void getCommonGoalPointStacks() {
    }
}