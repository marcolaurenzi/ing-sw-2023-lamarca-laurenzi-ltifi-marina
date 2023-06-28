package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateFinishedTest {
    private GameStateFinished gameStateFinished;
    private Game game;
    private ArrayList<Player> players;

    @BeforeEach
    void setUp() throws MaxNumberOfPlayersException, IOException {
        gameStateFinished = new GameStateFinished();
        game = new Game(2, 2);
        players = new ArrayList<>();
        players.add(new Player("Player 1", game));
        players.add(new Player("Player 2", game));
    }

    @Test
    void nextPlayer_throwsFinishedGameException() {
        // Act & Assert
        assertThrows(FinishedGameException.class, () -> gameStateFinished.nextPlayer(game, players));
    }

    @Test
    void getStateNumber_returnsZero() {
        // Act & Assert
        assertEquals(0, gameStateFinished.getStateNumber());
    }
}