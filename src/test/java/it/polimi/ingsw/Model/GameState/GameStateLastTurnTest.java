package it.polimi.ingsw.Model.GameState;

import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;
import it.polimi.ingsw.Model.PlayerState.PlayerStateWaiting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateLastTurnTest {
    private GameStateLastTurn gameStateLastTurn;
    private Game game;
    private ArrayList<Player> players;

    @BeforeEach
    void setUp() throws MaxNumberOfPlayersException, IOException {
        gameStateLastTurn = new GameStateLastTurn();
        game = new Game(2, 2);
        players = new ArrayList<>();
        players.add(new Player("Player 1", game));
        players.add(new Player("Player 2", game));
        game.setCurrentPlayerIndex(0);
    }

    @Test
    void nextPlayer_changesStateAndIndex() {
        // Arrange
        players.get(0).changeState(new PlayerStateSelecting());

        // Act
        gameStateLastTurn.nextPlayer(game, players);

        // Assert
        assertEquals(new PlayerStateWaiting().getClass(), players.get(0).getState().getClass());
        assertEquals(new PlayerStateSelecting().getClass(), players.get(1).getState().getClass());
        assertEquals(1, game.getCurrentPlayerIndex());
    }

    @Test
    void nextPlayer_changesStateAndIndexToFirstPlayer() {
        // Arrange
        players.get(0).changeState(new PlayerStateSelecting());
        players.get(1).changeState(new PlayerStateWaiting());

        // Act
        gameStateLastTurn.nextPlayer(game, players);

        // Assert
        assertEquals(new PlayerStateSelecting().getClass(), players.get(1).getState().getClass());
        assertEquals(new PlayerStateWaiting().getClass(), players.get(0).getState().getClass());
        assertEquals(1, game.getCurrentPlayerIndex());
    }

    @Test
    void nextPlayer_setsGameStateToFinished() {
        // Arrange
        players.get(0).changeState(new PlayerStateSelecting());
        players.get(1).changeState(new PlayerStateWaiting());

        // Act
        gameStateLastTurn.nextPlayer(game, players);

        // Assert
        assertEquals(new GameStateStarting().getClass(), game.getGameState().getClass());
    }

    @Test
    void getStateNumber_returnsOne() {
        // Act & Assert
        assertEquals(1, gameStateLastTurn.getStateNumber());
    }
}