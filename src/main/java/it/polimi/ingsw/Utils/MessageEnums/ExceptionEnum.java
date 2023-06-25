package it.polimi.ingsw.Utils.MessageEnums;

/**
 * This enumeration represents the different types of exceptions that can occur in the game.
 */
public enum ExceptionEnum {
    /**
     * Thrown when a player tries to join a game that has already started.
     */
    AlreadyStartedGameException,

    /**
     * Thrown when a player tries to use an already-in-use ID.
     */
    PlayerIdAlreadyInUseException,

    /**
     * Thrown when a player tries to create a new game with invalid parameters.
     */
    CreateNewGameException,

    /**
     * Thrown when a player tries to join a game that has reached the maximum number of players.
     */
    MaxNumberOfPlayersException,

    /**
     * Thrown when a game is already created.
     */
    GameAlreadyCreatedException,

    /**
     * Thrown when a void board tile is encountered.
     */
    VoidBoardTileException,

    /**
     * Thrown when a player's selection is not valid.
     */
    SelectionNotValidException,

    /**
     * Thrown when a player is in a waiting state.
     */
    PlayerIsWaitingException,

    /**
     * Thrown when the size of the tiles selection is different from the order length.
     */
    TilesSelectionSizeDifferentFromOrderLengthException,

    /**
     * Thrown when a column is not valid.
     */
    ColumnNotValidException,

    /**
     * Thrown when a player's selection is empty.
     */
    SelectionIsEmptyException,

    /**
     * Thrown when a wrong configuration is encountered.
     */
    WrongConfigurationException,

    /**
     * Thrown when a picked column is out of bounds.
     */
    PickedColumnOutOfBoundsException,

    /**
     * Thrown when an incorrect password is provided.
     */
    WrongPasswordException,

    /**
     * Thrown when a player is disconnected.
     */
    DisconnectedPlayerException,

    /**
     * Thrown when a player is already online.
     */
    PlayerOnlineException,

    /**
     * Thrown when a picked tile doesn't fit in the column.
     */
    PickDoesntFitColumnException
}
