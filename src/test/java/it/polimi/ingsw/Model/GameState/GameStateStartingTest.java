package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.GameNotStartedException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateStartingTest {
    private GameStateStarting gameStateStarting;
    private Game game;
    private ArrayList<Player> players;

    @BeforeEach
    void setUp() throws MaxNumberOfPlayersException, IOException {
        gameStateStarting = new GameStateStarting();
        game = new Game(1, 2);
        players = new ArrayList<>();
        players.add(new Player("Player 1", game));
        players.add(new Player("Player 2", game));
    }

    @Test
    void nextPlayer_throwsException() {
        // Act & Assert
        assertThrows(GameNotStartedException.class, () -> gameStateStarting.nextPlayer(game, players));
    }

    @Test
    void getStateNumber_returnsThree() {
        // Act & Assert
        assertEquals(3, gameStateStarting.getStateNumber());
    }
}