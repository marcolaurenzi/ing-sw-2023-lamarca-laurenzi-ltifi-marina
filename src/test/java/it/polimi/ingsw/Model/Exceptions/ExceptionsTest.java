package it.polimi.ingsw.Model.Exceptions;

import it.polimi.ingsw.Model.Exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    public void testAlreadyStartedGameException() {
        AlreadyStartedGameException exception = Assertions.assertThrows(AlreadyStartedGameException.class, () -> {
            throw new AlreadyStartedGameException("Game already started.");
        });
        Assertions.assertEquals("Game already started.", exception.getMessage());
    }

    @Test
    public void testColumnNotValidException() {
        ColumnNotValidException exception = Assertions.assertThrows(ColumnNotValidException.class, () -> {
            throw new ColumnNotValidException("Column is not valid.");
        });
        Assertions.assertEquals("Column is not valid.", exception.getMessage());
    }

    @Test
    public void testConnectionException() {
        ConnectionException exception = Assertions.assertThrows(ConnectionException.class, () -> {
            throw new ConnectionException("Connection error.");
        });
        Assertions.assertEquals("Connection error.", exception.getMessage());
    }

    @Test
    public void testCreateNewGameException() {
        CreateNewGameException exception = Assertions.assertThrows(CreateNewGameException.class, () -> {
            throw new CreateNewGameException("Error creating new game.");
        });
        Assertions.assertEquals("Error creating new game.", exception.getMessage());
    }

    @Test
    public void testDisconnectedPlayerException() {
        DisconnectedPlayerException exception = Assertions.assertThrows(DisconnectedPlayerException.class, () -> {
            throw new DisconnectedPlayerException("Player disconnected.");
        });
        Assertions.assertEquals("Player disconnected.", exception.getMessage());
    }

    @Test
    public void testFinishedGameException() {
        FinishedGameException exception = Assertions.assertThrows(FinishedGameException.class, () -> {
            throw new FinishedGameException("Game has finished.");
        });
        Assertions.assertEquals("Game has finished.", exception.getMessage());
    }

    @Test
    public void testGameAlreadyCreatedException() {
        GameAlreadyCreatedException exception = Assertions.assertThrows(GameAlreadyCreatedException.class, () -> {
            throw new GameAlreadyCreatedException("Game already created.");
        });
        Assertions.assertEquals("Game already created.", exception.getMessage());
    }

    @Test
    public void testGameNotStartedException() {
        GameNotStartedException exception = Assertions.assertThrows(GameNotStartedException.class, () -> {
            throw new GameNotStartedException("Game has not started.");
        });
        Assertions.assertEquals("Game has not started.", exception.getMessage());
    }

    @Test
    public void testMaxNumberOfPlayersException() {
        MaxNumberOfPlayersException exception = Assertions.assertThrows(MaxNumberOfPlayersException.class, () -> {
            throw new MaxNumberOfPlayersException("Maximum number of players exceeded.");
        });
        Assertions.assertEquals("Maximum number of players exceeded.", exception.getMessage());
    }

    @Test
    public void testMissingPlayerException() {
        MissingPlayerException exception = Assertions.assertThrows(MissingPlayerException.class, () -> {
            throw new MissingPlayerException("Player is missing.");
        });
        Assertions.assertEquals("Player is missing.", exception.getMessage());
    }

    @Test
    public void testNumberOfPlayersException() {
        NumberOfPlayersException exception = Assertions.assertThrows(NumberOfPlayersException.class, () -> {
            throw new NumberOfPlayersException("Number of players is not valid.");
        });
        Assertions.assertEquals("Number of players is not valid.", exception.getMessage());
    }

    @Test
    public void testPickDoesntFitColumnException() {
        PickDoesntFitColumnException exception = Assertions.assertThrows(PickDoesntFitColumnException.class, () -> {
            throw new PickDoesntFitColumnException("Picked item doesn't fit in the column.");
        });
        Assertions.assertEquals("Picked item doesn't fit in the column.", exception.getMessage());
    }

    @Test
    public void testPickedColumnOutOfBoundsException() {
        PickedColumnOutOfBoundsException exception = Assertions.assertThrows(PickedColumnOutOfBoundsException.class, () -> {
            throw new PickedColumnOutOfBoundsException("Column index is out of bounds.");
        });
        Assertions.assertEquals("Column index is out of bounds.", exception.getMessage());
    }

    @Test
    public void testPlayerIdAlreadyInUseException() {
        PlayerIdAlreadyInUseException exception = Assertions.assertThrows(PlayerIdAlreadyInUseException.class, () -> {
            throw new PlayerIdAlreadyInUseException("Player ID is already in use.");
        });
        Assertions.assertEquals("Player ID is already in use.", exception.getMessage());
    }

    @Test
    public void testPlayerIsWaitingException() {
        PlayerIsWaitingException exception = Assertions.assertThrows(PlayerIsWaitingException.class, () -> {
            throw new PlayerIsWaitingException("Player is in a waiting state.");
        });
        Assertions.assertEquals("Player is in a waiting state.", exception.getMessage());
    }

    @Test
    public void testPlayerOnlineException() {
        PlayerOnlineException exception = Assertions.assertThrows(PlayerOnlineException.class, () -> {
            throw new PlayerOnlineException();
        });
        Assertions.assertNotNull(exception);
    }

    @Test
    public void testSelectionIsEmptyException() {
        SelectionIsEmptyException exception = Assertions.assertThrows(SelectionIsEmptyException.class, () -> {
            throw new SelectionIsEmptyException("Selection is empty.");
        });
        Assertions.assertEquals("Selection is empty.", exception.getMessage());
    }

    @Test
    public void testSelectionIsFullException() {
        SelectionIsFullException exception = Assertions.assertThrows(SelectionIsFullException.class, () -> {
            throw new SelectionIsFullException("Selection is full.");
        });
        Assertions.assertEquals("Selection is full.", exception.getMessage());
    }

    @Test
    public void testSelectionNotValidException() {
        SelectionNotValidException exception = Assertions.assertThrows(SelectionNotValidException.class, () -> {
            throw new SelectionNotValidException("Selection is not valid.");
        });
        Assertions.assertEquals("Selection is not valid.", exception.getMessage());
    }

    @Test
    public void testTilesSelectionSizeDifferentFromOrderLengthException() {
        TilesSelectionSizeDifferentFromOrderLengthException exception = Assertions.assertThrows(TilesSelectionSizeDifferentFromOrderLengthException.class, () -> {
            throw new TilesSelectionSizeDifferentFromOrderLengthException("Selected tiles size is different from order length.");
        });
        Assertions.assertEquals("Selected tiles size is different from order length.", exception.getMessage());
    }

    @Test
    public void testVoidBoardTileException() {
        VoidBoardTileException exception = Assertions.assertThrows(VoidBoardTileException.class, () -> {
            throw new VoidBoardTileException("Attempting to access a void board tile.");
        });
        Assertions.assertEquals("Attempting to access a void board tile.", exception.getMessage());
    }

    @Test
    public void testWrongConfigurationException() {
        WrongConfigurationException exception = Assertions.assertThrows(WrongConfigurationException.class, () -> {
            throw new WrongConfigurationException("Incorrect game configuration.");
        });
        Assertions.assertEquals("Incorrect game configuration.", exception.getMessage());
    }

    @Test
    public void testWrongMessageClassEnumException() {
        WrongMessageClassEnumException exception = Assertions.assertThrows(WrongMessageClassEnumException.class, () -> {
            throw new WrongMessageClassEnumException("Incorrect message class enumeration.");
        });
        Assertions.assertEquals("Incorrect message class enumeration.", exception.getMessage());
    }

    @Test
    public void testWrongPasswordException() {
        WrongPasswordException exception = Assertions.assertThrows(WrongPasswordException.class, () -> {
            throw new WrongPasswordException("Incorrect password.");
        });
        Assertions.assertEquals("Incorrect password.", exception.getMessage());
    }
}
